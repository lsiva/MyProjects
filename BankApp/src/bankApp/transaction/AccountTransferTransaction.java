package bankApp.transaction;

import bankApp.Amount;
import bankApp.Common.TransactionType;

public class AccountTransferTransaction extends Transaction {
	
	Integer accountToNumber;
	
	
	public AccountTransferTransaction(Integer accountNumber, Integer accountToNum,Amount transferAmount){
		super(TransactionType.ACCOUNT_TRANSFER, accountNumber, transferAmount);
		accountToNumber = accountToNum;	
	}

	public Integer getAccountToNumber() {
		return accountToNumber;
	}
	public void setAccountToNumber(Integer accountToNumber) {
		this.accountToNumber = accountToNumber;
	}	
	
	public DepositTransaction getDepositTransaction(){
		return new DepositTransaction (this.getAccountToNumber(), this.getTransactionAmount());
	}
	
	public WithdrawTransaction getWithdrawTransaction(){
		return new WithdrawTransaction (this.getAccountNumber(), this.getTransactionAmount());
		
	}
}
