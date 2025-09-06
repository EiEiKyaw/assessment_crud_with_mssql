package org.akee.prj25.assessment.exception;

import org.akee.prj25.assessment.util.ErrorMessage;

public class InputException extends DomainException {

	private static final long serialVersionUID = -2563514607506662758L;

	public InputException(ErrorMessage message) {
		super(message);
	}

}
