package panels;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class OpponentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel name = new JLabel();
	JLabel hp = new JLabel("hp: 30");
	
	JLabel manaCrystals = new JLabel("1/1");
	JLabel handCard = new JLabel("Cards in hand:");
	JLabel deckCards = new JLabel("Cards in deck:");
	
	public OpponentPanel() {
		
		this.setLayout(new GridLayout(6,0));
		this.add(name);
		this.add(hp);
		this.add(manaCrystals);
		this.add(handCard);
		this.add(deckCards);
		
	}
	
}
