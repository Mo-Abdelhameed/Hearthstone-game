package panels;

import java.awt.*;

import java.io.IOException;


import javax.swing.*;

public class BoardPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FieldPanel currPlayer = new FieldPanel();
	FieldPanel oppPlayer = new FieldPanel();
	CardPanel player1 = new CardPanel("current");
	CardPanel player2 = new CardPanel("opponent");
	boolean current = true;
	Image x;
	
	public BoardPanel(boolean current) throws IOException
	{
		if(current) {
		this.setLayout(new GridLayout(4,0));
		this.add(player2);
		this.add(oppPlayer);
		this.add(currPlayer);
		this.add(player1);
		oppPlayer.setBackground(Color.GRAY);
		currPlayer.setBackground(Color.GRAY);
		oppPlayer.setLayout(new FlowLayout());
		currPlayer.setLayout(new FlowLayout());
		this.current = !this.current;
		}
		else {
			this.setLayout(new GridLayout(4,0));
			this.add(player1);
			this.add(currPlayer);
			this.add(oppPlayer);
			this.add(player2);
			oppPlayer.setBackground(Color.GRAY);
			currPlayer.setBackground(Color.GRAY);
			oppPlayer.setLayout(new FlowLayout());
			currPlayer.setLayout(new FlowLayout());
			this.current = !this.current;
		}
		
	}
	
	public boolean isCurrent() {
		return current;
	}

	public void setCurrPlayer(FieldPanel currPlayer) {
		this.currPlayer = currPlayer;
	}

	public void setOppPlayer(FieldPanel oppPlayer) {
		this.oppPlayer = oppPlayer;
	}

	public void setPlayer1(CardPanel player1) {
		this.player1 = player1;
	}

	public void setPlayer2(CardPanel player2) {
		this.player2 = player2;
	}

	public FieldPanel getCurrPlayer() {
		return currPlayer;
	}

	public FieldPanel getOppPlayer() {
		return oppPlayer;
	}

	public CardPanel getPlayer1() {
		return player1;
	}

	public CardPanel getPlayer2() {
		return player2;
	}

	
}
