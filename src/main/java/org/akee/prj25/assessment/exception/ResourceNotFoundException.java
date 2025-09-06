package org.akee.prj25.assessment.exception;

import org.akee.prj25.assessment.util.ErrorMessage;

public class ResourceNotFoundException extends DomainException {

	private static final long serialVersionUID = -1822539962831397399L;

	public ResourceNotFoundException(ErrorMessage message) {
		super(message);
	}

}
