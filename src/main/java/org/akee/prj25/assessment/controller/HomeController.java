package org.akee.prj25.assessment.controller;

import java.time.Instant;

import org.akee.prj25.assessment.util.CommonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@RestController
public class HomeController {

	@Value
	private static class Response {

		@JsonProperty("description")
		private String description;

		@JsonProperty("date")
		private String date;

	}

	@GetMapping("/")
	public ResponseEntity<HomeController.Response> execute() {

		return new ResponseEntity<HomeController.Response>(
				new HomeController.Response("Welcome to Home Page.", CommonUtil.formatDateToString(Instant.now())),
				HttpStatus.OK);

	}
}
