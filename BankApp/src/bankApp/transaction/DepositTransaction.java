package bankApp.transaction;

import bankApp.Amount;
import bankApp.Common.TransactionType;
import bankApp.exception.TransactionException;

public class DepositTransaction extends Transaction {
	
	public DepositTransaction(Integer accountNumber, Amount depositAmount){
		super(TransactionType.DEPOSIT, accountNumber, depositAmount);
	}

	public void validateTransaction(DepositTransaction transaction) throws TransactionException {
		validateAccountNumber(transaction.getAccountNumber());
		
	}

}
