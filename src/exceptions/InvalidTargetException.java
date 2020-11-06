package exceptions;

public class InvalidTargetException extends HearthstoneException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidTargetException() {}
	public InvalidTargetException(String s) {
		super(s);
	}
}
