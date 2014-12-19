import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**Class thiết kế chức nay xoay hình, để đơn giản hóa projet chỉ thiết kế chức năng
 * xoay hình về bên trái 180 độ cho mỗi lần thực thi
 * @author doanngoclong
 */
public class Rotation {

	/**Biến hình xoay */
	BufferedImage newImg1;
	
	/**Hàm dựng : rỗng */
	Rotation(){
	}

	/**Hàm xây dựng chức năng xoay hình */
	public void setrotation(BufferedImage m_img_dst)
	{
		// lấy thông số rộng - dài hình ảnh gốc		
		int width = m_img_dst.getWidth();						
		int height = m_img_dst.getHeight();
		// dựng ảnh mới với chiều rộng - dài trái ngược
		newImg1 = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
		// vẽ ảnh mới với các pixel xoay ngang từ hình cũ
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
			{
				int color1 = m_img_dst.getRGB(i, j);
				newImg1.setRGB(j,width - i - 1, color1);
			}
	}
	
	/**Hàm trả về biền hình xoay ngang*/
	public BufferedImage getImgRot()
	{
		return newImg1;
	}
	
	
}
