package bankApp.atm;

import bankApp.Amount;
import bankApp.Bank;
import bankApp.Common.TransactionType;
import bankApp.response.Response;
import bankApp.transaction.TransactionFactory;

public class BankAtm {

	public Response deposit(Integer acctNumber, Amount depositAmount) {
		return Bank.processTransaction(TransactionFactory.createTransaction(
				TransactionType.DEPOSIT, acctNumber, null, depositAmount));

	}

	public Response withdraw(Integer acctNumber, Amount withdrawAmount) {
		return Bank.processTransaction(TransactionFactory.createTransaction(
				TransactionType.WITHDRAWL, acctNumber, null, withdrawAmount));

	}

	public Response balanceInquiry(Integer acctNumber) {
		return Bank.processTransaction(TransactionFactory.createTransaction(
				TransactionType.BALANCE_INQUIRY, acctNumber, null, null));

	}

	public Response acctTransfer(Integer acctNumberFrom, Integer acctNumberTo,
			Amount transferAmount) {
		return Bank.processTransaction(TransactionFactory.createTransaction(
				TransactionType.ACCOUNT_TRANSFER, acctNumberFrom, acctNumberTo,
				transferAmount));

	}
	
	 
}
