package bankApp;

public class Account {
	private Integer accountNumber;
	private Amount balance;
	
	public Account(Integer actNumber, Amount amount) {
		this.accountNumber = actNumber;
		this.balance = amount;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Amount getBalance() {
		return balance;
	}
	public void setBalance(Amount balance) {
		this.balance = balance;
	}
	
	public boolean isZeroBalance() {
		return (this.balance.equals(new Amount(0)));
	}
}
