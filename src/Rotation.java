import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Rotation {

	BufferedImage newImg1;
	
	Rotation(){
	}

  
	public void setrotation(BufferedImage m_img_dst)
	{
		
			int width = m_img_dst.getWidth();						
			int height = m_img_dst.getHeight();
			newImg1 = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
			
			for (int i = 0; i < width; i++)
			     for (int j = 0; j < height; j++)
			     {
			    	 int color1 = m_img_dst.getRGB(i, j);
			         newImg1.setRGB(j,width - i - 1, color1);
			     }
			
			
		
	}
	public BufferedImage getImgRot()
	{
		return newImg1;
	}
	
	
}
