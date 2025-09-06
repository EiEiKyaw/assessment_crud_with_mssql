package org.akee.prj25.assessment.dto;

import org.akee.prj25.assessment.view.CustomerViews;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerResponseDto {

	@JsonView(CustomerViews.All.class)
	@JsonProperty("customer_id")
	private Long customerId;

	@JsonView(CustomerViews.All.class)
	private String name;

	@JsonView(CustomerViews.All.class)
	private Double walletBalance;

	@JsonView(CustomerViews.All.class)
	private int version;

	@JsonView(CustomerViews.Remove.class)
	private String status;

	public CustomerResponseDto(String status) {
		this.status = status;
	}

}
