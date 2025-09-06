package org.akee.prj25.assessment.thirdparty.txnfee;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Value;

@Data
public class TxnFeeResponse {

	@JsonProperty("data")
	private List<TxnFee> txnFeeList;

	@Value
	public static class TxnFee {

		private Long id;

		private String code;

		@JsonProperty("transaction_fee_amount")
		private Double feeAmount;

		private String status;
	}

}
