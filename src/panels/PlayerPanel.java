package panels;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class PlayerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String hp;
	private JLabel nameOfplayer = new JLabel("Name:");
	private	JLabel hpOfplayer = new JLabel("Current HP : 30");
	private JLabel manaCrystals = new JLabel("Mana Crystals: 1/1");
	private JLabel deckCards = new JLabel("Cards in deck:");
	private JButton heroPower = new JButton("use hero power");
	private JButton endTurn = new JButton("end turn");
	Image x;
	public PlayerPanel() {
		
		this.setLayout(new GridLayout(6,0));
		this.add(nameOfplayer);
		this.add(hpOfplayer);
		this.add(manaCrystals);
		this.add(deckCards);
		this.add(heroPower);
		this.add(endTurn);
		Font font = new Font("Verdana", Font.TRUETYPE_FONT, 12);
		this.nameOfplayer.setFont(font);
		try {
			x = new ImageIcon(ImageIO.read(new File("images/PlayerWood.jpeg"))).getImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getName() {
		return name;
	}
	
	public String getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hpOfplayer.removeAll();
		this.hpOfplayer.setText("Current HP: " + hp);
	}
	
	public void setManaCrystals(int currMana , int totalMana) {
		this.manaCrystals.removeAll();
		this.manaCrystals.setText("Mana Crystals: "+currMana+"/"+totalMana);
		
	}
	public JLabel getDeckCards() {
		return deckCards;
	}
	public void setDeckCards(int cardsInDeck) {
		this.deckCards.removeAll();
		this.deckCards.setText("Cards in deck: " + cardsInDeck);
	}
	public JButton getHeroPower() {
		return heroPower;
	}
	public void setHeroPower(JButton heroPower) {
		this.heroPower = heroPower;
	}
	public JButton getEndTurn() {
		return endTurn;
	}
	public void setEndTurn(JButton endTurn) {
		this.endTurn = endTurn;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(x,0,0,this.getWidth(),this.getHeight(),this); 
		
		
	}
	
}
