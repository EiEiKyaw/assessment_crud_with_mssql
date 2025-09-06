package org.akee.prj25.assessment.controller.customer;

import org.akee.prj25.assessment.dto.CustomerResponseDto;
import org.akee.prj25.assessment.exception.DuplicateResourceException;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;
import org.akee.prj25.assessment.service.customer.GetCustomerService;
import org.akee.prj25.assessment.service.customer.GetCustomerService.Input;
import org.akee.prj25.assessment.view.CustomerViews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GetCustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetCustomerController.class);

	private final GetCustomerService getCustomerService;

	@GetMapping("/customer/detail/{id}")
	@JsonView(CustomerViews.All.class)
	public ResponseEntity<CustomerResponseDto> editCustomer(@PathVariable(required = true) Long id)
			throws DuplicateResourceException, ResourceNotFoundException {

		LOGGER.info("Start - GetCustomerController with id : <{}>", id);

		CustomerResponseDto response = this.getCustomerService.execute(new Input(id));

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
