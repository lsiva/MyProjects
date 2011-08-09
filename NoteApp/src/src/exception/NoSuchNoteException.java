package src.exception;

public class NoSuchNoteException extends Exception {	
	
	private static final long serialVersionUID = -2476609551649480595L;
	

	public NoSuchNoteException(){
		super();
	}
	
	public NoSuchNoteException(String message){
		super(message);
	}		
}
