package src.exception;

public class NoSuchTagException extends Exception {

		private static final long serialVersionUID = 1L;
		
		public NoSuchTagException(){
			super();
		}
		
		public NoSuchTagException(String message){
			super(message);
		}		
		

}	
