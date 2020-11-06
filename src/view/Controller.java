package view;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import engine.*;
import exceptions.*;
import panels.*;
import model.cards.minions.Minion;
import model.cards.spells.*;
import model.heroes.*;

	public class Controller implements ActionListener , GameListener , MouseListener{
		private View view ;
		private ArrayList<JLabel> herosImages= new ArrayList<>();
		private Game game;
		private boolean firstplayerConfirmed = false , secondplayerConfirmed;
		private Hero firsthero , secondhero;
		private boolean minionTarget = false;
		private MinionTargetSpell miniontargetspell;
		private int handIndex ;
		private boolean herotarget = false;
		private HeroTargetSpell herotargetspell;
		private boolean gameStarted = false;
		private boolean leeching = false;
		private LeechingSpell leechingSpell;
		private boolean heroPower = false;
		private boolean attack = false;
		private Minion attacker ;
		private Clip clip;
		private static JFrame exception;
		
	
	public Controller() throws IOException {
		view = new View("start");
		this.playSound("sounds/Main-Title.wav");
		view.getStartScreen().addMouseListener(this);
		view.getBoard().getPlayer1().getPlayer().getHeroPower().addActionListener(this);
		view.getBoard().getPlayer1().getPlayer().getEndTurn().addActionListener(this);
		view.getBoard().getPlayer2().getPlayer().getHeroPower().addActionListener(this);
		view.getBoard().getPlayer2().getPlayer().getEndTurn().addActionListener(this);
		view.getStart().addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		
		if(b.getActionCommand().equals("end turn"))
		{
			heroPower = false;
			try {
				game.endTurn();
				CardPanel temp ;
				FieldPanel temp1 ;
				temp = view.getBoard().getPlayer1();
				view.getBoard().setPlayer1(view.getBoard().getPlayer2());
				view.getBoard().setPlayer2(temp);
				
				temp1 = view.getBoard().getCurrPlayer();
				view.getBoard().setCurrPlayer(view.getBoard().getOppPlayer());
				view.getBoard().setOppPlayer(temp1);
				if(game.getCurrentHero().getFatigueDamage() == 0)
					updateHand();
				else
					updateHandException();
			} catch (FullHandException e1) {
				fullHandException();
				CardPanel temp  ;
				temp = view.getBoard().getPlayer1();
				view.getBoard().setPlayer1(view.getBoard().getPlayer2());
				view.getBoard().setPlayer2(temp);
				
				FieldPanel temp1 ;
				temp1 = view.getBoard().getCurrPlayer();
				view.getBoard().setCurrPlayer(view.getBoard().getOppPlayer());
				view.getBoard().setOppPlayer(temp1);
				updateHandException();
				
			} catch (CloneNotSupportedException e1) {e1.printStackTrace();}
				
				view.getBoard().getPlayer1().getPlayer().setHp(game.getCurrentHero().getCurrentHP());	
				view.getBoard().getPlayer1().getPlayer().setManaCrystals(game.getCurrentHero().getTotalManaCrystals(), game.getCurrentHero().getTotalManaCrystals());
				view.getBoard().getPlayer1().getPlayer().setDeckCards(game.getCurrentHero().getDeck().size());
				view.getBoard().getPlayer2().getPlayer().getEndTurn().setVisible(false);
				view.getBoard().getPlayer2().getPlayer().getHeroPower().setVisible(false);
				view.getBoard().getPlayer1().getPlayer().getEndTurn().setVisible(true);
				view.getBoard().getPlayer1().getPlayer().getHeroPower().setVisible(true);
				this.updateField();
				view.revalidate();
				view.repaint();
		}
		if(b.getActionCommand().equals("use hero power"))
		{
			if(game.getCurrentHero() instanceof Paladin   || game.getCurrentHero() instanceof Hunter) 
				try{game.getCurrentHero().useHeroPower();
					if(game.getCurrentHero() instanceof Paladin) {
						game.getCurrentHero().getField().get(game.getCurrentHero().getField().size()-1).setListener(game.getCurrentHero());
						updateField();
						view.getBoard().getCurrPlayer().getMinionsInCurrField().get(game.getCurrentHero().getField().size()-1).addMouseListener(this);
					}
					
				}
		    	catch (NotEnoughManaException e1) {notEnoughManaException();return;}
				catch (HeroPowerAlreadyUsedException e1) {HeroPowerAlreadyUsedException(); return;}
				catch (NotYourTurnException e1) {notYourTurnException();	return;}
				catch (FullHandException e1) {fullHandException(); return;}
				catch (FullFieldException e1) {fullFieldException(); return;}
		    	catch (CloneNotSupportedException e1) {e1.printStackTrace();}
			if(game.getCurrentHero() instanceof Warlock)
			{
				try{game.getCurrentHero().useHeroPower(); updateHand();}
		    	catch (NotEnoughManaException e1) {notEnoughManaException();return;}
				catch (HeroPowerAlreadyUsedException e1) {HeroPowerAlreadyUsedException(); return;}
				catch (NotYourTurnException e1) {notYourTurnException();	return;}
				catch (FullHandException e1) {fullHandException(); return;}
				catch (FullFieldException e1) {fullFieldException(); return;}
		    	catch (CloneNotSupportedException e1) {e1.printStackTrace();}
			}
			else
				heroPower = true;
		    this.updateField();
		    this.updateHero();   
		}
		if(b.getActionCommand().equals("Start")) {
			try {view.chooseView();} catch (IOException e1) {e1.printStackTrace();}
			for(int i = 0 ; i < view.getHerosImages().size() ; i++)
			{
				view.getHerosImages().get(i).addMouseListener(this);
				this.herosImages.add(view.getHerosImages().get(i));		
			}
			return;
		}	
	}
	public Game getGame() {
		return game;
	}
	@Override
	public void onGameOver() {
		try {
			JLabel winner , l;
			if(firsthero.getCurrentHP() == 0) {
				 winner = (JLabel) view.getBoard().getPlayer2().getHand().getComponent(0);
				 l = new JLabel(new ImageIcon(ImageIO.read(new File("images/gameover2 .jpeg"))));
			}
			
			else {
				winner = (JLabel) view.getBoard().getPlayer1().getHand().getComponent(0);
				l = new JLabel(new ImageIcon(ImageIO.read(new File("images/gameover1.jpg"))));
			}
			l.add(winner);
			winner.setBounds(420, 260, 143,198);
			view.setEnabled(false);
			JFrame j = new JFrame();
			j.setBounds(250, 150, 728, 455);
			j.add(l);
			j.setVisible(true);
			j.setResizable(false);
			j.revalidate();
			j.repaint();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() instanceof JLabel) {
			JLabel l = (JLabel)e.getSource();
			if(attack)
			{
				
				if(l == view.getBoard().getPlayer2().getHand().getComponent(0))
				{
					
					try {game.getCurrentHero().attackWithMinion(attacker, game.getOpponent());}
						 catch (CannotAttackException e1) {CannotAttackException();}
						 catch (NotYourTurnException e1) {notYourTurnException();}
						 catch (TauntBypassException e1) {TauntException();}
						 catch (InvalidTargetException e1) {InvalidTargetException();}
						 catch (NotSummonedException e1) {NotSummonedException();}
						 this.updateHero();	
						 attack = false;
						 return;
				}
				
				 if(view.getBoard().getOppPlayer().getMinionsInCurrField().contains(l))
				{
					int index = view.getBoard().getOppPlayer().getMinionsInCurrField().indexOf(l);
					if(index == -1)
					{
						InvalidTargetException();
						attack = false;
						return;
					}
					try {game.getCurrentHero().attackWithMinion(attacker, game.getOpponent().getField().get(index));}
					 catch (CannotAttackException e1) {CannotAttackException();}
					 catch (NotYourTurnException e1) {notYourTurnException();}
					 catch (TauntBypassException e1) {TauntException();}
					 catch (exceptions.InvalidTargetException e1) {InvalidTargetException();}
					 catch (NotSummonedException e1) {NotSummonedException();}
					this.updateField();
					attack = false;
					return;
				}
				attack = false;
			}
			if(game != null && game.getCurrentHero().getHeroImagesHand().indexOf(l) != -1)
					this.handIndex = game.getCurrentHero().getHeroImagesHand().indexOf(l);
			

		
		if(this.getHerosImages().size()!=0 && l == this.getHerosImages().get(0)) 
			try {firsthero = new Hunter();} catch (IOException e1) {e1.printStackTrace();}catch (CloneNotSupportedException e1) {e1.printStackTrace();}
		
		if(this.getHerosImages().size()!=0 && l == this.getHerosImages().get(1)) 
			try {secondhero = new Hunter(); } catch (IOException e1) {e1.printStackTrace();}catch (CloneNotSupportedException e1) {e1.printStackTrace();}
		
		if(this.getHerosImages().size()!=0 && l == this.getHerosImages().get(2)) 
			try {firsthero = new Mage(); } catch (IOException e1) {e1.printStackTrace();}catch (CloneNotSupportedException e1) {e1.printStackTrace();}
		
		if(this.getHerosImages().size()!=0 && l == this.getHerosImages().get(3)) 
			try {secondhero = new Mage(); } catch (IOException e1) {e1.printStackTrace();}catch (CloneNotSupportedException e1) {e1.printStackTrace();}
		
		if(this.getHerosImages().size()!=0 && l == this.getHerosImages().get(4)) 
			try {firsthero = new Paladin();} catch (IOException e1) {e1.printStackTrace();}catch (CloneNotSupportedException e1) {e1.printStackTrace();}
		
		if(this.getHerosImages().size()!=0 && l == this.getHerosImages().get(5)) 
			try {secondhero = new Paladin(); } catch (IOException e1) {e1.printStackTrace();}catch (CloneNotSupportedException e1) {e1.printStackTrace();}
		
		if(this.getHerosImages().size()!=0 && l == this.getHerosImages().get(6)) 
			try {firsthero = new Priest();} catch (IOException e1) {e1.printStackTrace();}catch (CloneNotSupportedException e1) {e1.printStackTrace();}
		
		if(this.getHerosImages().size()!=0 && l == this.getHerosImages().get(7)) 
			try {secondhero = new Priest();} catch (IOException e1) {e1.printStackTrace();}catch (CloneNotSupportedException e1) {e1.printStackTrace();}
		
		if(this.getHerosImages().size()!=0 && l == this.getHerosImages().get(8)) 
			try {firsthero = new Warlock(); } catch (IOException e1) {e1.printStackTrace();}catch (CloneNotSupportedException e1) {e1.printStackTrace();}
		
		if(this.getHerosImages().size()!=0 && l == this.getHerosImages().get(9)) 
			try {secondhero = new Warlock(); } catch (IOException e1) {e1.printStackTrace();}catch (CloneNotSupportedException e1) {e1.printStackTrace();}
		
		if(l == this.getHerosImages().get(10) && firsthero != null)
		{
			firstplayerConfirmed = true;
			if(secondplayerConfirmed)
			{
				try {game = new Game(firsthero , secondhero); game.setListener(this);} catch (FullHandException e1) {e1.printStackTrace();} catch (CloneNotSupportedException e1) {e1.printStackTrace();}
				try {view.dispose(); view = new View("field");
				this.closeSound();
				this.playSound("sounds/Duel.wav");	
				setGameStarted(true);
				view.getBoard().getPlayer1().getPlayer().getHeroPower().addActionListener(this);
				view.getBoard().getPlayer1().getPlayer().getEndTurn().addActionListener(this);
				view.getBoard().getPlayer2().getPlayer().getHeroPower().addActionListener(this);
				view.getBoard().getPlayer2().getPlayer().getEndTurn().addActionListener(this);
				view.getBoard().getPlayer1().getPlayer().setDeckCards(game.getCurrentHero().getDeck().size());
				
				if(firsthero instanceof Hunter)
					view.getBoard().getPlayer1().getHand().add(this.herosImages.get(0));
				
				if(firsthero instanceof Mage)
					view.getBoard().getPlayer1().getHand().add(this.herosImages.get(2));
				
				if(firsthero instanceof Paladin)
					view.getBoard().getPlayer1().getHand().add(this.herosImages.get(4));
				
				if(firsthero instanceof Priest)
					view.getBoard().getPlayer1().getHand().add(this.herosImages.get(6));
				
				if(firsthero instanceof Warlock)
					view.getBoard().getPlayer1().getHand().add(this.herosImages.get(8));
				
				if(secondhero instanceof Hunter)
					view.getBoard().getPlayer2().getHand().add(this.herosImages.get(1));
				
				if(secondhero instanceof Mage)
					view.getBoard().getPlayer2().getHand().add(this.herosImages.get(3));
				
				if(secondhero instanceof Paladin)
					view.getBoard().getPlayer2().getHand().add(this.herosImages.get(5));
				
				if(secondhero instanceof Priest)
					view.getBoard().getPlayer2().getHand().add(this.herosImages.get(7));
				
				if(secondhero instanceof Warlock)
					view.getBoard().getPlayer2().getHand().add(this.herosImages.get(9));
				for(int i = 0 ; i < game.getCurrentHero().getHand().size() ; i++)
				{
					JLabel j = game.getCurrentHero().getHeroImagesHand().get(i);
					view.getBoard().getPlayer1().getHand().add(j);
					j.addMouseListener(this);
					
				}
				for(int i = 0 ; i < game.getOpponent().getHand().size() ; i++)
				{
					JLabel cardBack = new JLabel(new ImageIcon(ImageIO.read(new File("images/card back.png"))));
					view.getBoard().getPlayer2().getHand().add(cardBack);
					JLabel j = game.getOpponent().getHeroImagesHand().get(i);
					j.addMouseListener(this);
				}
				for(int i = 0 ; i < game.getCurrentHero().getHeroImagesDeck().size() ; i++)
				{
					JLabel j1 = game.getCurrentHero().getHeroImagesDeck().get(i);
					j1.addMouseListener(this);
				}
				for(int i = 0 ; i < game.getOpponent().getHeroImagesDeck().size() ; i++)
				{
					JLabel j1 = game.getOpponent().getHeroImagesDeck().get(i);
					j1.addMouseListener(this);
				}
			} catch (IOException e1) {e1.printStackTrace();}	
			}
			
		}
		if(l == this.getHerosImages().get(11) && secondhero != null)
		{
			
			secondplayerConfirmed = true;
			if(firstplayerConfirmed)
			{
				try {game = new Game(firsthero , secondhero); game.setListener(this);} catch (FullHandException e1) {e1.printStackTrace();} catch (CloneNotSupportedException e1) {e1.printStackTrace();}
				try {view.dispose(); view = new View("field");
				this.closeSound();
				this.playSound("sounds/Duel.wav");
					
				setGameStarted(true);
				view.getBoard().getPlayer1().getPlayer().getHeroPower().addActionListener(this);
				view.getBoard().getPlayer1().getPlayer().getEndTurn().addActionListener(this);
				view.getBoard().getPlayer2().getPlayer().getHeroPower().addActionListener(this);
				view.getBoard().getPlayer2().getPlayer().getEndTurn().addActionListener(this);
				view.getBoard().getPlayer1().getPlayer().setDeckCards(game.getCurrentHero().getDeck().size());
				view.getBoard().getPlayer2().getPlayer().setDeckCards(game.getOpponent().getDeck().size());
				
				
				if(game.getCurrentHero() instanceof Hunter)
					view.getBoard().getPlayer1().getHand().add(this.herosImages.get(0));
				
				if(game.getCurrentHero() instanceof Mage)
					view.getBoard().getPlayer1().getHand().add(this.herosImages.get(2));
				
				if(game.getCurrentHero() instanceof Paladin)
					view.getBoard().getPlayer1().getHand().add(this.herosImages.get(4));
				
				if(game.getCurrentHero() instanceof Priest)
					view.getBoard().getPlayer1().getHand().add(this.herosImages.get(6));
				
				if(game.getCurrentHero() instanceof Warlock)
					view.getBoard().getPlayer1().getHand().add(this.herosImages.get(8));
				
				if(game.getOpponent() instanceof Hunter)
					view.getBoard().getPlayer2().getHand().add(this.herosImages.get(1));
				
				if(game.getOpponent() instanceof Mage)
					view.getBoard().getPlayer2().getHand().add(this.herosImages.get(3));
				
				if(game.getOpponent() instanceof Paladin)
					view.getBoard().getPlayer2().getHand().add(this.herosImages.get(5));
				
				if(game.getOpponent() instanceof Priest)
					view.getBoard().getPlayer2().getHand().add(this.herosImages.get(7));
				
				if(game.getOpponent() instanceof Warlock)
					view.getBoard().getPlayer2().getHand().add(this.herosImages.get(9));
				
				for(int i = 0 ; i < game.getCurrentHero().getHand().size() ; i++)
				{
					JLabel j = game.getCurrentHero().getHeroImagesHand().get(i);
					view.getBoard().getPlayer1().getHand().add(j);
					j.addMouseListener(this);
				}
				for(int i = 0 ; i < game.getOpponent().getHand().size() ; i++)
				{
					JLabel cardBack = new JLabel(new ImageIcon(ImageIO.read(new File("images/card back.png"))));
					view.getBoard().getPlayer2().getHand().add(cardBack);	
					JLabel j = game.getOpponent().getHeroImagesHand().get(i);
					j.addMouseListener(this);
				}
				for(int i = 0 ; i < game.getCurrentHero().getHeroImagesDeck().size() ; i++)
				{
					JLabel j1 = game.getCurrentHero().getHeroImagesDeck().get(i);
					j1.addMouseListener(this);
				}
				for(int i = 0 ; i < game.getOpponent().getHeroImagesDeck().size() ; i++)
				{
					JLabel j1 = game.getOpponent().getHeroImagesDeck().get(i);
					j1.addMouseListener(this);
				}
			
				} catch (IOException e1) {e1.printStackTrace();}
			}
		}
		
		if(game != null && game.getCurrentHero().getHeroImagesHand().contains(l))
		{
			int index = game.getCurrentHero().getHeroImagesHand().indexOf(l);
			if(game.getCurrentHero().getHand().get(index) instanceof Minion)
			{
				Minion n = (Minion) game.getCurrentHero().getHand().get(index);
			   	try {game.getCurrentHero().playMinion(n);}
			    catch (NotYourTurnException e1) {notYourTurnException();return;}
			    catch (NotEnoughManaException e1) {notEnoughManaException();return;}
			    catch (FullFieldException e1) {fullFieldException();return;}
				view.getBoard().getPlayer1().getPlayer().setManaCrystals(game.getCurrentHero().getCurrentManaCrystals(), game.getCurrentHero().getTotalManaCrystals());
				view.getBoard().getPlayer1().getPlayer().setDeckCards(game.getCurrentHero().getDeck().size());
				//MinionPanel minion = new MinionPanel(n);
				MinionPic minion = new MinionPic(n);
				minion.getMinion().addMouseListener(this);
				
				view.getBoard().getCurrPlayer().add(minion.getMinion());
				view.getBoard().getPlayer1().getHand().getComponent(index+1).setVisible(false);
				view.getBoard().getPlayer1().getHand().remove(index + 1);
				view.getBoard().getCurrPlayer().getMinionsInCurrField().add(minion.getMinion());		
			}
			else
			{
				if(game.getCurrentHero().getHand().get(index) instanceof AOESpell)
				{
		 		    try{game.getCurrentHero().castSpell((AOESpell)game.getCurrentHero().getHand().get(index), game.getOpponent().getField());}
				    catch (NotYourTurnException e1) {notYourTurnException();return;}
				    catch (NotEnoughManaException e1) {notEnoughManaException();return;}
					this.updateField();
					view.getBoard().getPlayer1().getPlayer().setManaCrystals(game.getCurrentHero().getCurrentManaCrystals(), game.getCurrentHero().getTotalManaCrystals());
					view.getBoard().getPlayer1().getPlayer().setDeckCards(game.getCurrentHero().getDeck().size());
					view.getBoard().getPlayer1().getHand().getComponent(index+1).setVisible(false);
					view.getBoard().getPlayer1().getHand().remove(index + 1);
					
				}
				else if(game.getCurrentHero().getHand().get(index) instanceof FieldSpell)
				{
					try{game.getCurrentHero().castSpell((FieldSpell)game.getCurrentHero().getHand().get(index));}
				    catch (NotYourTurnException e1) {notYourTurnException();return;}	
					catch (NotEnoughManaException e1) {notEnoughManaException();return;}
					this.updateField();
					view.getBoard().getPlayer1().getPlayer().setManaCrystals(game.getCurrentHero().getCurrentManaCrystals(), game.getCurrentHero().getTotalManaCrystals());
					view.getBoard().getPlayer1().getPlayer().setDeckCards(game.getCurrentHero().getDeck().size());
					view.getBoard().getPlayer1().getHand().getComponent(index+1).setVisible(false);
					view.getBoard().getPlayer1().getHand().remove(index + 1);
				}
				else if(game.getCurrentHero().getHand().get(index) instanceof LeechingSpell)
				{
					leeching = true;
					this.leechingSpell = (LeechingSpell) game.getCurrentHero().getHand().get(index);
				}
				else if(game.getCurrentHero().getHand().get(index) instanceof MinionTargetSpell && game.getCurrentHero().getHand().get(index) instanceof HeroTargetSpell) 
				{
					minionTarget = true;
					this.miniontargetspell = (MinionTargetSpell) game.getCurrentHero().getHand().get(index);	
					herotarget = true;
					this.herotargetspell = (HeroTargetSpell) game.getCurrentHero().getHand().get(index);
				}
				else if(game.getCurrentHero().getHand().get(index) instanceof MinionTargetSpell)
				{
					minionTarget = true;
					this.miniontargetspell = (MinionTargetSpell) game.getCurrentHero().getHand().get(index);
				}
				else if(game.getCurrentHero().getHand().get(index) instanceof HeroTargetSpell)
				{
					herotarget = true;
					this.herotargetspell = (HeroTargetSpell) game.getCurrentHero().getHand().get(index);
				}
		}
			return;
	}
		
		if(herotarget && view.getBoard().getPlayer1().getHand().getComponent(0) == l)
		{
			try{game.getCurrentHero().castSpell(this.herotargetspell, game.getCurrentHero());}
		    catch (NotYourTurnException e1) {notYourTurnException();return;}
			catch (NotEnoughManaException e1) {notEnoughManaException();return;}
			this.updateField();
			view.getBoard().getPlayer1().getPlayer().setManaCrystals(game.getCurrentHero().getCurrentManaCrystals(), game.getCurrentHero().getTotalManaCrystals());
			view.getBoard().getPlayer1().getPlayer().setDeckCards(game.getCurrentHero().getDeck().size());
			view.getBoard().getPlayer1().getPlayer().setHp(game.getCurrentHero().getCurrentHP());
			view.getBoard().getPlayer1().getHand().getComponent(handIndex + 1).setVisible(false);
			view.getBoard().getPlayer1().getHand().remove(handIndex + 1);
			view.revalidate();
			view.repaint();
			herotarget = false;
			minionTarget = false; 
		}
		else
			if( herotarget && view.getBoard().getPlayer2().getHand().getComponent(0) == l)
			{
			    try{game.getCurrentHero().castSpell(this.herotargetspell, game.getOpponent()); }
			    catch (NotYourTurnException e1) {notYourTurnException();return;}
			    catch (NotEnoughManaException e1) {notEnoughManaException();return;}
			   	this.updateField();
				view.getBoard().getPlayer1().getPlayer().setManaCrystals(game.getCurrentHero().getCurrentManaCrystals(), game.getCurrentHero().getTotalManaCrystals());
				view.getBoard().getPlayer1().getPlayer().setDeckCards(game.getCurrentHero().getDeck().size());
				view.getBoard().getPlayer2().getPlayer().setHp(game.getOpponent().getCurrentHP());
				view.getBoard().getPlayer1().getHand().getComponent(handIndex + 1 ).setVisible(false);
				view.getBoard().getPlayer1().getHand().remove(handIndex + 1);
				view.revalidate();
				view.repaint();
				herotarget = false;
				minionTarget = false; 
			}
		if(heroPower && view.getBoard().getPlayer1().getHand().getComponent(0) == l)
		{
			heroPower = false;
			if(game.getCurrentHero() instanceof Mage)
			{
				Mage m = (Mage)game.getCurrentHero();
				try {m.useHeroPower(m);}
			    catch (NotEnoughManaException e1) {notEnoughManaException();return;}
			    catch (exceptions.HeroPowerAlreadyUsedException e1) {HeroPowerAlreadyUsedException();	return;}
			    catch (NotYourTurnException e1) {notYourTurnException();return;}
				catch (FullHandException e1) {fullHandException();return;}
			    catch (FullFieldException e1) {fullFieldException();return;}
			    catch (CloneNotSupportedException e1) {e1.printStackTrace();}
			}
			else if (game.getCurrentHero() instanceof Priest)
			{
				Priest p = (Priest)game.getCurrentHero();
				try {p.useHeroPower(p); view.getBoard().getPlayer1().getHand().add(game.getCurrentHero().getHeroImagesHand().get(game.getCurrentHero().getHeroImagesHand().size()-1));}
			    catch (NotEnoughManaException e1) {notEnoughManaException();return;}
			    catch (exceptions.HeroPowerAlreadyUsedException e1) {HeroPowerAlreadyUsedException();	return;}
			    catch (NotYourTurnException e1) {notYourTurnException();return;}
				catch (FullHandException e1) {fullHandException();return;}
			    catch (FullFieldException e1) {fullFieldException();return;}
			    catch (CloneNotSupportedException e1) {e1.printStackTrace();}
			}
			this.updateField();
			this.updateHero();		
		}
		if(heroPower && view.getBoard().getPlayer2().getHand().getComponent(0) == l)
		{
			heroPower = false;
			if(game.getCurrentHero() instanceof Mage)
			{
				Mage m = (Mage)game.getCurrentHero();
				try {m.useHeroPower(game.getOpponent());}
			    catch (NotEnoughManaException e1) {notEnoughManaException();return;}
			    catch (exceptions.HeroPowerAlreadyUsedException e1) {HeroPowerAlreadyUsedException();return;}
			    catch (NotYourTurnException e1) {notYourTurnException();return;}
				catch (FullHandException e1) {fullHandException();return;}
			    catch (FullFieldException e1) {fullFieldException();return;}
			    catch (CloneNotSupportedException e1) {e1.printStackTrace();}
			}
			else if (game.getCurrentHero() instanceof Priest)
			{
				Priest p = (Priest)game.getCurrentHero();
				try {p.useHeroPower(game.getOpponent());view.getBoard().getPlayer1().getHand().add(game.getCurrentHero().getHeroImagesHand().get(game.getCurrentHero().getHeroImagesHand().size()-1));}
			    catch (NotEnoughManaException e1) {notEnoughManaException();return;}
			    catch (exceptions.HeroPowerAlreadyUsedException e1) {HeroPowerAlreadyUsedException();	return;}
			    catch (NotYourTurnException e1) {notYourTurnException();return;}
				catch (FullHandException e1) {fullHandException();return;}
			    catch (FullFieldException e1) {fullFieldException();return;}
			    catch (CloneNotSupportedException e1) {e1.printStackTrace();}
				
			}
			this.updateField();
			this.updateHero();		
		}
		if(view.getBoard().getCurrPlayer().getMinionsInCurrField().contains(l))
		{	
			attack = true;
			int index = view.getBoard().getCurrPlayer().getMinionsInCurrField().indexOf(l);
			if(index == -1)
			{
				notYourTurnException();
				attack = false;
				return;
			}
			attacker = game.getCurrentHero().getField().get(index);	
		}
		else if(!minionTarget && !leeching && !herotarget && view.getBoard().getOppPlayer().getMinionsInCurrField().contains(l))
		{
			notYourTurnException();
		}
			
		
		if(minionTarget)
		{
			boolean current = true;
			int index = view.getBoard().getCurrPlayer().getMinionsInCurrField().indexOf(l);
			
			if(index == -1)
			{
				index = view.getBoard().getOppPlayer().getMinionsInCurrField().indexOf(l);
				current = false;
			}
				
			try {
				if(current)
					game.getCurrentHero().castSpell(this.miniontargetspell, game.getCurrentHero().getField().get(index));
				else
					game.getCurrentHero().castSpell(this.miniontargetspell, game.getOpponent().getField().get(index));
				minionTarget = false;
				herotarget = false;
			}catch (NotYourTurnException e1)   {notYourTurnException();	 return;}
			 catch (NotEnoughManaException e1) {notEnoughManaException(); return;} 
			 catch (InvalidTargetException e1) {InvalidTargetException(); return;}
			 this.updateField();
			 view.getBoard().getPlayer1().getPlayer().setManaCrystals(game.getCurrentHero().getCurrentManaCrystals(), game.getCurrentHero().getTotalManaCrystals());
			 view.getBoard().getPlayer1().getPlayer().setDeckCards(game.getCurrentHero().getDeck().size());
		 	 view.getBoard().getPlayer1().getHand().getComponent(handIndex + 1).setVisible(false);
			 view.getBoard().getPlayer1().getHand().remove(handIndex + 1);
			 view.revalidate();
			 view.repaint();
			 minionTarget = false;
			 herotarget = false;
			 return;
		}
		if(leeching)
		{
			boolean current = true;
			int index = view.getBoard().getCurrPlayer().getMinionsInCurrField().indexOf(l);
			
			if(index == -1)
			{
				index = view.getBoard().getOppPlayer().getMinionsInCurrField().indexOf(l);
				current = false;
			}
			
			try {
					if(current)
						game.getCurrentHero().castSpell(this.leechingSpell, game.getCurrentHero().getField().get(index));
					else
						game.getCurrentHero().castSpell(this.leechingSpell, game.getOpponent().getField().get(index));
					
				} 
			catch (NotYourTurnException e1) {notYourTurnException(); return;} 
			catch (NotEnoughManaException e1) {notEnoughManaException(); return;}
			this.updateField();
		    view.getBoard().getPlayer1().getPlayer().setManaCrystals(game.getCurrentHero().getCurrentManaCrystals(), game.getCurrentHero().getTotalManaCrystals());
		    view.getBoard().getPlayer1().getPlayer().setDeckCards(game.getCurrentHero().getDeck().size());
		    view.getBoard().getPlayer1().getHand().getComponent(handIndex + 1).setVisible(false);
		    view.getBoard().getPlayer1().getHand().remove(handIndex + 1);
		    view.revalidate();
			view.repaint();
			leeching = false;
			return;
	}
		if(heroPower)
		{
			boolean current = true;
			int index = view.getBoard().getCurrPlayer().getMinionsInCurrField().indexOf(l) ;
			if(index == -1)
			{
				index = view.getBoard().getOppPlayer().getMinionsInCurrField().indexOf(l) ;
				current = false;
			}
			if(current)
			{
				if(game.getCurrentHero() instanceof Mage)	
					try {((Mage)game.getCurrentHero()).useHeroPower(game.getCurrentHero().getField().get(index));}
			    	catch (NotEnoughManaException e1) {notEnoughManaException();return;}
			    	catch (exceptions.HeroPowerAlreadyUsedException e1) {HeroPowerAlreadyUsedException();	return;}
					catch (NotYourTurnException e1) {notYourTurnException();return;}
					catch (FullHandException e1) {fullHandException();return;}
			    	catch (FullFieldException e1) {fullFieldException();return;}
					catch (CloneNotSupportedException e1) {e1.printStackTrace();}
				else if(game.getCurrentHero() instanceof Priest)
					try {((Priest)game.getCurrentHero()).useHeroPower(game.getCurrentHero().getField().get(index));}
			    	catch (NotEnoughManaException e1) {notEnoughManaException();return;}
			    	catch (exceptions.HeroPowerAlreadyUsedException e1) {HeroPowerAlreadyUsedException();	return;}
			    	catch (NotYourTurnException e1) {notYourTurnException();return;}
					catch (FullHandException e1) {fullHandException();return;}
			    	catch (FullFieldException e1) {fullFieldException();return;}
					catch (CloneNotSupportedException e1) {e1.printStackTrace();}	
			}
			else
			{
				if(game.getCurrentHero() instanceof Mage)	
					try {((Mage)game.getCurrentHero()).useHeroPower(game.getOpponent().getField().get(index));}
			    	catch (NotEnoughManaException e1) {notEnoughManaException();return;}
			    	catch (exceptions.HeroPowerAlreadyUsedException e1) {HeroPowerAlreadyUsedException();	return;}
					catch (NotYourTurnException e1) {notYourTurnException();return;}
					catch (FullHandException e1) {fullHandException();return;}
			    	catch (FullFieldException e1) {fullFieldException();return;}
					catch (CloneNotSupportedException e1) {e1.printStackTrace();}
				else if(game.getCurrentHero() instanceof Priest)
					try {((Priest)game.getCurrentHero()).useHeroPower(game.getOpponent().getField().get(index));}
			    	catch (NotEnoughManaException e1) {notEnoughManaException();return;}
			    	catch (exceptions.HeroPowerAlreadyUsedException e1) {HeroPowerAlreadyUsedException();	return;}
			    	catch (NotYourTurnException e1) {notYourTurnException();return;}
					catch (FullHandException e1) {fullHandException();return;}
			    	catch (FullFieldException e1) {fullFieldException();return;}
					catch (CloneNotSupportedException e1) {e1.printStackTrace();}
				}
				this.updateField();
				this.updateHero();
				heroPower = false;
				return;
			}
		}
	}
	public static void CannotAttackException()
	{
		exception = new JFrame();
		exception.setBounds(330, 290, 474, 242);
		try {exception.add(new JLabel(new ImageIcon(ImageIO.read(new File("images/CannotAttackException.png")))));} catch (IOException e) {e.printStackTrace();}
		exception.setVisible(true);
		exception.setResizable(false);
		JButton n = new JButton("OK!");
		exception.add(n);
		
		
	}
	public static void notEnoughManaException()  {
		exception = new JFrame();
		exception.setBounds(330, 290, 474, 242);
		try {exception.add(new JLabel(new ImageIcon(ImageIO.read(new File("images/NotEnoughManaException.png")))));} catch (IOException e) {e.printStackTrace();}
		exception.setVisible(true);
		exception.setResizable(false);
	}
	public static void InvalidTargetException() {
		
		exception = new JFrame();
		exception.setBounds(330, 290, 474, 242);
		try {exception.add(new JLabel(new ImageIcon(ImageIO.read(new File("images/InvalidTargetException.png")))));} catch (IOException e) {e.printStackTrace();}
		exception.setVisible(true);
		exception.setResizable(false);
	}
	public static void notYourTurnException()
	{
		exception = new JFrame();
		exception.setBounds(330, 290, 474, 242);
		try {exception.add(new JLabel(new ImageIcon(ImageIO.read(new File("images/NotYourTurnException.png")))));} catch (IOException e) {e.printStackTrace();}
		exception.setVisible(true);
		exception.setResizable(false);
	}
	public static void fullFieldException() {
		exception = new JFrame();
		exception.setBounds(330, 290, 474, 242);
		try {exception.add(new JLabel(new ImageIcon(ImageIO.read(new File("images/FullFieldException.png")))));} catch (IOException e) {e.printStackTrace();}
		exception.setVisible(true);
		exception.setResizable(false);
	}
	public static void fullHandException()
	{
		exception = new JFrame();
		exception.setBounds(330, 290, 474, 242);
		try {JLabel l = new JLabel(new ImageIcon(ImageIO.read(new File("images/FullHandException.png"))));
		exception.add(l);
		} catch (IOException e) {e.printStackTrace();}
		exception.setVisible(true);
		exception.setResizable(false);
	}
	public static void HeroPowerAlreadyUsedException() {
		exception = new JFrame();
		exception.setBounds(330, 290, 474, 242);
		try {exception.add(new JLabel(new ImageIcon(ImageIO.read(new File("images/HeroPowerUsedException.png")))));} catch (IOException e) {e.printStackTrace();}
		exception.setVisible(true);
		exception.setResizable(false);
	}
	public static void TauntException() {
		exception = new JFrame();
		exception.setBounds(330, 290, 474, 242);
		try {exception.add(new JLabel(new ImageIcon(ImageIO.read(new File("images/TauntException.png")))));} catch (IOException e) {e.printStackTrace();}
		exception.setVisible(true);
		exception.setResizable(false);
	}
	public static void NotSummonedException(){
		exception = new JFrame();
		exception.setBounds(330, 290, 474, 242);
		try {exception.add(new JLabel(new ImageIcon(ImageIO.read(new File("images/NotSummonedException.png")))));} catch (IOException e) {e.printStackTrace();}
		exception.setVisible(true);
		exception.setResizable(false);
		
	}
	public View getView() {
		return view;
	}
	public ArrayList<JLabel> getHerosImages() {
		return herosImages;
	}
	public boolean isFirstplayerConfirmed() {
		return firstplayerConfirmed;
	}
	public boolean isSecondplayerConfirmed() {
		return secondplayerConfirmed;
	}
	public Hero getFirsthero() {
		return firsthero;
	}
	public Hero getSecondhero() {
		return secondhero;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	public void updateField()  {

		
		view.getBoard().getCurrPlayer().removeAll();
		view.getBoard().getCurrPlayer().getMinionsInCurrField().clear();
		for(int i = 0 ; i < game.getCurrentHero().getField().size() ; i++)
		{
			MinionPic m = new MinionPic(game.getCurrentHero().getField().get(i));
			view.getBoard().getCurrPlayer().add(m.getMinion());
			view.getBoard().getCurrPlayer().getMinionsInCurrField().add(m.getMinion());
			m.getMinion().addMouseListener(this);
		}
		
		view.getBoard().getOppPlayer().getMinionsInCurrField().clear();
		view.getBoard().getOppPlayer().removeAll();
		for(int i = 0 ; i < game.getOpponent().getField().size() ; i++)
		{
			MinionPic m = new MinionPic(game.getOpponent().getField().get(i));
			view.getBoard().getOppPlayer().add(m.getMinion());
			view.getBoard().getOppPlayer().getMinionsInCurrField().add(m.getMinion());
			m.getMinion().addMouseListener(this);
		}
		view.revalidate();
		view.repaint();
	}
	public void updateHero() {
		view.getBoard().getPlayer1().getPlayer().setHp(game.getCurrentHero().getCurrentHP());
		view.getBoard().getPlayer1().getPlayer().setManaCrystals(game.getCurrentHero().getCurrentManaCrystals(), game.getCurrentHero().getTotalManaCrystals());
		view.getBoard().getPlayer1().getPlayer().setDeckCards(game.getCurrentHero().getDeck().size());
		view.getBoard().getPlayer2().getPlayer().setHp(game.getOpponent().getCurrentHP());
		view.getBoard().getPlayer2().getPlayer().setManaCrystals(game.getOpponent().getCurrentManaCrystals(), game.getOpponent().getTotalManaCrystals());
		view.getBoard().getPlayer2().getPlayer().setDeckCards(game.getOpponent().getDeck().size());
		view.revalidate();
		view.repaint();
	}
	public boolean isGameStarted() {
		return gameStarted;
	}	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}
	public void playSound(String filePath) {	
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		    clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1 ) {}
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
	public void closeSound() {

		clip.close();
	}
	public void updateHand() {
		
		
		for(int i = 0 ; i < game.getOpponent().getHand().size() ; i++)
			 
			 view.getBoard().getPlayer2().getHand().remove(1);
		 
		 for(int i = 0 ; i < game.getOpponent().getHand().size() ; i++)
		 {
			 try {
				JLabel cardBack = new JLabel(new ImageIcon(ImageIO.read(new File("images/card back.png"))));
				view.getBoard().getPlayer2().getHand().add(cardBack);
			} catch (IOException e1) {e1.printStackTrace();}
			 
		 }
		 for(int i = 0 ; i < game.getCurrentHero().getHand().size() - 1  ; i++)
		 {
			 view.getBoard().getPlayer1().getHand().remove(1);
			 
		 }
		 for(int i = 0 ; i < game.getCurrentHero().getHand().size() ; i++)
		 {
				JLabel card = game.getCurrentHero().getHeroImagesHand().get(i);
				view.getBoard().getPlayer1().getHand().add(card);
		 }
	}
	public void updateHandException() {
		for(int i = 0 ; i < game.getOpponent().getHand().size() ; i++)
			 
			 view.getBoard().getPlayer2().getHand().remove(1);
		 
		 for(int i = 0 ; i < game.getOpponent().getHand().size() ; i++)
		 {
			 try {
				JLabel cardBack = new JLabel(new ImageIcon(ImageIO.read(new File("images/card back.png"))));
				view.getBoard().getPlayer2().getHand().add(cardBack);
			} catch (IOException e1) {e1.printStackTrace();}
			 
		 }
		 for(int i = 0 ; i < game.getCurrentHero().getHand().size()  ; i++)
		 {
			 view.getBoard().getPlayer1().getHand().remove(1);
			 
		 }
		 for(int i = 0 ; i < game.getCurrentHero().getHand().size() ; i++)
		 {
				JLabel card = game.getCurrentHero().getHeroImagesHand().get(i);
				view.getBoard().getPlayer1().getHand().add(card);
		 }
		 
	}
	public static void main(String[] args) throws IOException {
		new Controller();
	}
}
