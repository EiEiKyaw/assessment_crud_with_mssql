package org.akee.prj25.assessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponseDto {

	@JsonProperty("transaction_id")
	private Long transactionId;

	@JsonProperty("transaction_ref_id")
	private String transactionRefId;

	private String status;

	@JsonProperty("created_at")
	private String createdAt;

}
