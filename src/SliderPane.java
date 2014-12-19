import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;


public class SliderPane extends JPanelWithBackground {
	
	JSlider slider_1 = new JSlider(JSlider.VERTICAL,0,20,10);
	JSlider slider_2 = new JSlider(JSlider.VERTICAL,0,255,15);
	
	public SliderPane(JPanel mainPane) throws IOException {
		
		//-----Declaration-----
		super("./img/khung.jpg");
		slider_1.setMajorTickSpacing(5);
		slider_1.setMinorTickSpacing(1);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		slider_2.setMajorTickSpacing(51);
		slider_2.setMinorTickSpacing(17);
		slider_2.setPaintTicks(true);
		slider_2.setPaintLabels(true);
		Font font = new Font("Serif", Font.ITALIC, 15);
		slider_1.setFont(font);
		slider_2.setFont(font);
		
		//-----SetLayout-----
		this.setLayout(null);
		
		//-----SetSize-----
		this.setBounds(1040,200,290,400);
		slider_1.setBounds(20,30,120,300);
		slider_2.setBounds(150,30,120,300);
		
		
		//-----SetBorderColor
		//(pour faciliter la vision)
		this.setBorder(new LineBorder(Color.red));
		slider_1.setBackground(null);
		
		
		//-----Add_Slider-----
		this.add(slider_1);
		this.add(slider_2);	
		mainPane.add(this);
		
	}

	public JSlider getSlider1()
	{
		return slider_1;
	}
	public JSlider getSlider2()
	{
		return slider_2;
	}

}
