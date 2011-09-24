package bankApp.response;

import bankApp.Amount;

public class BalanceResponse extends SuccessResponse {
	private Amount balance;

	public BalanceResponse(Amount amount) {
		balance = amount;
	}

	public Amount getBalance() {
		return balance;
	}

	public void setBalance(Amount balance) {
		this.balance = balance;
	}
}
