package org.akee.prj25.assessment.service.transaction;

import org.akee.prj25.assessment.dto.TransactionDetailDto;
import org.akee.prj25.assessment.exception.InputException;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;

import lombok.Value;

public interface GetTransactionDetailService {

	@Value
	public static class Input {

		private String transactionRefId;

	}

	TransactionDetailDto execute(Input input) throws ResourceNotFoundException, InputException;

}
