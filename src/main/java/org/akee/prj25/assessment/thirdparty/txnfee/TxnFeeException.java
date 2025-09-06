package org.akee.prj25.assessment.thirdparty.txnfee;

public class TxnFeeException extends Exception {

	private static final long serialVersionUID = 1651272370961357665L;

	public TxnFeeException(String message) {
		super(message);
	}

	public TxnFeeException(String message, Throwable cause) {
		super(message, cause);
	}
}
