package org.akee.prj25.assessment.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer implements Serializable {

	private static final long serialVersionUID = 5624713096053277659L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "wallet_balance", nullable = false)
	@Min(value = 499, message = "Wallet Balance must be at least 500")
	private Double walletBalance;

	@Version
	private Integer version;

	public Customer(String name, Double walletBalance) {
		super();
		this.name = name;
		this.walletBalance = walletBalance;
	}

	public void editCustomer(String name) {
		this.name = name;
	}

	public void detectBalance(Double transferAmount) {
		this.walletBalance = this.walletBalance - transferAmount;
	}

	public void transferBalance(Double transferAmount) {
		this.walletBalance = this.walletBalance + transferAmount;
	}

}
