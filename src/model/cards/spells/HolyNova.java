package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell{

	public HolyNova() {
		
		super("Holy Nova" , 5 , Rarity.BASIC);
	}
	
	
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField) {
		
		for(int i = 0 ; i<oppField.size() ; i++) 
		{
			int preSize = oppField.size();
			if(oppField.get(i).isDivine())
				oppField.get(i).setDivine(false);
			else
				oppField.get(i).setCurrentHP(oppField.get(i).getCurrentHP() - 2);
			int newSize = oppField.size();
			if(preSize != newSize)
				i--;
		}
			
		
		for(int i = 0 ; i<curField.size() ; i++) 
			curField.get(i).setCurrentHP(curField.get(i).getCurrentHP() + 2);
	}
}

