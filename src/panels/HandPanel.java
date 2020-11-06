package panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HandPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image x;
	public HandPanel() 
	{
		super();
		this.setPreferredSize(new Dimension(1130, 180));
		try {
			x = new ImageIcon(ImageIO.read(new File("images/handWoodEdit.jpeg"))).getImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(x,0,0,this.getWidth(),this.getHeight(),this); 
		
		
	}
	
}
