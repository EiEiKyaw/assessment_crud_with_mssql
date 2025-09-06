package org.akee.prj25.assessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionRequestDto {

	@JsonProperty("from_customer_id")
	@NotNull(message = "Sender Id must not be null")
	private Long fromCustomerId;

	@JsonProperty("to_customer_id")
	@NotNull(message = "Receiver Id must not be null")
	private Long toCustomerId;

	@JsonProperty("transfer_amount")
	@NotNull(message = "Amount must not be null")
	@Positive(message = "Amount must be greater than 5")
	private Double transferAmount;

}
