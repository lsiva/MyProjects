package bankApp.response;

import bankApp.Account;


public class AccountOpenSuccessResponse extends SuccessResponse {
	
	Account account;
	
	public AccountOpenSuccessResponse(Account acct){
		account = acct;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	

}
