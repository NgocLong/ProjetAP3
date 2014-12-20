import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Cette classe modélise la fonction Luminosité. 
 */
public class colorUpDown {
	
	/**
	 * Constructeur par défaut vide
	 */
	colorUpDown(){
	}
	
	/**
	 * Fonction d'augmenter la luminosité
	 * @param img
	 * @param value_color	  
	 */
	public void setColorDown(BufferedImage img, int value_color) throws IOException
	{
		// Prendre les pixels de l'image source
        for (int x = 0; x < img.getWidth(); x++) 
        {
            for (int y = 0; y < img.getHeight(); y++) 
            {
                int rgba = img.getRGB(x, y);
                Color col = new Color(rgba, true);
                // Diminuer la luminosité
                col = new Color(col.getRed() - col.getRed()/value_color,
                                col.getGreen() - col.getGreen()/value_color,
                                col.getBlue() - col.getBlue()/value_color);
                img.setRGB(x, y, col.getRGB());                
            }
        }      
	}
	
	/**
	 * Fonction de diminuer la luminosité
	 * @param img
	 * @param value_color
	 */
	public void setColorUp(BufferedImage img, int value_color) throws IOException
	{
		// Prendre les pixels de l'image source
        for (int x = 0; x < img.getWidth(); x++) 
        {
            for (int y = 0; y < img.getHeight(); y++) 
            {
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
