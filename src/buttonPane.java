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
	
	public buttonPane(Visualiseur m_visu) {
		
		//-----setBorderColor-----
		//(pour faciliter la vision)
		this.setBorder(new LineBorder(Color.red));
		
		//-----setSize-----
		this.setBounds(290,730,700,130);
		
		//-----Declaration-----
		//img_negatif = img_src;
		//buttonNegatifAction negatif_action = new buttonNegatifAction( m_visu );
		
		ImageIcon icon1 = new ImageIcon("./img/start.png");
		JButton but_negatif = new JButton(icon1);
		but_negatif.setPreferredSize(new Dimension(100,100));
		//but_negatif.addActionListener( negatif_action );
		
		this.add(but_negatif, FlowLayout.LEFT);
		
	}
	

}
