package org.akee.prj25.assessment.service.customer;

import org.akee.prj25.assessment.dto.CustomerResponseDto;
import org.akee.prj25.assessment.exception.DuplicateResourceException;

import lombok.Value;

public interface CreateCustomerService {

	@Value
	public static class Input {

		private String name;

		private Double walletBalance;

	}

	CustomerResponseDto execute(Input input) throws DuplicateResourceException;

}
