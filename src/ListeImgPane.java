import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

/**Class chứa list các hình ảnh được đưa vào chương trình để chỉnh sửa
 * tối đa lưu giữ được 10 hình, nếu quá sẽ hiện hộp thoại lỗi
 * các hình được vẽ thumbnail và đưa vào panel
 * @author doanngoclong
 */
public class ListeImgPane extends JPanelWithBackground {
	
	/**Biến panel chính để chứa hình */ 
	JPanel l_img_Pane = new JPanel();
	
	/**Biến scroll thanh cuộn */
	JScrollPane scroll_Pane = new JScrollPane(l_img_Pane);
	
	/**Hàm dựng */
	public ListeImgPane() throws IOException {
		// dựng background hình kế thừa từ class JPanelWithBackground
		super("./img/list.jpg");	
		this.setLayout(null);
		// kẻ viền
		this.setBorder(new LineBorder(Color.red));
		scroll_Pane.setBorder(new LineBorder(Color.red));
		// tùy chỉnh kích thước
		this.setPreferredSize(new Dimension(150,600));
		scroll_Pane.setBounds(10,10,130,835);
		l_img_Pane.setPreferredSize(new Dimension(110,1050));
		this.add(scroll_Pane);
	}
	
	/**Hàm dùng để thêm các hình vào thanh cuộn của list hình */
	public void addImgBut(JButton but_img)
	{
		l_img_Pane.add(but_img);
	}

}
