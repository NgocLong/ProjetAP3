import java.awt.image.*;

/**
 * Cette classe modélise la fonction Rotation.
 * Pour être simple, cette fonction fait, à chaque fois, une rotation de 180 degres à gauche.
 */
public class Rotation 
{
	/**
	 * Variable contenant l'image à faire tourner
	 */
	BufferedImage newImg1;
	
	/**
	 * Constructeur par défaut vide de la classe Rotation
	 */
	Rotation(){
	}
	
	public void setrotation(BufferedImage m_img_dst)
	{
		// Prendre les coordonnées de l'image source		
		int width = m_img_dst.getWidth();						
		int height = m_img_dst.getHeight();
		// Faire tourner l'image
		newImg1 = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
		// Dessiner une nouvelle image avec les nouveaux pixels
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
