package model.heroes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;

import engine.ActionValidator;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.*;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.minions.MinionListener;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;

public abstract class Hero implements MinionListener{

	private String name;
	private int currentHP = 30;
	private boolean heroPowerUsed;
	private int totalManaCrystals;
	private int currentManaCrystals;
	private ArrayList<Card> deck;
	private ArrayList<Minion> field;
	private ArrayList<Card> hand;
	private int fatigueDamage ;
	private HeroListener listener;
	private ActionValidator validator;
	private ArrayList <JLabel> heroImagesDeck = new ArrayList<>();
	private ArrayList <JLabel> heroImagesHand = new ArrayList<>();
	
	public ArrayList<JLabel> getHeroImagesHand() {
		return heroImagesHand;
	}

	public ArrayList<JLabel> getHeroImagesDeck() {
		return heroImagesDeck;
	}

	public Hero() {}
	
	public Hero(String name) throws IOException, CloneNotSupportedException {
		this.name = name;
		this.currentHP = 30;
		this.heroPowerUsed = false;
		this.totalManaCrystals = 0;
		this.currentManaCrystals = 0 ;
		this.fatigueDamage = 0;
		this.deck = new ArrayList<>();
		this.field = new ArrayList<>();
		this.hand = new ArrayList<>();
		this.buildDeck();
	}
	

	public String getName() {
		return name;
	}
	
	public ArrayList<Card> getDeck(){
		return deck;
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public int getCurrentHP() {
		return currentHP;
	}
	public void setCurrentHP(int currentHP) {
		if(currentHP > 30) this.currentHP = 30;
		else this.currentHP = currentHP;
		
		if(currentHP <= 0)
		{
			this.currentHP = 0;
			listener.onHeroDeath();
		}
			
	}
	public int getFatigueDamage() {
		return fatigueDamage;
	}

	public boolean isHeroPowerUsed() {
		return heroPowerUsed;
	}
	public void setHeroPowerUsed(boolean heroPowerUsed) {
		this.heroPowerUsed = heroPowerUsed;
	}
	public int getTotalManaCrystals() {
		return totalManaCrystals;
	}
	public void setTotalManaCrystals(int totalManaCrystals) {
		if(totalManaCrystals >= 10)
			this.totalManaCrystals = 10;
		else
			this.totalManaCrystals = totalManaCrystals;
		
	}
	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}
	
	public void setCurrentManaCrystals(int currentManaCrystals) {

		if(currentManaCrystals >= 10)
			this.currentManaCrystals = 10;
		else
			this.currentManaCrystals = currentManaCrystals;
		
	}
	public ArrayList<Minion> getField() {
		return field;
	}
	
	
	
	public HeroListener getListener() {
		return listener;
	}

	public void setListener(HeroListener listener) {
		this.listener = listener;
	}


	public void setValidator(ActionValidator validator) {
		this.validator = validator;
	}

