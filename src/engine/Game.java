package engine;

import java.util.ArrayList;
import java.util.Random;
import exceptions.*;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;

public class Game implements ActionValidator , HeroListener  {

	private Hero firstHero ;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	private GameListener listener;
	
	public Game() {}
	
	public Game(Hero p1, Hero p2)throws CloneNotSupportedException , FullHandException { 
		
		firstHero = p1;
		secondHero = p2;
		Random r = new Random();
		int start = r.nextInt(2);
		if (start == 0) {
			currentHero = firstHero;
			opponent = secondHero;
		}
		else {
			currentHero = secondHero;
			opponent = firstHero;
		}
		currentHero.setTotalManaCrystals(1);
		currentHero.setCurrentManaCrystals(1);
		currentHero.setListener(this);
		currentHero.setValidator(this);
		opponent.setListener(this);
		opponent.setValidator(this);
		currentHero.drawCard(); currentHero.drawCard(); currentHero.drawCard();
		opponent.drawCard(); opponent.drawCard(); opponent.drawCard(); opponent.drawCard();
	}
	public Hero getCurrentHero() {
		return currentHero;
	}
	public Hero getOpponent() {
		return opponent;
	}	
	public void setListener(GameListener listener) {
		this.listener = listener;
	}
	public void validateTurn(Hero user) throws NotYourTurnException{
		if(user != this.currentHero) 
			throw new NotYourTurnException();
	}
	public void validateAttack(Minion attacker,Minion target) throws
	CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException	
	{	
		boolean taunt = false;
		ArrayList<Minion> oppField =  opponent.getField();
		for(int i = 0 ; i<oppField.size() ; i++)
		{
			if(oppField.get(i).isTaunt()) taunt = true;
		}
		if(attacker.getAttack() == 0) throw new CannotAttackException("The attacker has 0 attack points");
		if(currentHero.getField().contains(attacker) && currentHero.getField().contains(target))throw new InvalidTargetException("You cannot attack this targer.");
		if(attacker.isSleeping() || attacker.isAttacked()) throw new CannotAttackException("This minion cannot attack at this turn");
		if(!currentHero.getField().contains(attacker))throw new NotSummonedException("This minion is not summoned");
		if(!opponent.getField().contains(target))throw new NotSummonedException("The target is not in the field");
		if(taunt && !target.isTaunt()) throw new TauntBypassException("Cannot attack this minion while a taunt minion is in the field");
		
		
		
			
	}
	
	
	public void validateAttack(Minion attacker,Hero target) throws	CannotAttackException, NotSummonedException, TauntBypassException,
	InvalidTargetException{
		
		boolean taunt = false;
		ArrayList<Minion> oppField =  opponent.getField();
		for(int i = 0 ; i<oppField.size() ; i++)
		{
			if(oppField.get(i).isTaunt()) taunt = true;
		}
		if(attacker.getAttack() == 0) throw new CannotAttackException("The attacker has 0 attack points");
		if(attacker.isSleeping() || attacker.isAttacked()) throw new CannotAttackException("This minion cannot attack at this turn");
		if(!currentHero.getField().contains(attacker))throw new NotSummonedException("This minion is not summoned");
		
		if(taunt) throw new TauntBypassException("Cannot attack the Hero while a taunt minion is in the field");
		if(attacker.getName().equals("Icehowl")) throw new InvalidTargetException("Icehowl cannot attack heros");
		if(target.getField().contains(attacker))throw new InvalidTargetException("You cannot attack this target");
	}
	public void validateManaCost(Card card) throws NotEnoughManaException{
		
		if(this.currentHero.getCurrentManaCrystals() < card.getManaCost())throw new NotEnoughManaException("You do not have enough mana crystals for this action");
	}
	
	public void validatePlayingMinion(Minion minion) throws FullFieldException{	
		if(currentHero.getField().size() == 7)throw new FullFieldException("Cannot play a minion while having a fullfield");
	}
	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException,HeroPowerAlreadyUsedException{
		if(hero.isHeroPowerUsed()) throw new HeroPowerAlreadyUsedException("The hero has already used his power");
		if(hero.getCurrentManaCrystals() < 2) throw new NotEnoughManaException("You do not have enough mana crystals for this action");	
	}
	public void onHeroDeath() {
		listener.onGameOver();
	}
	public void damageOpponent(int amount) {
		this.opponent.setCurrentHP(this.opponent.getCurrentHP() - amount);
	}
	public void endTurn() throws FullHandException, CloneNotSupportedException{
		Hero temp = this.currentHero;
		this.currentHero = this.opponent;
		this.opponent = temp;
		this.currentHero.setTotalManaCrystals(this.currentHero.getTotalManaCrystals() + 1);
		this.currentHero.setCurrentManaCrystals(this.currentHero.getTotalManaCrystals());
		this.currentHero.setHeroPowerUsed(false);
		for(int i = 0; i < this.currentHero.getField().size() ; i++) {	
			this.currentHero.getField().get(i).setSleeping(false);
			this.currentHero.getField().get(i).setAttacked(false);
		}
		this.currentHero.drawCard();	
	}	
}
