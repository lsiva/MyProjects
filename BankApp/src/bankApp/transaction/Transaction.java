package bankApp.transaction;

import bankApp.Amount;
import bankApp.Bank;
import bankApp.Common.BankAppConstants;
import bankApp.Common.TransactionType;
import bankApp.exception.TransactionException;

public abstract class Transaction {
	TransactionType transactionType;
	protected Integer accountNumber;
	protected Amount transactionAmount;

	public Transaction(TransactionType message, Integer acctNumber,
			Amount amount) {
		this.transactionType = message;
		this.accountNumber = acctNumber;
		this.transactionAmount = amount;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer acctNumber) {
		this.accountNumber = acctNumber;
	}

	public Amount getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Amount amount) {
		this.transactionAmount = amount;
	}

	public TransactionType getTransactionMessage() {
		return transactionType;
	}

	public void setTransactionMessage(TransactionType transactionMessage) {
		this.transactionType = transactionMessage;
	}

	public void validateAccountNumber(Integer accountNumber)
			throws TransactionException {
		if (!Bank.checkAccountNumber(accountNumber))
			throw new TransactionException(BankAppConstants.INVALID_ACCOUNT);
	}

}