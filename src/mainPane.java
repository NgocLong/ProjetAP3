import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;


public class mainPane extends JPanel {
	
	SliderPane slider_pane = new SliderPane();
	private JLabel l_src = new JLabel();
	private JLabel l_dest = new JLabel();
	JPanel src_Pane = new JPanel();
	JPanel dest_Pane = new JPanel();
	JPanel img_src_Pane = new JPanel();
	JPanel img_dest_Pane = new JPanel();
	JScrollPane src_scroll = new JScrollPane(img_src_Pane);
	JScrollPane dest_scroll = new JScrollPane(img_dest_Pane);
	
	public mainPane(){
	
		//-----Declaration-----
		
		//-----SetLayout-----
		this.setLayout(null);
		src_Pane.setLayout(null);
		dest_Pane.setLayout(null);
		
		//-----SetSize-----
		src_Pane.setBounds(270,80,360,600);
		dest_Pane.setBounds(640,80,360,600);
		src_scroll.setBounds(30,100,300,400);
		dest_scroll.setBounds(30,100,300,400);
		img_src_Pane.setPreferredSize(new Dimension(500,500));
		img_dest_Pane.setPreferredSize(new Dimension(500,500));
		
		//-----SetBorderColor-----
		//(pour faciliter la vision)
		this.setBorder(new LineBorder(Color.red));
		src_Pane.setBorder(new LineBorder(Color.red));
		dest_Pane.setBorder(new LineBorder(Color.red));
		src_scroll.setBorder(new LineBorder(Color.red));
		dest_scroll.setBorder(new LineBorder(Color.red));
		img_src_Pane.setBorder(new LineBorder(Color.blue));
		img_dest_Pane.setBorder(new LineBorder(Color.blue));
		
		//-----Add_Pane-----
		this.add(src_Pane);
		this.add(dest_Pane);
		src_Pane.add(src_scroll);
		dest_Pane.add(dest_scroll);
		this.add(slider_pane);
		
	}

	public void setImage(JLabel img_src, JLabel img_dest)
	{
		l_src = img_src;
		l_dest = img_dest;
		img_src_Pane.add(l_src);
		img_dest_Pane.add(l_dest);
	}
	
	
	
}
