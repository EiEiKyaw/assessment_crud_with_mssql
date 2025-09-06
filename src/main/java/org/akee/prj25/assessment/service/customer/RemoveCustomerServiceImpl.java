package org.akee.prj25.assessment.service.customer;

import org.akee.prj25.assessment.dto.CustomerResponseDto;
import org.akee.prj25.assessment.entity.Customer;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;
import org.akee.prj25.assessment.repository.CustomerRepository;
import org.akee.prj25.assessment.util.ErrorMessage;
import org.akee.prj25.assessment.util.GeneralStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RemoveCustomerServiceImpl implements RemoveCustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoveCustomerServiceImpl.class);

	private final CustomerRepository customerRepository;

	@Override
	@Transactional(rollbackFor = { ResourceNotFoundException.class })
	public CustomerResponseDto execute(Input input) throws ResourceNotFoundException {

		LOGGER.info("Remove Customer by id : <{}>", input.getId());

		Customer existing = this.customerRepository.findById(input.getId())
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.ID_INVALID));

		this.customerRepository.delete(existing);

		return new CustomerResponseDto(GeneralStatus.SUCCESS.name());

	}

}
