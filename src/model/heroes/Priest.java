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
import model.cards.*;
import model.cards.spells.*;
import model.cards.minions.Minion;
public class Priest extends Hero {

	public Priest() throws IOException , CloneNotSupportedException{
		super("Anduin Wrynn");
		this.buildDeck();
	}
	
	public  void buildDeck () throws IOException , CloneNotSupportedException{
		ArrayList<Card> res = new ArrayList<>();
		JLabel hunter1 ;
		ArrayList<Minion> tmp2 = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv") , 13);
		Spell k1 = new DivineSpirit();
		Spell k2 = new DivineSpirit();
		Spell multishot1 = new HolyNova();
		Spell multishot2 = new HolyNova();
		Spell s1 = new ShadowWordDeath();
		Spell s2 = new ShadowWordDeath();
		Card m = new Minion("Prophet Velen" , 7 , Rarity.LEGENDARY ,7 , 7 , false , false , false);
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
			if(this.getDeck().get(i).getName().equals("Prophet Velen"))
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Prophet Velen.png"))));
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
			if(this.getDeck().get(i) instanceof DivineSpirit)
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Divine Spirit.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i) instanceof ShadowWordDeath)
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Shadow Word Death.png"))));
				super.getHeroImagesDeck().add(hunter1);
			}
			if(this.getDeck().get(i) instanceof HolyNova)
			{
				hunter1 =  new JLabel(new ImageIcon(ImageIO.read(new File("images/Holy Nova.gif"))));
				super.getHeroImagesDeck().add(hunter1);
			}
				
		}
		
	}
	
	public void useHeroPower(Minion target) throws NotEnoughManaException,	HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException
	{
		super.useHeroPower();
		for(int i = 0 ; i<this.getField().size() ; i++)
		{
			if(this.getField().get(i).getName().equals("Prophet Velen"))
			{
				target.setCurrentHP(target.getCurrentHP() + 8);
				return;
			}
		}
		target.setCurrentHP(target.getCurrentHP() + 2);
	}
	public void useHeroPower(Hero target) throws NotEnoughManaException,	HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		
		for(int i = 0 ; i<this.getField().size() ; i++)
		{
			if(this.getField().get(i).getName().equals("Prophet Velen"))
			{
				target.setCurrentHP(target.getCurrentHP() + 8);
				return;
			}	
		}
		target.setCurrentHP(target.getCurrentHP() + 2);
	}
}
	

