package org.akee.prj25.assessment.controller.customer;

import org.akee.prj25.assessment.dto.CustomerResponseDto;
import org.akee.prj25.assessment.exception.DuplicateResourceException;
import org.akee.prj25.assessment.service.customer.CreateCustomerService;
import org.akee.prj25.assessment.service.customer.CreateCustomerService.Input;
import org.akee.prj25.assessment.view.CustomerViews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RestController
@RequiredArgsConstructor
public class CreateCustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateCustomerController.class);

	private final CreateCustomerService createCustomerService;

	@PostMapping("/customer/create")
	@JsonView(CustomerViews.Create.class)
	public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody @Valid Request request)
			throws DuplicateResourceException {

		LOGGER.info("Start - CreateCustomerController with name : <{}>", request.getName());

		CustomerResponseDto response = this.createCustomerService
				.execute(new Input(request.getName(), request.getWalletBalance()));

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@Value
	public static class Request {

		@NotNull(message = "Name must not be null")
		@NotEmpty(message = "Name must not be empty")
		private String name;

		@JsonProperty("wallet_balance")
		@NotNull(message = "Wallet Balance must not be null")
		@DecimalMin(value = "499", inclusive = false, message = "Wallet Balance must be greater than 500")
		private Double walletBalance;

	}

}
