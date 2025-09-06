package org.akee.prj25.assessment.service.customer;

import org.akee.prj25.assessment.dto.CustomerResponseDto;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;

import lombok.Value;

public interface RemoveCustomerService {

	@Value
	public static class Input {

		private Long id;

	}

	CustomerResponseDto execute(Input input) throws ResourceNotFoundException;

}
