package org.akee.prj25.assessment.controller.customer;

import org.akee.prj25.assessment.dto.CustomerResponseDto;
import org.akee.prj25.assessment.exception.InsufficientBalanceException;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;
import org.akee.prj25.assessment.service.customer.RemoveCustomerService;
import org.akee.prj25.assessment.service.customer.RemoveCustomerService.Input;
import org.akee.prj25.assessment.view.CustomerViews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RemoveCustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoveCustomerController.class);

	private final RemoveCustomerService removeCustomerService;

	@DeleteMapping(value = "/customer/delete/{id}")
	@JsonView(CustomerViews.Remove.class)
	public ResponseEntity<CustomerResponseDto> removeCustomer(@PathVariable(required = true) Long id)
			throws ResourceNotFoundException, InsufficientBalanceException {

		LOGGER.info("Start - RemoveCustomerController with id : <{}>", id);

		CustomerResponseDto response = this.removeCustomerService.execute(new Input(id));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
