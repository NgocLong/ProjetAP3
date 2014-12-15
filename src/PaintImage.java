import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class PaintImage {
	
	PaintImage()
	{
		
	}
	
	public void paint(Graphics g, BufferedImage img_paint)
	{
		g.drawImage(img_paint,150,150,null);
		
	}
	
}
