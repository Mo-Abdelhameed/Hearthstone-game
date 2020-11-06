package exceptions;
import model.cards.*;

public class FullHandException extends HearthstoneException{
	private static final long serialVersionUID = 1L;
	private Card burned ;
	
	public FullHandException(Card b) {
		burned = b ;
	}
	public FullHandException(String s , Card b) {
		super(s);
		burned = b ;
	}
	public Card getBurned() {
		return burned;
	}
	
	
	
}
