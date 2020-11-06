package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell{

	public Flamestrike() {
		super("Flamestrike" , 7 , Rarity.BASIC);
	}

	
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField) {
		
	
		
		
		for(int i = 0 ; i < oppField.size() ; i++)
		{
			int preSize = oppField.size();
			if(oppField.get(i).isDivine())
			{
				oppField.get(i).setDivine(false);
			}
			else
			{
				oppField.get(i).setCurrentHP(oppField.get(i).getCurrentHP() - 4);	
				int currSize = oppField.size();
				if(preSize > currSize )
					i--;
			}
		}
	}
}
