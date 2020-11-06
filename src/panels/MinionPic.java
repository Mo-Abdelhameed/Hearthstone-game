package panels;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.cards.minions.Minion;

public class MinionPic extends JLabel{

	
	private static final long serialVersionUID = 1L;
	ImageIcon image;
	JLabel minion ;
	JLabel health;
	JLabel attack;
	ImageIcon hp;
	ImageIcon att;
	JLabel sleeping;
	ImageIcon s;
	public MinionPic(Image image)
	{
	}
	
	public MinionPic(Minion m)  {
		
		this.setSize(85,100);
		try {
		if(m.getName().equals("Goldshire Footman"))	
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Goldshire Footman.png")));
		
		if(m.getName().equals("Stonetusk Boar"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Stonetusk Boar.png")));
		
		if(m.getName().equals("Bloodfen Raptor"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Bloodfen Raptor.png")));
		
		if(m.getName().equals("Frostwolf Grunt"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Frostwolf Grunt.png")));
		
		if(m.getName().equals("Wolfrider"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Wolfrider.png")));
		
		if(m.getName().equals("Chilwind Yeti"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Chilwind Yeti.png")));
		
		if(m.getName().equals("Boulderfist Ogre"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Boulderfist Ogre.png")));
		
		if(m.getName().equals("Core Hound"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Core Hound.png")));
		
		
		
		
		
		if(m.getName().equals("Chromaggus"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Chromaggus.png")));
		
		if(m.getName().equals("The LichKing"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/The LichKing.png")));
		
		if(m.getName().equals("Icehowl"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Icehowl.png")));
		
		
		
		if(m.getName().equals("King Krush"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/King Krush.png")));
		
		if(m.getName().equals("Kalycgos"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Kalygos.png")));
		
		
		
		if(m.getName().equals("Prophet Velen"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Prophet Velen.png")));
		
		if(m.getName().equals("Wilfred Fizzlebang"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Wilfred Fizzlebang.png")));
		
		if(m.getName().equals("Sheep"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Sheep.png")));
		
		if(m.getName().equals("Silver Hand Recruit"))
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Silver Hand Recruit.png")));
		
		///////////////////////
		if(m.getName().equals("Argent Commander") && m.isDivine())
			image = new ImageIcon(ImageIO.read(new File("fieldMinionsDivine/Argent Commandor.png")));
		
		if(m.getName().equals("Tirion Fordring") && m.isDivine())
			image = new ImageIcon(ImageIO.read(new File("fieldMinionsDivine/Tirion Fordring.png")));
		
		if(m.getName().equals("Colossus of the Moon") && m.isDivine())
			image = new ImageIcon(ImageIO.read(new File("fieldMinionsDivine/Colossus of the Moon.png")));
		
		if(m.getName().equals("Sunwalker") && m.isDivine())
			image = new ImageIcon(ImageIO.read(new File("fieldMinionsDivine/Sunwalker.png")));
		
		if(m.getName().equals("Argent Commander") && !m.isDivine())
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Argent Commandor_1.png")));
		
		if(m.getName().equals("Tirion Fordring") && !m.isDivine())
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Tirion Fordring_1.png")));
		
		if(m.getName().equals("Colossus of the Moon") && !m.isDivine())
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Colossus of the Moon_1.png")));
		
		if(m.getName().equals("Sunwalker") && !m.isDivine())
			image = new ImageIcon(ImageIO.read(new File("fieldMinions/Sunwalker_1.png")));
		
		
	
		
		
		
		minion = new JLabel(image);
		minion.setSize(85, 100);
		s = new ImageIcon(ImageIO.read(new File("images/zzzz.png")));
		hp = new ImageIcon(ImageIO.read(new File("images/health.png")));
		att = new ImageIcon(ImageIO.read(new File("images/attack.png")));
		health = new JLabel(hp);
		attack = new JLabel(att);
		sleeping = new JLabel(s);
		
		minion.add(health);
		minion.add(attack);
		if(m.isSleeping())
		{
			minion.add(sleeping);
			sleeping.setBounds(50,0 , 40, 40);
		}
			
		attack.setBounds(-20, 50, 75, 75);
		health.setBounds(35, 50, 75, 75);
		
		
		
		if(m.getAttack() == 0)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/0.png")))));
			attack.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
			
		
		if(m.getAttack() == 1)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/1.png")))));
			attack.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getAttack() == 2)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/2.png")))));
			attack.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getAttack() == 3)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/3.png")))));
			attack.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getAttack() == 4)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/4.png")))));
			attack.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getAttack() == 5)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/5.png")))));
			attack.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getAttack() == 6)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/6.png")))));
			attack.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getAttack() == 7)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/7.png")))));
			attack.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getAttack() == 8)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/8.png")))));
			attack.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getAttack() == 9)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/9.png")))));
			attack.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getAttack() == 10)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/10 attack.png")))));
			attack.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		
		if(m.getCurrentHP() == 0)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/0.png")))));
			health.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getCurrentHP() == 1)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/1.png")))));
			health.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getCurrentHP() == 2)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/2.png")))));
			health.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getCurrentHP() == 3)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/3.png")))));
			health.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getCurrentHP() == 4)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/4.png")))));
			health.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getCurrentHP() == 5)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/5.png")))));
			health.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getCurrentHP() == 6)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/6.png")))));
			health.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getCurrentHP() == 7)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/7.png")))));
			health.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getCurrentHP() == 8)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/8.png")))));
			health.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getCurrentHP() == 9)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/9.png")))));
			health.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		if(m.getCurrentHP() == 10)
		{
			JLabel H = new JLabel((new ImageIcon(ImageIO.read(new File("digits/10 health.png")))));
			health.add(H);
			H.setBounds(0, 0, attack.getWidth(), attack.getHeight());
		}
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {

	Minion m = new Minion();
	m.setName("Chilwind Yeti");
	MinionPic mm = new MinionPic(m);
	JFrame j = new JFrame();
	j.setVisible(true);
	j.add(mm.getMinion());
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ImageIcon getImage() {
		return image;
	}

	public JLabel getMinion() {
		return minion;
	}
}
