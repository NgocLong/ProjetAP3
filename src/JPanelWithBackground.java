import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * Cette classe modélise un panneau avec une image de fond
 * Les autres classe peuvent hériter de cette classe pour avoir une image de fond
 */
public class JPanelWithBackground extends JPanel {
	
	/**
	 * Variable contenant l'image de fond
	 */
	private Image backgroundImage;
	
	/**
	 * Constructeur par défaut prend en paramètre le chemin de l'image de fond
	 * @param fileName le chemin de l'image de fond
	 */
	public JPanelWithBackground(String fileName) throws IOException 
	{
		backgroundImage = ImageIO.read(new File((fileName)));
	}		
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);	    	
	    g.drawImage(backgroundImage, 0, 0, this);
	}

}	
