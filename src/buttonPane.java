import java.awt.BorderLayout;
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

/**Class Panel chứa các button chỉnh sửa hình ảnh 
 *  mỗi nút nhận 1 nhiệm vụ riêng, các chức năng được gán trong Visualiseur 
 *  */
public class buttonPane extends JPanel {
		
	/**Biến để canh vị trí các button */
	int position = 125;
	
	/**Panel hổ trợ để canh layout cho panel chính 
	 * phải có cái panel này chêm đằng trước nếu không borderlayout sẽ làm
	 * cái panel chính ép sát dí vào bên trái
	 * */
	JPanel sup = new JPanel();
	
	/**Panel chính chứa button */
	JPanelWithBackground but = new JPanelWithBackground("./img/button.jpg");
	
	/**Hàm dựng */
	public buttonPane() throws IOException {
		super();		
		but.setLayout(null);
		// kẻ màu viền (border) cho panel
		this.setBorder(new LineBorder(Color.red));
		// điểu chỉnh kích thước
		// sử dụng setPreferredSize để kích thước có thể tương thích với nhiều hệ máy khác nhau
		this.setPreferredSize(new Dimension(830,130));
		but.setPreferredSize(new Dimension(900,130));
		but.setBorder(new LineBorder(Color.green));
		sup.setPreferredSize(new Dimension(140,130));
		// tùy chỉnh layout, ở đây sử dụng BorderLayout
		this.add(but,BorderLayout.CENTER);
		this.add(sup,BorderLayout.CENTER);
	}
	
	/**Hàm dùng đẻ thêm button theo vị trí mong muốn
	 * nếu add thông thường vị trí sẽ không được đẹp
	 * @param b
	 */
	public void addBut(JButton b)
	{
		b.setBounds(position,40,110,50);
		position += 130;
		but.add(b);
	}
}
