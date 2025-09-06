package org.akee.prj25.assessment.exception;

import org.akee.prj25.assessment.util.ErrorMessage;

public class DuplicateResourceException extends DomainException {

	private static final long serialVersionUID = -6691916858555570671L;

	public DuplicateResourceException(ErrorMessage message) {
		super(message);
	}

}
