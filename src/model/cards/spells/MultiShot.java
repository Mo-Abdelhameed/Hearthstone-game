package model.cards.spells;

import java.util.ArrayList;
import java.util.Random;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell{

	public MultiShot() {
		super("Multi-Shot" , 4 , Rarity.BASIC);
	}
	
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField) {
		
		if(oppField.isEmpty())
			return;
		if(oppField.size() == 1)
		{
			if(!oppField.get(0).isDivine())
				oppField.get(0).setCurrentHP(oppField.get(0).getCurrentHP() - 3);
			else
				oppField.get(0).setDivine(false);
			return;
		}														
		ArrayList<Minion> copy = new ArrayList<>();
		for(int i = 0 ; i<oppField.size() ; i++)
		{
			copy.add(oppField.get(i));
		}
		
		Random rn = new Random();
		int x = rn.nextInt(oppField.size() - 1);
		copy.remove(x);
		int y ;
		if(copy.size() != 1)
			 y = rn.nextInt(copy.size() - 1);
		else
			y = 0;
		if(!oppField.get(x).isDivine())
			oppField.get(x).setCurrentHP(oppField.get(x).getCurrentHP() - 3);
		else
			oppField.get(x).setDivine(false);
		
		if(!copy.get(y).isDivine())
			copy.get(y).setCurrentHP(copy.get(y).getCurrentHP() - 3);
		else
			copy.get(y).setDivine(false);
	}
}
