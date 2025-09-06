package org.akee.prj25.assessment.exception;

import org.akee.prj25.assessment.util.ErrorMessage;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class DomainException extends Exception {

	private static final long serialVersionUID = 3574976269188709999L;

	@Getter(AccessLevel.PUBLIC)
	private final ErrorMessage errorMessage;

	public DomainException(ErrorMessage errorMessage) {

		super(errorMessage.code() + " :: " + errorMessage.description());
		this.errorMessage = errorMessage;
	}

	public DomainException() {

		super();
		this.errorMessage = null;
	}

}