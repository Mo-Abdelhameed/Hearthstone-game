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
import model.cards.spells.LevelUp;
import model.cards.spells.SealOfChampions;
import model.cards.spells.Spell;

public class Paladin extends Hero{

	public Paladin() throws IOException , CloneNotSupportedException{
		super("Uther Lightbringer");
		this.buildDeck();
	}
	public  void buildDeck () throws IOException , CloneNotSupportedException{
		ArrayList<Card> res = new ArrayList<>();
		JLabel hunter1 ;
		ArrayList<Minion> tmp2 = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv") , 15);
		Spell k1 = new SealOfChampions();
		Spell k2 = new SealOfChampions();
		Spell multishot1 = new LevelUp();
		Spell multishot2 = new LevelUp();
		Card m = new Minion("Tirion Fordring" , 4 , Rarity.LEGENDARY ,6 , 6 , true , true , false);
		res.add(m);
		res.add(k1); res.add(k2); res.add(multishot1); res.add(multishot2);
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
			if(this.getDeck().get(i).getName().equals("Tirion Fordring"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Tirion Fordring.png"))));
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
			if(this.getDeck().get(i) instanceof SealOfChampions)
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Seal of Champions.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i) instanceof LevelUp)
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Level Up!.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			
		}
	}
	
	public void useHeroPower() throws NotEnoughManaException,	HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		Minion m = new Minion("Silver Hand Recruit" , 1 , Rarity.BASIC , 1 , 1 , false , false , false);
		if(this.getField().size() == 7) throw new FullFieldException("Sorry ! You have a full field");
		this.getField().add(m);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
