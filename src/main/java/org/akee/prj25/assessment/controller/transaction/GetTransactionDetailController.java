package org.akee.prj25.assessment.controller.transaction;

import org.akee.prj25.assessment.dto.TransactionDetailDto;
import org.akee.prj25.assessment.exception.InputException;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;
import org.akee.prj25.assessment.service.transaction.GetTransactionDetailService;
import org.akee.prj25.assessment.service.transaction.GetTransactionDetailService.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@AllArgsConstructor
public class GetTransactionDetailController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetTransactionDetailController.class);

	private final GetTransactionDetailService getTransactionDetailService;

	@PostMapping("/wallet/transaction/detail")
	public ResponseEntity<TransactionDetailDto> getTransactionDetail(@RequestBody @Valid Request request)
			throws ResourceNotFoundException, InputException {

		LOGGER.info("Start - GetTransactionDetailController with txn_ref_id : <{}>", request.getTransactionRefId());

		TransactionDetailDto response = this.getTransactionDetailService
				.execute(new Input(request.getTransactionRefId()));

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Data
	private static class Request {

		@JsonProperty("transaction_ref_id")
		@NotNull(message = "Transaction Ref Id must not be null")
		private String transactionRefId;

	}

}