	public final static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException{
		ArrayList<Minion> res = new ArrayList<>();
		String currentLine = "";
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
			String[]data = currentLine.split(",");
			String name = data[0];
			int manacost = Integer.parseInt(data[1]);
			int attack = Integer.parseInt(data[3]);
			int maxhp = Integer.parseInt(data[4]);
			boolean taunt = Boolean.parseBoolean(data[5]);
			boolean divine = Boolean.parseBoolean(data[6]);
			boolean charge = Boolean.parseBoolean(data[7]);
			char r = data[2].charAt(0);
			Rarity rarity = null;
			switch(r) {
			case 'b' : rarity = Rarity.BASIC ;  break;
			case 'c' : rarity = Rarity.COMMON ; break;
			case 'r' : rarity = Rarity.RARE ;   break;
			case 'e' : rarity = Rarity.EPIC;    break;
			case 'l' : rarity = Rarity.LEGENDARY; break;
			}
			if(name.equals("Icehowl")) {
				Icehowl i = new Icehowl();
				res.add(i);
				continue;
			}
			Minion m = new Minion (name,manacost,rarity,attack,maxhp,taunt,divine,charge);
			res.add(m);
		}
		br.close();
		return res;	
	}
		
	public final static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions , int count) throws CloneNotSupportedException{
		ArrayList<Minion> temp = new ArrayList<>();
		ArrayList<Minion> res = new ArrayList<>();
		for (int i = 0 ; i < minions.size();i++) {
			if (minions.get(i).getRarity()== Rarity.LEGENDARY)
				temp.add(minions.get(i));
			else
			{
				temp.add(minions.get(i));
				temp.add(minions.get(i));
			}
		}
		Collections.shuffle(temp);
		
		while(count>0) {
			
			if(temp.get(0) instanceof Icehowl)
			{
				Icehowl newMinion = new Icehowl();
				res.add(newMinion);
				count--;
			}	
			else
			{
				Minion newMinion = (Minion)temp.get(0).clone();
				res.add(newMinion);
				count--;				
			}
			
			temp.remove(0);	
		}
		return res;
	}
	public abstract void buildDeck () throws IOException, CloneNotSupportedException;
	
	public void onMinionDeath(Minion m) {
		this.field.remove(m);
	}
	public void useHeroPower() throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException{
		validator.validateUsingHeroPower(this);
		validator.validateTurn(this);
		this.heroPowerUsed = true;
		this.setCurrentManaCrystals(this.getCurrentManaCrystals() - 2);	
	}
	public void playMinion(Minion m) throws NotYourTurnException,NotEnoughManaException, FullFieldException{
		validator.validateTurn(this);
		validator.validateManaCost(m);
		validator.validatePlayingMinion(m);
		this.getField().add(m);
		int indexOfm = this.getHand().indexOf(m);
		this.getHand().remove(m);
		this.getHeroImagesHand().remove(indexOfm);
		this.setCurrentManaCrystals(this.getCurrentManaCrystals() - m.getManaCost());
	}
	public void attackWithMinion(Minion attacker, Minion target) throws CannotAttackException, NotYourTurnException, TauntBypassException,InvalidTargetException, NotSummonedException{
		validator.validateAttack(attacker, target);
		validator.validateTurn(this);
		attacker.attack(target);
	}
	public void attackWithMinion(Minion attacker, Hero target) throws CannotAttackException, NotYourTurnException, TauntBypassException,NotSummonedException, InvalidTargetException{	
		validator.validateAttack(attacker, target);
		validator.validateTurn(this);
		attacker.attack(target);
	}
	public void castSpell(FieldSpell s) throws NotYourTurnException,NotEnoughManaException{
		validator.validateTurn(this);
		validator.validateManaCost((Card)s);
		boolean found = false;
		if(this instanceof Mage)
		{
			for(int i=0 ; i<this.getField().size() ; i++)
			{
				if(this.getField().get(i).getName().equals("Kalycgos"))
				{
					found = true;
					break;
				}
			}
			if(found) 
				((Card)s).setManaCost(((Card)s).getManaCost() - 4);
		}
		s.performAction(this.getField());
		int index = this.getHand().indexOf((Card)s);
		this.getHand().remove((Card)s);
		this.getHeroImagesHand().remove(index);
		this.currentManaCrystals -= ((Card)s).getManaCost() ; 	
	}
	public void castSpell(AOESpell s, ArrayList<Minion >oppField) throws	NotYourTurnException, NotEnoughManaException{
		validator.validateTurn(this);
		validator.validateManaCost((Card)s);
		boolean found = false;
		if(this instanceof Mage)
		{
			for(int i=0 ; i<this.getField().size() ; i++)
			{
				if(this.getField().get(i).getName().equals("Kalycgos"))
				{
					found = true;
					break;
				}
			}
			if(found) 
				((Card)s).setManaCost(((Card)s).getManaCost() - 4);
		}
		s.performAction(oppField, this.getField());
		int index = this.getHand().indexOf((Card)s);
		this.getHand().remove((Card)s);
		this.getHeroImagesHand().remove(index);
		this.currentManaCrystals -= ((Card)s).getManaCost() ; 	
	}
	public void castSpell(MinionTargetSpell s, Minion m) throws NotYourTurnException,NotEnoughManaException, InvalidTargetException{
		validator.validateTurn(this);
		validator.validateManaCost((Card)s);
		boolean found = false;
		if(this instanceof Mage)
		{
			for(int i=0 ; i<this.getField().size() ; i++)
			{
				if(this.getField().get(i).getName().equals("Kalycgos"))
				{
					found = true;
					break;
				}
			}
			if(found) 
				((Card)s).setManaCost(((Card)s).getManaCost() - 4);
		}
		int index = this.getHand().indexOf((Card)s);
		this.getHand().remove((Card)s);
		this.getHeroImagesHand().remove(index);
		s.performAction(m);
		this.currentManaCrystals -= ((Card)s).getManaCost() ; 	
	}	
	public void castSpell(HeroTargetSpell s, Hero h) throws NotYourTurnException,NotEnoughManaException{
		validator.validateTurn(this);
		validator.validateManaCost((Card)s);
		boolean found = false;
		if(this instanceof Mage)
		{
			for(int i=0 ; i<this.getField().size() ; i++)
			{
				if(this.getField().get(i).getName().equals("Kalycgos"))
				{
					found = true;
					break;
				}
			}
			if(found) 
				((Card)s).setManaCost(((Card)s).getManaCost() - 4);
		}
		s.performAction(h);
		int index = this.getHand().indexOf((Card)s);
		this.getHand().remove((Card)s);
		this.getHeroImagesHand().remove(index);
		this.currentManaCrystals -= ((Card)s).getManaCost() ; 
	}
	public void castSpell(LeechingSpell s, Minion m) throws NotYourTurnException,NotEnoughManaException{
		validator.validateTurn(this);
		validator.validateManaCost((Card)s);
		boolean found = false;
		if(this instanceof Mage)
		{
			for(int i=0 ; i<this.getField().size() ; i++)
			{
				if(this.getField().get(i).getName().equals("Kalycgos"))
				{
					found = true;
					break;
				}
			}
			if(found) 
				((Card)s).setManaCost(((Card)s).getManaCost() - 4);
		}
		int index = this.getHand().indexOf((Card)s);
		this.getHand().remove((Card)s);
		this.getHeroImagesHand().remove(index);
		int x = s.performAction(m);
		this.currentHP += x ;
		this.currentManaCrystals -= ((Card)s).getManaCost() ; 
	}
	
	public void endTurn() throws FullHandException, CloneNotSupportedException{
		listener.endTurn();
	}
	
	public Card drawCard() throws FullHandException,CloneNotSupportedException{
		
		if(this.getDeck().isEmpty())
		{	
			this.setCurrentHP(this.currentHP - this.fatigueDamage);
			this.fatigueDamage++ ;
			return null;
		}
			Card c = this.getDeck().get(0);
			JLabel j = this.getHeroImagesDeck().get(0);
			if(this.getHand().size() == 10) {
				this.getHeroImagesDeck().remove(0);
				throw new FullHandException("Cannot draw a card due to a full hand",this.getDeck().remove(0));
			}
				for(int i = 0 ; i<this.getField().size() ; i++)
				{
					if(this.getField().get(i).getName().equals("Chromaggus"))
					{
						this.getHand().add(c);
						playSound1("sounds/addCardtoHand.wav");
						this.getHeroImagesHand().add(j);
						if(this.getHand().size() < 10) {
							this.getHand().add((Card)c.clone());
							JLabel j1 = this.getHeroImagesDeck().get(0);
							this.getHeroImagesHand().add(j1);
						}
						
							
						this.getDeck().remove(c);
						this.getHeroImagesDeck().remove(j);
						return c;
					}		
				}
				this.getHand().add(c);
				playSound1("sounds/addCardtoHand.wav");
				this.getHeroImagesHand().add(j);
				this.getDeck().remove(c);
				this.getHeroImagesDeck().remove(j);
				if(this.getDeck().isEmpty())
					this.fatigueDamage++ ;
				return c;
	}	
	public static void playSound1(String filePath)
	{
		Clip c;
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		    c = AudioSystem.getClip();
			c.open(audio);
			c.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1 ) {System.out.println("Exception");}
			
			
		
	}
	
}
