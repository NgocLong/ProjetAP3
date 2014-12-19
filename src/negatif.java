import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**Class chức năng negatif : mỗi thông số trên 1 pixel màu của hình ảnh gốc sẽ được
 *  thay đổi thành thông số màu trái ngược tương ứng
 * @author doanngoclong
 */
public class negatif {
	
	/**Hàm dựng : rỗng */
	negatif(){
	}
	
	/**Hàm thiết kế chức năng negatif */
	public void SetNegatif(BufferedImage img) throws IOException
	{
		// lấy màu từ ảnh gốc
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int rgba = img.getRGB(x, y);
                // đổi thành màu trái ngược
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                                255 - col.getGreen(),
                                255 - col.getBlue());
                // gán vào ảnh đích
                img.setRGB(x, y, col.getRGB());                
            }
        }      
	}
}
