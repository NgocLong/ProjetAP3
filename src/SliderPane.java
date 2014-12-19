import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;

/**Class slider chứa 2 thanh kéo phục vụ cho 2 chức năng Scale và Blur hình
 * @author doanngoclong
 */
public class SliderPane extends JPanelWithBackground {
	
	/**Biến thanh kéo 1 - kéo chiều dọc : Scale hình*/
	JSlider slider_1 = new JSlider(JSlider.VERTICAL,0,20,10);
	
	/**Biến thanh kéo 2 - kéo chiều dọc : Blur hình*/
	JSlider slider_2 = new JSlider(JSlider.VERTICAL,0,20,0);
	
	/**Biến label nhãn scale*/
	JLabel l1 = new JLabel("Scale");
	
	/**Biến label nhãn blur*/
	JLabel l2 = new JLabel("Blur");
	
	/**Hàm dựng có background */
	public SliderPane() throws IOException {
		super("./img/thanhkeo.jpg");
		// thiết kế độ chia trung bình và độ chia nhỏ nhất
		slider_1.setMajorTickSpacing(5);
		slider_1.setMinorTickSpacing(1);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		slider_2.setMajorTickSpacing(5);
		slider_2.setMinorTickSpacing(1);
		slider_2.setPaintTicks(true);
		slider_2.setPaintLabels(true);
		// thiết kế font chữ
		Font font = new Font("Serif", Font.ITALIC, 15);
		Font font_label = new Font("Serif", Font.BOLD, 20);
		slider_1.setFont(font);
		slider_2.setFont(font);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(290,400));
		slider_1.setBounds(20,30,120,300);
		slider_2.setBounds(150,30,120,300);
		l1.setBounds(45,330,70,30);
		l2.setBounds(165,330,70,30);
		l1.setFont(font_label);
		l2.setFont(font_label);
		this.setBorder(new LineBorder(Color.red));
		slider_1.setBackground(null);
		this.add(slider_1);
		this.add(slider_2);	
		this.add(l1);
		this.add(l2);		
	}

	/**Hàm trả về thanh kéo 1, để thao tác ở các nơi khác */
	public JSlider getSlider1()
	{
		return slider_1;
	}
	
	/**Hàm trả về thanh kéo 2, để thao tác ở các nơi khác */
	public JSlider getSlider2()
	{
		return slider_2;
	}
}
