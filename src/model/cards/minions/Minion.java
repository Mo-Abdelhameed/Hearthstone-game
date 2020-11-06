package model.cards.minions;
import exceptions.InvalidTargetException;
import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;

public class Minion extends Card implements Cloneable{
	
	private int attack , maxHP , currentHP ;
	private boolean taunt , divine , sleeping , attacked ;
	private MinionListener listener;
	
	
	public Minion() {} 

	public Minion(String name,int manaCost,Rarity rarity, int attack,int maxHP,boolean taunt,boolean divine,boolean charge) {

		super(name,manaCost,rarity);
		this.attack = attack;
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
		this.sleeping = !charge;
		this.attacked = false;
		
	}
	
	//-------------------- Setters && Getters ------------------//

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		if(attack < 0)
			this.attack = 0;
		else
			this.attack = attack;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP){
		if(currentHP <= this.maxHP)
			this.currentHP = currentHP;
		else
			this.currentHP = maxHP;
		if(currentHP <= 0)
			listener.onMinionDeath(this);
	}

	public boolean isTaunt() {
		return taunt;
	}

	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}

	public boolean isDivine() {
		return divine;
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}
	
	
	
	public void setListener(MinionListener listener) {
		this.listener = listener;
	}

	public boolean equals(Object o) {
		if(o instanceof Minion)
		{
			Minion m = (Minion) o ;
			return super.equals(m) && this.attack == m.attack && this.maxHP == m.maxHP && this.currentHP == m.currentHP
				&& this.taunt == m.taunt && this.divine == m.divine && this.sleeping == m.sleeping
				&& this.attacked == m.attacked ;
		}
		return false;
	}
	
	public void attack(Minion target) {
		
		
		if(target.isDivine() && this.divine)
		{
			if(this.attack > 0)								
				target.setDivine(false);
			if(target.attack > 0)
				this.setDivine(false);
		}
		
		if(!target.isDivine() && !this.divine)
		{
			
			//this.currentHP -= target.attack;
			this.setCurrentHP(this.getCurrentHP() - target.attack);
			target.setCurrentHP(target.getCurrentHP() - this.attack);
			//target.currentHP -= this.attack;
		}
		
		if(target.isDivine() && !this.divine)
		{
			if(this.attack > 0)
				target.setDivine(false);
			//this.currentHP -= target.attack;
			this.setCurrentHP(this.getCurrentHP() - target.attack);
		}
		
		
		if(!target.isDivine() && this.divine)
		{
			if(target.attack > 0)
				this.setDivine(false);
			//target.currentHP -= this.attack;
			target.setCurrentHP(target.getCurrentHP() - this.attack);
		}
		this.attacked = true;
		
		
	}

	public void attack(Hero target) throws InvalidTargetException{
		
		if(this.getName().equals("Icehowl")) throw new InvalidTargetException();
		target.setCurrentHP(target.getCurrentHP() - this.getAttack());
		this.attacked = true;
		
	}
	
	
	
	public static void main(String[] args) {
		
		Card c = new Minion();
		Minion m = new Minion();
		c = m;
		m = (Minion)c;	
	}
	
	
	
	
}
