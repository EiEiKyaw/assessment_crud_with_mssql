package org.akee.prj25.assessment.controller.customer;

import org.akee.prj25.assessment.dto.CustomerResponseDto;
import org.akee.prj25.assessment.exception.DuplicateResourceException;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;
import org.akee.prj25.assessment.service.customer.EditCustomerService;
import org.akee.prj25.assessment.service.customer.EditCustomerService.Input;
import org.akee.prj25.assessment.view.CustomerViews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RestController
@RequiredArgsConstructor
public class EditCustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EditCustomerController.class);

	private final EditCustomerService editCustomerService;

	@PutMapping("/customer/edit/{id}")
	@JsonView(CustomerViews.Update.class)
	public ResponseEntity<CustomerResponseDto> editCustomer(@PathVariable(required = true) Long id,
			@RequestBody @Valid Request request) throws DuplicateResourceException, ResourceNotFoundException {

		LOGGER.info("Start - EditCustomerController with id : <{}>", id);

		CustomerResponseDto response = this.editCustomerService.execute(new Input(id, request.getName()));

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Value
	public static class Request {

		@NotNull(message = "Name must not be null")
		@NotEmpty(message = "Name must not be empty")
		private String name;

	}

}
