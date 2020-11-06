package panels;

import java.awt.*;
import javax.swing.*;


public class CardPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayerPanel player = new PlayerPanel();
	//private PlayerPanel oppPlayer = new PlayerPanel();
	private HandPanel hand = new HandPanel();
	public CardPanel(String s)
	{
		if(s.equals("current")) {
		
		this.setPreferredSize(new Dimension(300, 428));
		
		this.setLayout(new FlowLayout());
		
		this.add(player);
		
		
		this.add(hand);
	
		}
		if(s.equals("opponent"))
		{
			this.setPreferredSize(new Dimension(300, 428));
			//player.setPreferredSize(new Dimension(300,this.getHeight()));
			this.setLayout(new FlowLayout());
		//	oppPlayer.setBackground(Color.RED);
			this.add(player);
			
			//hand.setBackground(Color.BLUE);
			this.add(hand);
			player.getHeroPower().setVisible(false);
			player.getEndTurn().setVisible(false);
		}
//		
		
		
		
	}
	public void setPlayer(PlayerPanel player) {
		this.player = player;
	}
	public void setHand(HandPanel hand) {
		this.hand = hand;
	}
	public PlayerPanel getPlayer() {
		return player;
	}
//	public PlayerPanel getOppPlayer() {
//		return oppPlayer;
//	}
	public HandPanel getHand() {
		return hand;
	}
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setVisible(true);
	    CardPanel n = 	new CardPanel("current");
	    n.hand.setBackground(Color.BLACK);
		j.add(n);
	}
	
	
	
	
}
