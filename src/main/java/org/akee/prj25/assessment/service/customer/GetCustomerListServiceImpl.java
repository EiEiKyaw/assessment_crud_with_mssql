package org.akee.prj25.assessment.service.customer;

import org.akee.prj25.assessment.dto.PageResponseDto;
import org.akee.prj25.assessment.entity.Customer;
import org.akee.prj25.assessment.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetCustomerListServiceImpl implements GetCustomerListService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetCustomerListServiceImpl.class);

	private final CustomerRepository customerRepository;

	@Override
	@Transactional(readOnly = true)
	public PageResponseDto<Customer> execute(Input input) {

		LOGGER.info("Find Customer by name : <{}>", input.getName());

		Pageable pageable = PageRequest.of(Math.max(0, input.getPage() - 1), input.getSize());

		Page<Customer> customerList = (input.getName() == null || input.getName().isBlank())
				? customerRepository.findAll(pageable)
				: customerRepository.findByNameContainingIgnoreCase(input.getName(), pageable);

		return new PageResponseDto<>(customerList.getContent(), input.getPage(), customerList.getSize(),
				customerList.getTotalElements(), customerList.getTotalPages());

	}

}
