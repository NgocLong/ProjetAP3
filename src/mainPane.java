import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

/**Class chứa-hiển thị hình ảnh cần chỉnh sửa (bên phải) và hình ảnh gốc (bên trái)
 * hình ảnh gốc sẽ không đổi để đảm bảo yêu cầu so sánh với hình ảnh được chỉnh sửa
 * @author doanngoclong
 */
public class mainPane extends JPanelWithBackground {
	
	/**Biến label chứa hình ảnh gốc*/
	private JLabel l_src = new JLabel();
	
	/**Biến label chứa hình ảnh đích*/
	private JLabel l_dest = new JLabel();
	
	/**Biến Panel có background chứa hình ảnh gốc */
	JPanelWithBackground src_Pane = new JPanelWithBackground("./img/khunghinh.jpg");
	
	/**Biến Panel có background chứa hình ảnh đích */
	JPanelWithBackground dest_Pane = new JPanelWithBackground("./img/khunghinh.jpg");
	
	/**Biến phụ Panel chứa ảnh gốc được đính kèm vào biến thanh cuộn src_scroll */
	JPanel img_src_Pane = new JPanel();
	
	/**Biến phụ Panel chứa ảnh đích được đính kèm vào biến thanh cuộn dest_scroll */
	JPanel img_dest_Pane = new JPanel();
	
	/**Biến thanh cuộn scroll chứa hình ảnh gốc, thanh cuộn sẽ xuất hiện khi
	 * kích cỡ hình ảnh vượt ra khỏi khung Panel
	 */
	JScrollPane src_scroll = new JScrollPane(img_src_Pane);
	
	/**Biến thanh cuộn scroll chứa hình ảnh đích, thanh cuộn sẽ xuất hiện khi
	 * kích cỡ hình ảnh vượt ra khỏi khung Panel
	 */
	JScrollPane dest_scroll = new JScrollPane(img_dest_Pane);
	
	/**Hàm dựng với background*/
	public mainPane() throws IOException {
		super("./img/christmas.jpg");
		src_Pane.setLayout(null);
		dest_Pane.setLayout(null);
		// thiết kế vị trí tuyệt đối (absolute position) cho panel chứa hình
		src_scroll.setBounds(30,150,390,500);
		dest_scroll.setBounds(30,150,390,500);
		src_Pane.setPreferredSize(new Dimension(450,800));
		dest_Pane.setPreferredSize(new Dimension(450,800));
		// vẽ viền
		this.setBorder(new LineBorder(Color.red));
		src_Pane.setBorder(new LineBorder(Color.red));
		dest_Pane.setBorder(new LineBorder(Color.red));
		src_scroll.setBorder(new LineBorder(Color.green));
		dest_scroll.setBorder(new LineBorder(Color.green));
		img_src_Pane.setBorder(new LineBorder(Color.blue));
		img_dest_Pane.setBorder(new LineBorder(Color.blue));
		src_Pane.add(src_scroll);
		dest_Pane.add(dest_scroll);
		// thêm 2 panel chứa hình vào panel chính
		this.add(src_Pane,BorderLayout.CENTER);
		this.add(dest_Pane,BorderLayout.CENTER);		
	}

	/**Hàm dùng để lấy truyền biến label chứa hình từ nơi khác */
	public void setImage(JLabel img_src, JLabel img_dest)
	{
		l_src = img_src;
		l_dest = img_dest;
		img_src_Pane.add(l_src);
		img_dest_Pane.add(l_dest);
	}
}
