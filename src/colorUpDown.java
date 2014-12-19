import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class colorUpDown {
	
	colorUpDown(){
	}

	public void setColorDown(BufferedImage img, int value_color) throws IOException
	{
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int rgba = img.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(col.getRed() - col.getRed()/value_color,
                                col.getGreen() - col.getGreen()/value_color,
                                col.getBlue() - col.getBlue()/value_color);
                img.setRGB(x, y, col.getRGB());                
            }
        }      
	}
	
	public void setColorUp(BufferedImage img, int value_color) throws IOException
	{
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int rgba = img.getRGB(x, y);
                Color col = new Color(rgba, true);
                
                int red = col.getRed() + col.getRed()/value_color;; 
                int green = col.getGreen() + col.getGreen()/value_color;
                int blue = col.getBlue() + col.getBlue()/value_color;
                
                if(red > 255)
                	red = col.getRed();
                if(green > 255)
                	green = col.getGreen();
                if(blue > 255)
                	blue = col.getBlue();
                
                col = new Color(red,green,blue);
                img.setRGB(x, y, col.getRGB());                
            }
        }      
	}
	
	
}
