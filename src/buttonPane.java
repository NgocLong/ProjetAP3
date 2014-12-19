import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class buttonPane extends JPanelWithBackground {
	
	//private BufferedImage img_negatif = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);	
	int position = 60;
	
	public buttonPane(JPanel mainPane) throws IOException {
		
		super("./img/button.jpg");
		
		this.setLayout(null);
		//-----setBorderColor-----
		//(pour faciliter la vision)
		this.setBorder(new LineBorder(Color.red));
		
		//-----setSize-----
		this.setBounds(290,730,700,130);
		
		//-----Declaration-----
		
		
		//-----Add-----
		mainPane.add(this);
		

	}
	
	public void addBut(JButton b)
	{
		b.setBounds(position,40,110,50);
		position += 120;
		this.add(b);
	}

}
