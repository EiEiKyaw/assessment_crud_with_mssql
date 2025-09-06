package org.akee.prj25.assessment.controller.customer;

import org.akee.prj25.assessment.dto.PageResponseDto;
import org.akee.prj25.assessment.entity.Customer;
import org.akee.prj25.assessment.service.customer.GetCustomerListService;
import org.akee.prj25.assessment.service.customer.GetCustomerListService.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RestController
@RequiredArgsConstructor
public class GetCustomerListController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetCustomerListController.class);

	private final GetCustomerListService getCustomerListService;

	@PostMapping("/customer/list")
	public ResponseEntity<PageResponseDto<Customer>> getCustomerList(@RequestBody @Valid Request request) {

		LOGGER.info("Start - GetCustomerListController with request : <{}>", request);

		PageResponseDto<Customer> response = this.getCustomerListService
				.execute(new Input(request.getName(), request.getPage(), request.getSize()));

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Value
	public static class Request {

		private String name;

		private Integer page;

		private Integer size;

		public int getPage() {
			return page != null ? page : 0;
		}

		public int getSize() {
			return size != null ? size : 10;
		}

	}

}
