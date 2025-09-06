package org.akee.prj25.assessment.thirdparty.txnfee;

import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Response;

public class TxnFeeErrorDecoder {

	private final ObjectMapper objectMapper;

	public TxnFeeErrorDecoder(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public String decode(Response<?> response) {

		try {

			if (response.errorBody() != null) {

				return response.errorBody().string();

			}

		} catch (Exception e) {

			return "Unknown error occurred";

		}

		return "Unknown error occurred";
	}
}
