import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**Class Panel được vẽ background hình ảnh
 * có thể kế thừa class này thay vì JPanel để tạo background cho panel
 * */
public class JPanelWithBackground extends JPanel {

	/**Biến hình background*/
	private Image backgroundImage;
	
	/**Hàm dựng 
	 * truyền biến đường dẫn file hình dùng để làm background
	 * @param fileName
	 * @throws IOException
	 */
	public JPanelWithBackground(String fileName) throws IOException {
		backgroundImage = ImageIO.read(new File((fileName)));
	  }	
	
	/**Hàm vẽ background sử dụng Graphics-drawImage */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    	// vẽ hình nền lên Panel
	    	g.drawImage(backgroundImage, 0, 0, this);
	  }

}	