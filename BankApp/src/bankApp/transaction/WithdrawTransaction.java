package bankApp.transaction;

import bankApp.Amount;
import bankApp.Bank;
import bankApp.common.BankAppConstants;
import bankApp.common.TransactionType;
import bankApp.exception.TransactionException;

public class WithdrawTransaction extends Transaction{
	public WithdrawTransaction(Integer accountNumber, Amount withdrawlAmount){
		super(TransactionType.WITHDRAWL, accountNumber,withdrawlAmount);
	}
	
	public void validateTransaction(WithdrawTransaction transaction)
			throws TransactionException {
		validateAccountNumber(transaction.getAccountNumber());
		validateOverdraftPrevention(transaction);

	}

	private void validateOverdraftPrevention(WithdrawTransaction transaction) throws TransactionException {
		Amount withDrawAccountBalance = Bank.getAccount(accountNumber).getBalance();
		if(withDrawAccountBalance.compareTo(transaction.getTransactionAmount()) < 0){
			throw new TransactionException(BankAppConstants.WITHDRAWL_IN_OVERDRAFT);
		}
			
		
	}

}
