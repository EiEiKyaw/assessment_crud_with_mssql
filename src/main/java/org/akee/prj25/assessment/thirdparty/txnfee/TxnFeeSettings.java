package org.akee.prj25.assessment.thirdparty.txnfee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class TxnFeeSettings {

	@Value("${txn_fee.base-url}")
	private String baseUrl;

	@Value("${txn_fee.api-key}")
	private String apiKey;

}