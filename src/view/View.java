package view;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import panels.BoardPanel;

public class View  extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<JLabel> herosImages = new ArrayList<>();
	private JLabel startScreen;
	private JButton Start ;
	private BoardPanel board = new BoardPanel(true);
	
	
	
	public View(String s) throws IOException {
		super();
		if(s.equals("start"))
		{
			startScreen = new JLabel(new ImageIcon(ImageIO.read(new File("images/herostheme.png"))));
			this.setContentPane(startScreen);
			this.setBounds(0,0,2000,1000);
			this.setVisible(true);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		    Start = new JButton("Start");
			add(Start);
			Start.setBounds(545,500 , 200, 50);
		
			
			this.revalidate();
			this.repaint();
		
		}
		
//		if(s.equals("choosehero"))
//		{
//			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//			this.setBounds(0,0,2000,1000);
//			this.setVisible(true);
//			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("images/herostheme.png")))));
//			setLayout(new BorderLayout());
//
//			JLabel hunter1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_hunter2.png"))));
//			JLabel mage1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_mage2.png"))));
//			JLabel paladin1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_paladin2.png"))));
//			JLabel priest1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_priest2.png"))));
//			JLabel warlock1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_warlock2.png"))));
//			
//			JLabel hunter2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_hunter2.png"))));
//			JLabel mage2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_mage2.png"))));
//			JLabel paladin2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_paladin2.png"))));
//			JLabel priest2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_priest2.png"))));
//			JLabel warlock2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_warlock2.png"))));
//			
//			
//			JLabel play1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_hero-select-screen.png"))));
//			JLabel play2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_hero-select-screen.png"))));
//			
//			
//			herosImages.add(hunter1); herosImages.add(hunter2);
//			herosImages.add(mage1);   herosImages.add(mage2);
//			herosImages.add(paladin1);herosImages.add(paladin2);
//			herosImages.add(priest1); herosImages.add(priest2);
//			herosImages.add(warlock1);herosImages.add(warlock2);
//			herosImages.add(play1);   herosImages.add(play2);
//			
//			
//			
//
//			
//			 
//			this.setLayout(new GridLayout(2,6));
//			add(hunter1);
//			add(mage1);
//			add(paladin1);
//			add(priest1);
//			add(warlock1);
//			add(play1);
//			
//			add(hunter2);
//			add(mage2);
//			add(paladin2);
//			add(priest2);
//			add(warlock2);
//			add(play2);
//			
//
//			this.revalidate();
//			this.repaint();
//		}
		
		if(s.equals("field"))
		{
			this.setBounds(0,0,2000,1000);
			this.setVisible(true);
			this.add(board);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
			this.revalidate();
			this.repaint();
			
		}
	}
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public JButton getStart() {
		return Start;
	}



	public BoardPanel getBoard() {
		return board;
	}



	public JLabel getStartScreen() {
		return startScreen;
	}





	public static void main(String[] args) throws IOException {
		new Controller();
	}

	



	public ArrayList<JLabel> getHerosImages() {
		return herosImages;
	}
	public void chooseView()throws IOException
	{
		this.remove(Start);
		setLayout(new BorderLayout());
		JLabel hunter1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_hunter2.png"))));
		JLabel mage1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_mage2.png"))));
		JLabel paladin1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_paladin2.png"))));
		JLabel priest1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_priest2.png"))));
		JLabel warlock1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_warlock2.png"))));
		JLabel hunter2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_hunter2.png"))));
		JLabel mage2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_mage2.png"))));
		JLabel paladin2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_paladin2.png"))));
		JLabel priest2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_priest2.png"))));
		JLabel warlock2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_warlock2.png"))));
		JLabel play1 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_hero-select-screen.png"))));
		JLabel play2 = new JLabel(new ImageIcon(ImageIO.read(new File("images/rsz_hero-select-screen.png"))));
		herosImages.add(hunter1); herosImages.add(hunter2);
		herosImages.add(mage1);   herosImages.add(mage2);
		herosImages.add(paladin1);herosImages.add(paladin2);
		herosImages.add(priest1); herosImages.add(priest2);
		herosImages.add(warlock1);herosImages.add(warlock2);
		herosImages.add(play1);   herosImages.add(play2);
		this.setLayout(new GridLayout(2,6));
		add(hunter1);
		add(mage1);
		add(paladin1);
		add(priest1);
		add(warlock1);
		add(play1);
		add(hunter2);
		add(mage2);
		add(paladin2);
		add(priest2);
		add(warlock2);
		add(play2);
		this.revalidate();
		this.repaint();
	}
	
	public void fieldView()
	{
		this.removeAll();
		this.setBounds(0,0,2000,1000);
		this.setVisible(true);
		this.add(board);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		this.revalidate();
		this.repaint();
	}
	

	
	

	
	
	
}
