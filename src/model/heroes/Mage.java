package model.heroes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Mage extends Hero{

	public Mage() throws IOException , CloneNotSupportedException {
		super("Jaina Proudmoore");
		this.buildDeck();
	}
	
	public  void buildDeck () throws IOException , CloneNotSupportedException{
		ArrayList<Card> res = new ArrayList<>();
		JLabel hunter1 ;
		ArrayList<Minion> tmp2 = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv") , 13);
		Spell f1 = new Flamestrike();
		Spell f2 = new Flamestrike();
		Spell p1 = new Polymorph();
		Spell p2 = new Polymorph();
		Spell b1 = new Pyroblast();
		Spell b2 = new Pyroblast();
		Card m = new Minion("Kalycgos" , 10 , Rarity.LEGENDARY ,4 , 12 , false , false , false);
		res.add(m);
		res.add(f1); res.add(f2); res.add(p1); res.add(p2); res.add(b1) ; res.add(b2);
		res.addAll(tmp2);
		Collections.shuffle(res);
		this.getDeck().addAll(res);
		for(int i = 0 ; i < this.getDeck().size() ; i++)
		{
			if(this.getDeck().get(i) instanceof Minion)
			{
				Minion newm = (Minion) this.getDeck().get(i);
				newm.setListener(this);
			}
			if(this.getDeck().get(i).getName().equals("Kalycgos"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Kalygos.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Goldshire Footman"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Goldshire Footman.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Stonetusk Boar"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Stonetusk.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Bloodfen Raptor"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Bloodfen.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Frostwolf Grunt"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Frostwolf.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Wolfrider"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Wolfrider.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Chilwind Yeti"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Chilwind.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Boulderfist Ogre"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Boulderfist.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Core Hound"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Core Hound.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Argent Commander"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Argent Commander.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Sunwalker"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Sunwalker.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Chromaggus"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Chromaggus.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("The LichKing"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/The LichKing.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Icehowl"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/icehowl.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i).getName().equals("Colossus of the Moon"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Colossus of the Moon.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i) instanceof Flamestrike)
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Flamestrike.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i) instanceof Polymorph)
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Polymorph.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i) instanceof Pyroblast)
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Pyroblast.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			
				
		}
		
		
		
	}
	
	public void useHeroPower(Hero target) throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException{
		super.useHeroPower();
		
		target.setCurrentHP(target.getCurrentHP() - 1);
		
	}
	
	public void useHeroPower(Minion target) throws NotEnoughManaException,	HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		if(!target.isDivine())
			target.setCurrentHP(target.getCurrentHP() - 1);
		else
			target.setDivine(false);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
}
