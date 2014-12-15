import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;


public class SliderPane extends JPanel {
	
	public SliderPane() {
		
		//-----Declaration-----
		JSlider slider_1 = new JSlider(JSlider.VERTICAL,0,30,15);
		JSlider slider_2 = new JSlider(JSlider.VERTICAL,0,30,15);
		JSlider slider_3 = new JSlider(JSlider.VERTICAL,0,30,15);
		slider_1.setMajorTickSpacing(10);
		slider_1.setMinorTickSpacing(5);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		slider_2.setMajorTickSpacing(10);
		slider_2.setMinorTickSpacing(5);
		slider_2.setPaintTicks(true);
		slider_2.setPaintLabels(true);
		slider_3.setMajorTickSpacing(10);
		slider_3.setMinorTickSpacing(5);
		slider_3.setPaintTicks(true);
		slider_3.setPaintLabels(true);
		Font font = new Font("Serif", Font.ITALIC, 15);
		slider_1.setFont(font);
		slider_2.setFont(font);
		slider_3.setFont(font);
		
		//-----SetLayout-----
		this.setLayout(null);
		
		//-----SetSize-----
		this.setBounds(1050,200,290,400);
		slider_1.setBounds(10,20,90,300);
		slider_2.setBounds(100,20,90,300);
		slider_3.setBounds(190,20,90,300);
		
		//-----SetBorderColor
		//(pour faciliter la vision)
		this.setBorder(new LineBorder(Color.red));
		slider_1.setBorder(new LineBorder(Color.red));
		slider_2.setBorder(new LineBorder(Color.red));
		slider_3.setBorder(new LineBorder(Color.red));
		
		//-----Add_Slider-----
		this.add(slider_1);
		this.add(slider_2);
		this.add(slider_3);
		
		
		
		
		
	}

}
