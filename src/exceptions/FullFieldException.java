package exceptions;

public class FullFieldException extends HearthstoneException{
	private static final long serialVersionUID = 1L;
	public FullFieldException() {}
	public FullFieldException(String s) {
		super(s);
	}
}
