package exceptions;

public class HeroPowerAlreadyUsedException extends HearthstoneException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HeroPowerAlreadyUsedException() {}
	public HeroPowerAlreadyUsedException(String s){
		super(s);
	}
}
