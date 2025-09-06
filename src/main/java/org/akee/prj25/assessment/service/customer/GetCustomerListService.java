package org.akee.prj25.assessment.service.customer;

import org.akee.prj25.assessment.dto.PageResponseDto;
import org.akee.prj25.assessment.entity.Customer;

import lombok.Value;

public interface GetCustomerListService {

	@Value
	public static class Input {

		private String name;

		private int page;

		private int size;

	}

	PageResponseDto<Customer> execute(Input input);

}
