package bankApp;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import bankApp.common.BankAppConstants;
import bankApp.exception.TransactionException;
import bankApp.response.AccountOpenSuccessResponse;
import bankApp.response.BalanceResponse;
import bankApp.response.FailureResponse;
import bankApp.response.Response;
import bankApp.response.SuccessResponse;
import bankApp.transaction.AccountTransferTransaction;
import bankApp.transaction.BalanceInquiryTransaction;
import bankApp.transaction.DepositTransaction;
import bankApp.transaction.Transaction;
import bankApp.transaction.WithdrawTransaction;

public class Bank {

	private static AtomicInteger accountNumber = new AtomicInteger(0);

	public static Integer getAccountNumber() {
		return new Integer(accountNumber.getAndIncrement());
	}

	private static HashMap<Integer, Account> accounts = new HashMap<Integer, Account>();

	public static Response processTransaction(Transaction transaction) {
		try {
			switch (transaction.getTransactionMessage()) {
			case DEPOSIT:
				Account depositAccount = deposit((DepositTransaction) transaction);
				finalizeTransaction(transaction.getAccountNumber(),
						depositAccount);
			case WITHDRAWL:
				Account withdrawAccount = withdraw((WithdrawTransaction) transaction);
				finalizeTransaction(transaction.getAccountNumber(),
						withdrawAccount);
			case BALANCE_INQUIRY:
				return new BalanceResponse(
						balanceInquiry((BalanceInquiryTransaction) transaction));
			case ACCOUNT_TRANSFER:
				AccountTransferTransaction at = (AccountTransferTransaction) transaction;
				Account toAccount = deposit(at.getDepositTransaction());
				Account fromAccount = withdraw(at.getWithdrawTransaction());
				finalizeTransaction(at.getAccountToNumber(), toAccount);
				finalizeTransaction(at.getAccountNumber(), fromAccount);
			}
		} catch (Exception e) {
			return new FailureResponse(e.getMessage());
		}

		return new FailureResponse("Unrecognized transaction");
	}

	private static Account deposit(DepositTransaction transaction)
			throws TransactionException {
		transaction.validateTransaction(transaction);
		int accountBalance = accounts.get(transaction.getAccountNumber())
				.getBalance().getValue();
		Amount afterDeposit = new Amount(accountBalance);
		afterDeposit.add(transaction.getTransactionAmount().getValue());
		return new Account(transaction.getAccountNumber(), afterDeposit);
	}

	private static Account withdraw(WithdrawTransaction transaction)
			throws TransactionException {
		transaction.validateTransaction(transaction);
		int accountBalance = accounts.get(transaction.getAccountNumber())
				.getBalance().getValue();
		Amount afterWithdrawl = new Amount(accountBalance);
		afterWithdrawl.subtract(transaction.getTransactionAmount().getValue());
		return new Account(transaction.getAccountNumber(), afterWithdrawl);
	}

	private static Amount balanceInquiry(BalanceInquiryTransaction transaction)
			throws TransactionException {
		transaction.validateTransaction(transaction);
		return accounts.get(transaction.getAccountNumber()).getBalance();

	}

	public static Response openAccount() {
		Integer actNumber = getAccountNumber();
		Account account = new Account(actNumber, new Amount(0));
		accounts.put(actNumber, account);
		return new AccountOpenSuccessResponse(account);
	}

	public static Response closeAccount(Integer accountNumber) {
		Response response = null;
		if (getAccount(accountNumber).isZeroBalance()) {
			accounts.remove(accountNumber);
			response = new SuccessResponse();
		} else {
			response = new FailureResponse(BankAppConstants.NO_ZERO_BALANCE);
		}
		return response;
	}

	public static boolean checkAccountNumber(Integer accountNumber) {
		return accounts.containsKey(accountNumber);
	}

	public static Account getAccount(Integer accountNumber) {
		return accounts.get(accountNumber);
	}

	private static Response finalizeTransaction(Integer accountNumber,
			Account account) {
		accounts.put(accountNumber, account);
		return new SuccessResponse();
	}
}
