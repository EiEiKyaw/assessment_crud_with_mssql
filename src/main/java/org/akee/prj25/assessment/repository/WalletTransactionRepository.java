package org.akee.prj25.assessment.repository;

import org.akee.prj25.assessment.dto.TransactionDetailDto;
import org.akee.prj25.assessment.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {

	@Query("SELECT new org.akee.prj25.assessment.dto.TransactionDetailDto(wt.id as transactionId, wt.transactionRefId, "
			+ "s.name as senderName, r.name as receiverName, "
			+ "wt.transferAmount, wt.transferFee, wt.status, wt.createdAt) FROM WalletTransaction wt "
			+ "JOIN Customer s ON wt.fromCustomerId = s.id JOIN Customer r ON wt.toCustomerId = r.id "
			+ "WHERE wt.transactionRefId = :transactionRefId")
	TransactionDetailDto findTransactionDetailByRefId(@Param("transactionRefId") String transactionRefId);

}