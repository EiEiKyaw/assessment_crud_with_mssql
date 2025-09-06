package org.akee.prj25.assessment.controller.transaction;

import org.akee.prj25.assessment.dto.TransactionResponseDto;
import org.akee.prj25.assessment.exception.InputException;
import org.akee.prj25.assessment.exception.InsufficientBalanceException;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;
import org.akee.prj25.assessment.service.transaction.TransferWalletBalanceService;
import org.akee.prj25.assessment.service.transaction.TransferWalletBalanceService.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@AllArgsConstructor
public class TransferWalletBalanceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransferWalletBalanceController.class);

	private final TransferWalletBalanceService transferWalletBalance;

	@PostMapping("/wallet/transfer")
	public ResponseEntity<TransactionResponseDto> transferWalletBalance(@RequestBody @Valid Request request)
			throws ResourceNotFoundException, InsufficientBalanceException, InputException {

		LOGGER.info("Start - TransactionController from <{}> to <{}>", request.getFromCustomerId(),
				request.getToCustomerId());

		TransactionResponseDto output = this.transferWalletBalance.execute(
				new Input(request.getFromCustomerId(), request.getToCustomerId(), request.getTransferAmount()));

		return new ResponseEntity<>(new TransactionResponseDto(output.getTransactionId(), output.getTransactionRefId(),
				output.getStatus(), output.getCreatedAt()), HttpStatus.CREATED);

	}

	@Data
	private static class Request {

		@JsonProperty("from_customer_id")
		@NotNull(message = "Sender Id must not be null")
		private Long fromCustomerId;

		@JsonProperty("to_customer_id")
		@NotNull(message = "Receiver Id must not be null")
		private Long toCustomerId;

		@JsonProperty("transfer_amount")
		@NotNull(message = "Transfer Amount must not be null")
		@DecimalMin(value = "99", inclusive = false, message = "Transfer Amount must be greater than 100")
		private Double transferAmount;

	}

}