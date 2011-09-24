package bankApp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import bankApp.Account;
import bankApp.Amount;
import bankApp.Bank;
import bankApp.atm.BankAtm;
import bankApp.response.AccountOpenSuccessResponse;
import bankApp.response.BalanceResponse;
import bankApp.response.FailureResponse;
import bankApp.response.Response;

public class TestBankApp {
	Amount zeroAmount = new Amount(0);
	Amount bigAmount = new Amount(25000);
	Amount equalAmount = new Amount(25000);
	Amount smallAmount = new Amount(25);

	public static Account zeroBalanceAccount = null;
	public static Account richAccount = null;
	public static Account popperAccount = null;

	@BeforeClass
	public static void setUpBeforeClass() {
		zeroBalanceAccount = ((AccountOpenSuccessResponse) Bank.openAccount())
				.getAccount();
		richAccount = ((AccountOpenSuccessResponse) Bank.openAccount())
				.getAccount();
		popperAccount = ((AccountOpenSuccessResponse) Bank.openAccount())
				.getAccount();
	}

	@Test
	public void testEqualAmount() {
		assertTrue(bigAmount.equals(equalAmount));
		assertFalse(bigAmount.equals(richAccount));
	}

	@Test
	public void testAmountCompare() {
		assertEquals(bigAmount.compareTo(equalAmount), 0);
		assertEquals(bigAmount.compareTo(smallAmount), 1);
		assertEquals(smallAmount.compareTo(bigAmount), -1);

	}

	@Test(expected = Exception.class)
	public void testAmountCompareException() {
		assertEquals(smallAmount.compareTo(richAccount), -1);

	}

	@Test
	public void testAccountExists() {
		assertTrue(Bank.checkAccountNumber(richAccount.getAccountNumber()));
		assertFalse(Bank.checkAccountNumber(new Integer(9999)));
	}

	@Test
	public void testDeposit() {
		new BankAtm().deposit(richAccount.getAccountNumber(), bigAmount);
		assertEquals(Bank.getAccount(richAccount.getAccountNumber()).getBalance(), bigAmount);
		new BankAtm().deposit(popperAccount.getAccountNumber(), smallAmount);
		assertEquals(Bank.getAccount(popperAccount.getAccountNumber()).getBalance(), smallAmount);

	}

	@Test
	public void testBalanceInquiry() {
		AccountOpenSuccessResponse response = (AccountOpenSuccessResponse) Bank
				.openAccount();
		Integer newAccountNumber = response.getAccount().getAccountNumber();
		Response balanceResponse = new BankAtm()
				.balanceInquiry(newAccountNumber);
		assertEquals(((BalanceResponse) balanceResponse).getBalance(),
				zeroAmount);
	}

	@Test
	public void testBalanceInquiryFailure() {
		Response balanceResponse = new BankAtm().balanceInquiry(9999);
		assertTrue(balanceResponse.getClass().getName().equals(
				"bankApp.response.FailureResponse"));
	}

	@Test
	public void testOverdraft() {
		Response response = new BankAtm().withdraw(popperAccount
				.getAccountNumber(), bigAmount);
		System.out.println(((FailureResponse)response).getErrorMessage());
		assertTrue(response.getClass().getName().equals(
				"bankApp.response.FailureResponse"));
		assertEquals(Bank.getAccount(popperAccount.getAccountNumber())
				.getBalance(), smallAmount);
	}
	
	@Test
	public void testAccountClosure(){
		Integer zeroBalanceAcctNumber = zeroBalanceAccount.getAccountNumber();	
		Bank.closeAccount(zeroBalanceAccount.getAccountNumber());
		assertFalse(Bank.checkAccountNumber(zeroBalanceAcctNumber));
		
		//transactions against a closed account fail
		Response balanceResponse = new BankAtm().balanceInquiry(zeroBalanceAcctNumber);
		assertTrue(balanceResponse.getClass().getName().equals(
				"bankApp.response.FailureResponse"));
		
	}
	
	@Test
	public void testAccountClosureFailure(){
		Integer popperAcctNumber = popperAccount.getAccountNumber();	
		Bank.closeAccount(popperAccount.getAccountNumber());
		assertTrue(Bank.checkAccountNumber(popperAcctNumber));
	}
	
	@Test
	public void testAccountTransfer(){
		new BankAtm().acctTransfer(richAccount.getAccountNumber(), popperAccount.getAccountNumber(), new Amount(10000));
		assertEquals(Bank.getAccount(popperAccount.getAccountNumber())
				.getBalance(), new Amount(10025));
		assertEquals(Bank.getAccount(richAccount.getAccountNumber())
				.getBalance(), new Amount(15000));
	}
	
	@Test
	public void testAccountTransferFailure(){
		System.out.println(Bank.getAccount(popperAccount.getAccountNumber())
				.getBalance().getValue());
		System.out.println(Bank.getAccount(richAccount.getAccountNumber())
				.getBalance().getValue());
		Response response = new BankAtm().acctTransfer(popperAccount.getAccountNumber(), richAccount.getAccountNumber(), new Amount(20000));
		assertTrue(response.getClass().getName().equals(
		"bankApp.response.FailureResponse"));
		System.out.println(Bank.getAccount(popperAccount.getAccountNumber())
				.getBalance().getValue());
		System.out.println(Bank.getAccount(richAccount.getAccountNumber())
				.getBalance().getValue());
		assertEquals(Bank.getAccount(popperAccount.getAccountNumber())
				.getBalance(), new Amount(10025));
		assertEquals(Bank.getAccount(richAccount.getAccountNumber())
				.getBalance(), new Amount(15000));
	}
}
