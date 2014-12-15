import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class LoadImage extends JComponent {
	
	BufferedImage img = new BufferedImage( 1000, 1000 , BufferedImage.TYPE_INT_ARGB);
	BufferedImage img1;
	
	//BufferedImage img_test;
	
    public LoadImage(String url, BufferedImage m_img) {
    	
    	try {
           img1 = ImageIO.read(new File(url));
           m_img = img1;
           //img_test = img1;
       } catch (IOException e) {
       }

    }

    
    public Dimension getPreferredSize() {
        if (img1 == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img1.getWidth(null), img1.getHeight(null));
       }
    }
	
    
    /*
    public void paint(Graphics g) {
    		
    	Graphics2D g2d = img.createGraphics();
    	
    	float[] scales = { 1, 1, 1, 0,5 };
    	float y = 1.5f;
    	float x = 0.5f;
    	float[] offsets = new float[4];
    	RescaleOp rop = new RescaleOp(y,x,null);
    	g2d.drawImage(img1, rop, 0, 0);
    	g.drawImage(img, 0, 0 , null);
    		
    }
	*/
    
	
	public void paint(Graphics g)
	{
		g.drawImage(img1,100,100,null);
		
	}
}