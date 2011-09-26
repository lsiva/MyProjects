package bankApp.transaction;

import bankApp.Amount;
import bankApp.common.TransactionType;
import bankApp.exception.TransactionException;

public class BalanceInquiryTransaction extends Transaction {
	public BalanceInquiryTransaction(Integer accountNumber) {
		super(TransactionType.BALANCE_INQUIRY, accountNumber, new Amount(0));
	}

	public void validateTransaction(BalanceInquiryTransaction transaction)
			throws TransactionException {
		validateAccountNumber(transaction.getAccountNumber());
	}
}
