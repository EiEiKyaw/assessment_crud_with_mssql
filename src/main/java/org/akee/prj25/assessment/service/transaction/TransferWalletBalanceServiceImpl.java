package org.akee.prj25.assessment.service.transaction;

import org.akee.prj25.assessment.dto.TransactionResponseDto;
import org.akee.prj25.assessment.entity.Customer;
import org.akee.prj25.assessment.entity.WalletTransaction;
import org.akee.prj25.assessment.exception.InputException;
import org.akee.prj25.assessment.exception.InsufficientBalanceException;
import org.akee.prj25.assessment.exception.ResourceNotFoundException;
import org.akee.prj25.assessment.repository.CustomerRepository;
import org.akee.prj25.assessment.repository.WalletTransactionRepository;
import org.akee.prj25.assessment.thirdparty.txnfee.TxnFeeClient;
import org.akee.prj25.assessment.thirdparty.txnfee.TxnFeeException;
import org.akee.prj25.assessment.thirdparty.txnfee.TxnFeeResponse;
import org.akee.prj25.assessment.thirdparty.txnfee.TxnFeeResponse.TxnFee;
import org.akee.prj25.assessment.util.CommonUtil;
import org.akee.prj25.assessment.util.ErrorMessage;
import org.akee.prj25.assessment.util.GeneralStatus;
import org.akee.prj25.assessment.util.TxnFeeCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransferWalletBalanceServiceImpl implements TransferWalletBalanceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransferWalletBalanceServiceImpl.class);

	private final CustomerRepository customerRepository;

	private final WalletTransactionRepository transactionHistoryRepository;

	private final TxnFeeClient txnFeeClient;

	@Override
	public TransactionResponseDto execute(Input input)
			throws ResourceNotFoundException, InsufficientBalanceException, InputException {

		if (input.getFromCustomerId() == null || input.getToCustomerId() == null) {
			LOGGER.error("Customer Ids is null");
			throw new InputException(ErrorMessage.IDS_NULL);
		}

		if (input.getTransferAmount() < 100) {
			LOGGER.warn("Transfer Amount must be greater than 100");
			throw new InputException(ErrorMessage.TRF_BALANCE_LIMIT);
		}

		Customer from = this.customerRepository.findById(input.getFromCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.TRF_ID_INVALID));

		Customer to = this.customerRepository.findById(input.getToCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.REV_ID_INVALID));

		TxnFee txnFee = this.getTxnFeeByAmount(input.getTransferAmount());

		WalletTransaction history = new WalletTransaction(from.getId(), to.getId(), input.getTransferAmount(),
				txnFee.getFeeAmount());

		double detectedAmount = input.getTransferAmount() + txnFee.getFeeAmount();

		LOGGER.debug("<{}> will detect from customer id : <{}>", detectedAmount, from.getId());

		if (from.getWalletBalance() < detectedAmount) {

			history.changeStatus(GeneralStatus.INSUFFICIENT_BALANCE.name());

			this.transactionHistoryRepository.save(history);

			throw new InsufficientBalanceException(ErrorMessage.INSUFFICIENT_BALANCE);

		}

		from.detectBalance(detectedAmount);
		customerRepository.save(from);

		to.transferBalance(input.getTransferAmount());
		customerRepository.save(to);

		this.transactionHistoryRepository.save(history);

		return new TransactionResponseDto(history.getId(), history.getTransactionRefId(), history.getStatus(),
				CommonUtil.formatDateToString(history.getCreatedAt()));

	}

	private TxnFeeResponse getTxnFeeList() throws InputException {

		TxnFeeResponse fees = new TxnFeeResponse();

		try {

			fees = txnFeeClient.getTxnFeeList();

		} catch (TxnFeeException e) {

			throw new InputException(ErrorMessage.TXN_FEE_ERROR);

		}

		return fees;

	}

	private TxnFeeResponse.TxnFee getTxnFeeByAmount(double amount) throws InputException {
		TxnFeeCode feeCode = this.getFeeCodeByAmount(amount);

		TxnFee txnFee = this.getTxnFeeList().getTxnFeeList().stream().filter(f -> f.getCode().equals(feeCode.name()))
				.findFirst().orElseThrow(() -> new InputException(ErrorMessage.TXN_FEE_NOT_FOUND));

		return txnFee;
	}

	private TxnFeeCode getFeeCodeByAmount(double amount) {

		if (amount < 1000)
			return TxnFeeCode.BELOW_1000;

		if (amount >= 1000 && amount < 5000)
			return TxnFeeCode.ABOVE_1000;

		if (amount >= 5000 && amount < 10000)
			return TxnFeeCode.ABOVE_5000;

		return TxnFeeCode.ABOVE_10000;

	}

}
