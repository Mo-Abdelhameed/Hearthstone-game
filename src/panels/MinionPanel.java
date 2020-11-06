package panels;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class MinionPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel name = new JLabel();
	private JLabel manacost= new JLabel();
	private JLabel rarity = new JLabel();
	private JLabel attack= new JLabel();
	private JLabel hp= new JLabel();
	private JLabel taunt= new JLabel();
	private JLabel charge= new JLabel();
	private JLabel divine= new JLabel();
	private JLabel sleeping= new JLabel();
	
	public MinionPanel(Minion m) 
	{
		this.setLayout(new GridLayout(9,0));
		this.name.setText(m.getName());
		this.manacost.setText("Mana Cost: "+m.getManaCost());
		this.rarity.setText("" + m.getRarity());
		this.attack.setText("Attack : " + m.getAttack());
		this.hp.setText("Current hp: " + m.getCurrentHP());
		if(m.isTaunt())
			this.taunt.setText("Taunt");
		else
			this.taunt.setText("Not taunt");
		if(!m.isSleeping())
			this.charge.setText("Charge");
		else
			this.charge.setText("Not charge");
		if(m.isDivine())
			this.divine.setText("Divine");
		else
			this.divine.setText("Not divine");
		if(m.isSleeping())
			this.sleeping.setText("Sleeping");
		else
			this.sleeping.setText("Awake");
		
		this.add(name);
		this.add(manacost);
		this.add(rarity);
		this.add(this.attack);
		this.add(this.hp);
		this.add(this.taunt);
		this.add(this.charge);
		this.add(this.divine);
		this.add(this.sleeping);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		JFrame j = new JFrame();
		j.setVisible(true);
		Minion m = new Minion("Lolo" , 10 , Rarity.LEGENDARY , 5 , 5 ,false,false,false);
		j.add(new MinionPanel(m));
		
	}

	

	public void setName(String name) {
		this.name.removeAll();
		this.name.setText(name);
	}

	public JLabel getManacost() {
		return manacost;
	}

	public void setManacost(String manacost) {
		this.manacost.removeAll();
		this.manacost.setText("Mana cost: " + manacost );
	}

	public JLabel getRarity() {
		return rarity;
	}

	public void setRarity(JLabel rarity) {
		this.rarity = rarity;
	}

	public JLabel getAttack() {
		return attack;
	}

	public void setAttack(JLabel attack) {
		this.attack = attack;
	}

	public JLabel getHp() {
		return hp;
	}

	public void setHp(JLabel hp) {
		this.hp = hp;
	}

	public JLabel getTaunt() {
		return taunt;
	}

	public void setTaunt(JLabel taunt) {
		this.taunt = taunt;
	}

	public JLabel getCharge() {
		return charge;
	}

	public void setCharge(JLabel charge) {
		this.charge = charge;
	}

	public JLabel getDivine() {
		return divine;
	}

	public void setDivine(String divine) {
		this.divine.removeAll();
		this.divine.setText(divine);
	}

	public JLabel getSleeping() {
		return sleeping;
	}

	public void setSleeping(String sleeping) {
		this.sleeping.removeAll();
		this.sleeping.setText(sleeping);;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
