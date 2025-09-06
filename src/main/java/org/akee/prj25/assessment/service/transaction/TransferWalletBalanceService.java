package org.akee.prj25.assessment.service.transaction;

import org.akee.prj25.assessment.dto.TransactionResponseDto;
import org.akee.prj25.assessment.exception.InputException;
import org.akee.prj25.assessment.exception.InsufficientBalanceException;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

public interface TransferWalletBalanceService {

	@Value
	public static class Input {

		@JsonProperty("from_customer_id")
		private Long fromCustomerId;

		@JsonProperty("to_customer_id")
		private Long toCustomerId;

		@JsonProperty("transfer_amount")
		private Double transferAmount;

	}

	TransactionResponseDto execute(Input input)
			throws ResourceNotFoundException, InsufficientBalanceException, InputException;

}
