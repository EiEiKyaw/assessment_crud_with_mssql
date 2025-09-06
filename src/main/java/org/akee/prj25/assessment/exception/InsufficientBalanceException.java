package org.akee.prj25.assessment.exception;

import org.akee.prj25.assessment.util.ErrorMessage;

public class InsufficientBalanceException extends DomainException {

	private static final long serialVersionUID = -2746832018590679981L;

	public InsufficientBalanceException(ErrorMessage message) {
		super(message);
	}

}
