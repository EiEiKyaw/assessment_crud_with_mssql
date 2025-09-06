package org.akee.prj25.assessment.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDetailDto {

	@JsonProperty("transaction_id")
	private Long transactionId;

	@JsonProperty("transaction_ref_id")
	private String transactionRefId;

	@JsonProperty("sender_name")
	private String senderName;

	@JsonProperty("receiver_name")
	private String receiverName;

	@JsonProperty("tansfer_amount")
	private Double transferAmount;

	@JsonProperty("tansfer_fee")
	private Double transferFee;

	private String status;

	@JsonProperty("created_at")
	private Instant createdAt;

}
