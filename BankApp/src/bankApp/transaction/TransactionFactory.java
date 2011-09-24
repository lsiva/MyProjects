package bankApp.transaction;

import bankApp.Amount;
import bankApp.Common.TransactionType;

public class TransactionFactory {
	public static Transaction createTransaction(TransactionType message, Integer accountNumber, Integer accountToNumber, Amount transactionAmount){
		switch(message){
			case DEPOSIT:
				 return new DepositTransaction(accountNumber,transactionAmount);
			case WITHDRAWL:
				return new WithdrawTransaction(accountNumber,transactionAmount);
			case BALANCE_INQUIRY:
				 return new BalanceInquiryTransaction(accountNumber);
			case ACCOUNT_TRANSFER:
				 return new AccountTransferTransaction(accountNumber,accountToNumber,transactionAmount );
			}
		return null; 
	}
}
