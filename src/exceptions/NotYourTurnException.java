package exceptions;

public class NotYourTurnException extends HearthstoneException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotYourTurnException() {}
	public NotYourTurnException(String s){
		super(s);
	}
}
