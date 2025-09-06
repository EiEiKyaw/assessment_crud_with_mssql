package org.akee.prj25.assessment.service.transaction;

import org.akee.prj25.assessment.dto.TransactionDetailDto;
import org.akee.prj25.assessment.exception.InputException;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;
import org.akee.prj25.assessment.repository.WalletTransactionRepository;
import org.akee.prj25.assessment.util.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetTransactionDetailServiceImpl implements GetTransactionDetailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetTransactionDetailServiceImpl.class);

	private final WalletTransactionRepository walletTransactionRepository;

	@Override
	@Transactional(readOnly = true)
	public TransactionDetailDto execute(Input input) throws ResourceNotFoundException, InputException {

		LOGGER.info("Get Transaction detail by txn_ref_id : <{}>", input.getTransactionRefId());

		if (input.getTransactionRefId() == null || input.getTransactionRefId().isEmpty()) {

			throw new InputException(ErrorMessage.TXN_REF_ID_NULL);

		}

		TransactionDetailDto transaction = this.walletTransactionRepository
				.findTransactionDetailByRefId(input.getTransactionRefId());

		if (transaction == null) {

			throw new ResourceNotFoundException(ErrorMessage.TXN_REF_ID_INVALID);

		}

		return transaction;

	}

}
