import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class negatif {

	BufferedImage inputImg = null;
	
	//negatif(BufferedImage img)
	negatif(){
	}
	
	negatif(BufferedImage img)
	{
		inputImg = img;
	}
	
	public void SetNegatif(BufferedImage img) throws IOException
	{
		

		//inputImg = ImageIO.read(new File(url));
		inputImg = img;


        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int rgba = img.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                                255 - col.getGreen(),
                                255 - col.getBlue());
                img.setRGB(x, y, col.getRGB());
                inputImg.setRGB(x, y, col.getRGB());
            }
        }
        
        //img = inputImg;
        
        
        /*
        try {
            File outputFile = new File("invert-"+imageName);
            ImageIO.write(inputFile, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	*/
        
        
        
	}
	
	
	
	
	
	
}
