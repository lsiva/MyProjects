package src.exception;

public class NoSuchLinkException extends Exception {	

	private static final long serialVersionUID = -4680941683277413843L;

	public NoSuchLinkException(){
		super();
	}
	
	public NoSuchLinkException(String message){
		super(message);
	}		
}
