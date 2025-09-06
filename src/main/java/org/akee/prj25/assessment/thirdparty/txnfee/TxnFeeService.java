package org.akee.prj25.assessment.thirdparty.txnfee;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TxnFeeService {

	@GET("list")
	Call<TxnFeeResponse> getTxnFee(@Header("x-api-key") String apiKey);

}
