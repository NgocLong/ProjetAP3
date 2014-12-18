import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class buttonPane extends JPanel {
	
	//private BufferedImage img_negatif = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);	
	
	public buttonPane(JPanel mainPane) {
		
		//-----setBorderColor-----
		//(pour faciliter la vision)
		this.setBorder(new LineBorder(Color.red));
		
		//-----setSize-----
		this.setBounds(290,730,700,130);
		
		//-----Declaration-----
		
		
		//-----Add-----
		mainPane.add(this);
		

	}
	

}
