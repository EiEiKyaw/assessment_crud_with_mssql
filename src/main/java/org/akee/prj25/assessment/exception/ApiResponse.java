package org.akee.prj25.assessment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

	private int status;
	private String message;
	private long timestamp;
	private String path;

}
