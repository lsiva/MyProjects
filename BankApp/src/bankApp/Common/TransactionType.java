package bankApp.Common;

public enum TransactionType {
	DEPOSIT ("Deposit"),
	WITHDRAWL("Withdraw"),
	BALANCE_INQUIRY("Balance Inquiry"),
	ACCOUNT_TRANSFER("Account Transfer");
	
	public final String value;

	TransactionType(String type) {
        value = type;
    }
	
	public String value() {
        return value;
    }


}
