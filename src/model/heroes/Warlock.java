package model.heroes;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.SiphonSoul;
import model.cards.spells.Spell;
import model.cards.spells.TwistingNether;

public class Warlock extends Hero{

	public Warlock() throws IOException,CloneNotSupportedException{
		super("Gul'dan");
		this.buildDeck();
	}
	
	
	public  void buildDeck () throws IOException,CloneNotSupportedException{
		ArrayList<Card> res = new ArrayList<>();
		JLabel hunter1 ;
		ArrayList<Minion> tmp2 = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv") , 13);
		Spell k1 = new CurseOfWeakness();
		Spell k2 = new CurseOfWeakness();
		Spell multishot1 = new SiphonSoul();
		Spell multishot2 = new SiphonSoul();
		Spell s1 = new TwistingNether();
		Spell s2 = new TwistingNether();
		Card m = new Minion("Wilfred Fizzlebang", 6 , Rarity.LEGENDARY ,4 , 4 , false , false , false);
		res.add(m);
		res.add(k1); res.add(k2); res.add(multishot1); res.add(multishot2);res.add(s1);res.add(s2);
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
			if(this.getDeck().get(i).getName().equals("Wilfred Fizzlebang"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/wilfred fizzlebang.png"))));
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
			if(this.getDeck().get(i) instanceof CurseOfWeakness)
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Curse of Weakness.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i) instanceof TwistingNether)
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Twisting Nether.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i) instanceof SiphonSoul)
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Siphon Soul.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			
		}
	}
	
	public void useHeroPower() throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException {
		boolean wilfred = false;
		boolean Chromaggus = false;
		super.useHeroPower();
		this.setCurrentHP(this.getCurrentHP() - 2);
		for(int i = 0 ; i<this.getField().size() ; i++)
		{
			if(this.getField().get(i).getName().equals("Wilfred Fizzlebang"))
				wilfred = true;
			if(this.getField().get(i).getName().equals("Chromaggus"))
				Chromaggus = true;	
		}
			int preSize = this.getHand().size();
			Card c = this.drawCard();
			int newSize = this.getHand().size();
			if(c instanceof Minion)
			{
				if(wilfred && Chromaggus)
				{
					if(newSize == preSize+2) {
						this.getHand().get(this.getHand().size() - 1).setManaCost(0);
						this.getHand().get(this.getHand().size() - 2).setManaCost(0);
					}
					else
						this.getHand().get(this.getHand().size() - 1).setManaCost(0);			
				}
				if(wilfred && !Chromaggus)	
					this.getHand().get(this.getHand().size() - 1).setManaCost(0);
			}		
	}
}
