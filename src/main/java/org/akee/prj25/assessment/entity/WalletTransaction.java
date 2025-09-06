package org.akee.prj25.assessment.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import org.akee.prj25.assessment.util.GeneralStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wallet_transaction")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WalletTransaction implements Serializable {

	private static final long serialVersionUID = -6421836604744690137L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "from_customer_id", nullable = false)
	private Long fromCustomerId;

	@Column(name = "to_customer_id", nullable = false)
	private Long toCustomerId;

	@Column(name = "transfer_amount", nullable = false)
	@Min(value = 99, message = "Wallet Balance must be at least 100")
	private Double transferAmount;

	@Column(name = "transfer_fee", nullable = false)
	private Double transferFee;

	// SUCCESS, FAILED, INSUFFICIENT_BALANCE
	@Column(name = "status")
	private String status;

	@Column(name = "transaction_ref_id", nullable = false)
	private String transactionRefId;

	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	public WalletTransaction(Long fromCustomerId, Long toCustomerId, Double transferAmount, Double transferFee) {
		super();
		this.fromCustomerId = fromCustomerId;
		this.toCustomerId = toCustomerId;
		this.transferAmount = transferAmount;
		this.transferFee = transferFee;
		this.status = GeneralStatus.SUCCESS.name();
		this.createdAt = Instant.now();
		this.transactionRefId = UUID.randomUUID().toString().replace("-", "").substring(0, 13);
	}

	public void changeStatus(String statusName) {
		this.status = statusName;
	}

}
