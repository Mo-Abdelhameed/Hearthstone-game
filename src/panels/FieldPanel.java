package panels;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FieldPanel extends JPanel{

	 
	Image x ;
	private static final long serialVersionUID = 1L;
	private ArrayList <JLabel> minionsInCurrField = new ArrayList<>(); 
	
	
	
	
	public ArrayList<JLabel> getMinionsInCurrField() {
		return minionsInCurrField;
	}
	public void setMinionsInCurrField(ArrayList<JLabel> minionsInCurrField) {
		this.minionsInCurrField = minionsInCurrField;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FieldPanel() throws IOException {
		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(1050,320));
		x = new ImageIcon(ImageIO.read(new File("images/blue.jpeg"))).getImage();
		
		// this.add(new JLabel(new ImageIcon(ImageIO.read(new File("images/field.jpg"))))); 
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(x,0,0,this.getWidth(),this.getHeight(),this); 
		
		
	}
	
}
