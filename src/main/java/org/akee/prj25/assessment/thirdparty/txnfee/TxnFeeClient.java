package org.akee.prj25.assessment.thirdparty.txnfee;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Component
public class TxnFeeClient {

	@Getter(AccessLevel.NONE)
	private final TxnFeeSettings settings;

	@Getter(AccessLevel.NONE)
	private final TxnFeeService txnFeeService;

	private final TxnFeeErrorDecoder errorDecoder;

	public TxnFeeClient(TxnFeeSettings settings, ObjectMapper objectMapper) {

		this.settings = settings;

		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

		logging.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(settings.getBaseUrl())
				.addConverterFactory(JacksonConverterFactory.create(objectMapper)).client(client).build();

		this.txnFeeService = retrofit.create(TxnFeeService.class);

		this.errorDecoder = new TxnFeeErrorDecoder(objectMapper);
	}

	public TxnFeeResponse getTxnFeeList() throws TxnFeeException {

		Response<TxnFeeResponse> rawResponse;

		try {

			rawResponse = (Response<TxnFeeResponse>) txnFeeService.getTxnFee(settings.getApiKey()).execute();

		} catch (Exception e) {

			throw new TxnFeeException("Network or system error: " + e.getMessage(), e);

		}

		if (!rawResponse.isSuccessful() || rawResponse.body() == null) {

			throw new TxnFeeException(errorDecoder.decode(rawResponse));

		}

		return rawResponse.body();

	}

}
