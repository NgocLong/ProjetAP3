import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Cette classe modélise la fonction Négatif.
 */
public class negatif {
	
	/**
	 * Constructeur par défaut vide
	 */
	negatif(){
	}	

	public void SetNegatif(BufferedImage img) throws IOException
	{
		// Prendre les pixels de l'image source
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int rgba = img.getRGB(x, y);
                // Faire la transformation
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                                255 - col.getGreen(),
                                255 - col.getBlue());
                // Affecter à l'image modifiée
                img.setRGB(x, y, col.getRGB());                
            }
        }      
	}
}
