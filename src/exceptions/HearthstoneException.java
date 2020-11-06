package exceptions;

public abstract class HearthstoneException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public HearthstoneException() {super();}
	
	
	public HearthstoneException(String s) {
		super(s);
	}
}
