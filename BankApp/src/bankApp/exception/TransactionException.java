package bankApp.exception;

public class TransactionException extends Exception {
	private static final long serialVersionUID = 1L;

	public TransactionException() {
		super("unknown");

	}

	public TransactionException(String errorMessage) {
		super(errorMessage);

	}

	public TransactionException(Throwable cause) {
		super(cause);
	}

	public TransactionException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
