import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;


public class mainPane extends JPanelWithBackground {

	private JLabel l_src = new JLabel();
	private JLabel l_dest = new JLabel();
	JPanel src_Pane = new JPanel();
	JPanel dest_Pane = new JPanel();
	JPanel img_src_Pane = new JPanel();
	JPanel img_dest_Pane = new JPanel();
	JScrollPane src_scroll = new JScrollPane(img_src_Pane);
	JScrollPane dest_scroll = new JScrollPane(img_dest_Pane);
	
	public mainPane() throws IOException {
		super("./img/christmas.jpg");
		//super("./img/hinh-nen-de-thuong-2.jpg");
		//-----Declaration-----
		//backgroundImage = ImageIO.read(new File("./img/christmas.jpg"));
		
		//-----SetLayout-----
		this.setLayout(null);
		src_Pane.setLayout(null);
		dest_Pane.setLayout(null);
		
		//-----SetSize-----
		src_Pane.setBounds(270,80,360,600);
		dest_Pane.setBounds(640,80,360,600);
		src_scroll.setBounds(30,100,300,400);
		dest_scroll.setBounds(30,100,300,400);
		
		//-----SetBorderColor-----
		//(pour faciliter la vision)
		this.setBorder(new LineBorder(Color.red));
		src_Pane.setBorder(new LineBorder(Color.red));
		dest_Pane.setBorder(new LineBorder(Color.red));
		src_scroll.setBorder(new LineBorder(Color.green));
		dest_scroll.setBorder(new LineBorder(Color.green));
		img_src_Pane.setBorder(new LineBorder(Color.blue));
		img_dest_Pane.setBorder(new LineBorder(Color.blue));
		//this.setIcon(new ImageIcon("./img/jpeg"));
		
		
		//-----Add_Pane-----
		this.add(src_Pane);
		this.add(dest_Pane);
		src_Pane.add(src_scroll);
		dest_Pane.add(dest_scroll);		
		
	}

	public void setImage(JLabel img_src, JLabel img_dest)
	{
		l_src = img_src;
		l_dest = img_dest;
		img_src_Pane.add(l_src);
		img_dest_Pane.add(l_dest);
	}
	
}
