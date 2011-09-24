package bankApp.response;

public class FailureResponse extends Response {
	String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public FailureResponse (String Message){
		errorMessage = Message;
	}
}
