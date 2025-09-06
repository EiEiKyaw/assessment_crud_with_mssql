package org.akee.prj25.assessment.util;

public record ErrorMessage(String code, String description) {

	public static final ErrorMessage ID_INVALID = new ErrorMessage("A001", "ID is invalid.");
	public static final ErrorMessage INSUFFICIENT_BALANCE = new ErrorMessage("A002", "Insufficient balance.");
	public static final ErrorMessage DUP_CUST_NAME = new ErrorMessage("A003", "Duplicate customer name.");

	public static final ErrorMessage TRF_ID_INVALID = new ErrorMessage("A004", "Sender Id is invalid.");
	public static final ErrorMessage REV_ID_INVALID = new ErrorMessage("A005", "Receiver Id is invalid.");

	public static final ErrorMessage IDS_NULL = new ErrorMessage("A006", "Customer IDs must not be null");
	public static final ErrorMessage TRF_BALANCE_LIMIT = new ErrorMessage("A007",
			"Transfer amount must be at least 100");

	// For transaction
	public static final ErrorMessage TXN_REF_ID_NULL = new ErrorMessage("A008", "Transaction ref id must not be null");
	public static final ErrorMessage TXN_REF_ID_INVALID = new ErrorMessage("A009", "Transaction ref id is invalid");

	// For transaction fee
	public static final ErrorMessage TXN_FEE_ERROR = new ErrorMessage("T001", "Failed to fetch transaction fee");
	public static final ErrorMessage TXN_FEE_NOT_FOUND = new ErrorMessage("T002", "Transaction fee not found");

}
