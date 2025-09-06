package org.akee.prj25.assessment.service.customer;

import org.akee.prj25.assessment.dto.CustomerResponseDto;
import org.akee.prj25.assessment.entity.Customer;
import org.akee.prj25.assessment.exception.DuplicateResourceException;
import org.akee.prj25.assessment.mapper.CustomerMapper;
import org.akee.prj25.assessment.repository.CustomerRepository;
import org.akee.prj25.assessment.util.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateCustomerServiceImpl implements CreateCustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateCustomerServiceImpl.class);

	private final CustomerRepository customerRepository;

	@Override
	public CustomerResponseDto execute(Input input) throws DuplicateResourceException {

		LOGGER.info("Create Customer input : <{}>", input);

		if (this.customerRepository.findByName(input.getName()).isPresent()) {

			LOGGER.error("Customer name is already taken : <{}>", input.getName());
			throw new DuplicateResourceException(ErrorMessage.DUP_CUST_NAME);
		}

		Customer entity = this.customerRepository.save(new Customer(input.getName(), input.getWalletBalance()));

		return CustomerMapper.toResponseDto(entity);

	}

}
