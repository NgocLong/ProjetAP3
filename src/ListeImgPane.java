import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;


public class ListeImgPane extends JPanel {
	
	JPanel l_img_Pane = new JPanel();
	JScrollPane scroll_Pane = new JScrollPane(l_img_Pane);
	
	
	public ListeImgPane(JPanel mainPane) {
		super();
		
		
		this.setLayout(null);
		//l_img_Pane.setLayout(new BoxLayout(l;
		
		//-----setBorderColor-----
		
		
		//(pour faciliter la vision)
		this.setBorder(new LineBorder(Color.red));
		scroll_Pane.setBorder(new LineBorder(Color.red));
		
		//-----setSize-----
		this.setBounds(60,180,150,600);
		scroll_Pane.setBounds(10,10,130,580);
		l_img_Pane.setPreferredSize(new Dimension(110,1050));
		
		//-----Declaration-----
		
		
		//-----Add-----
		this.add(scroll_Pane);

	}
	
	public void addImgBut(JButton but_img)
	{
		l_img_Pane.add(but_img);
		
	}

}
