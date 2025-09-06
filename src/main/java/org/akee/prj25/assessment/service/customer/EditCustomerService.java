package org.akee.prj25.assessment.service.customer;

import org.akee.prj25.assessment.dto.CustomerResponseDto;
import org.akee.prj25.assessment.exception.DuplicateResourceException;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;

import lombok.Value;

public interface EditCustomerService {

	@Value
	public static class Input {

		private Long id;

		private String name;

	}

	CustomerResponseDto execute(Input input) throws DuplicateResourceException, ResourceNotFoundException;

}
