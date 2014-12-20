import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Cette classe modélise les 2 panneaux contenant l'image source et l'image modifiée
 * L'image source n'est jamais modifiée mais s'affiche quand même pour faire la comparaison des 2 images
 */
public class mainPane extends JPanelWithBackground {
	
	/**
	 * Référence l'objet où est affichée l'image source
	 */
	private JLabel l_src = new JLabel();
	
	/**
	 * Référence l'objet où est affichée l'image modifiée (destination)
	 */
	private JLabel l_dest = new JLabel();
	
	/**
	 * Un panneau de l'image source avec une image de fond 
	 */
	JPanelWithBackground src_Pane = new JPanelWithBackground("./img/khunghinh.jpg");
	
	/**
	 * Un panneau de l'image modifiée avec une image de fond 
	 */
	JPanelWithBackground dest_Pane = new JPanelWithBackground("./img/khunghinh.jpg");
	
	/**
	 * Un panneau secondaire contenant l'image source attachée avec une barre de défilement src_scroll
	 */
	JPanel img_src_Pane = new JPanel();
	
	/**
	 * Un panneau secondaire contenant l'image modifiée attachée avec une barre de défilement dest_scroll
	 */
	JPanel img_dest_Pane = new JPanel();
	
	/**
	 * Référence l'objet de la barre de défilement contenant l'image source
	 */
	JScrollPane src_scroll = new JScrollPane(img_src_Pane);
	
	/**
	 * Référence l'objet de la barre de défilement contenant l'image modifiée
	 */
	JScrollPane dest_scroll = new JScrollPane(img_dest_Pane);
	
	/**
	 * Constructeur par défaut du panneau avec une image de fond
	 */
	public mainPane() throws IOException 
	{
		super("./img/christmas.jpg");
		src_Pane.setLayout(null);
		dest_Pane.setLayout(null);
		
		// Fixer la position absolue du panneauutto 
		src_scroll.setBounds(30,30,390,500);
		dest_scroll.setBounds(30,30,390,500);
		src_Pane.setPreferredSize(new Dimension(450,570));
		dest_Pane.setPreferredSize(new Dimension(450,570));
				
		// Dessiner les bordures
		this.setBorder(new LineBorder(Color.red));
		src_Pane.setBorder(new LineBorder(Color.red));
		dest_Pane.setBorder(new LineBorder(Color.red));
		src_scroll.setBorder(new LineBorder(Color.green));
		dest_scroll.setBorder(new LineBorder(Color.green));
		img_src_Pane.setBorder(new LineBorder(Color.blue));
		img_dest_Pane.setBorder(new LineBorder(Color.blue));		
	
		
		src_Pane.add(src_scroll);
		dest_Pane.add(dest_scroll);
		
		// Placer ces 2 panneaux dans le panneau principal
		this.add(src_Pane,BorderLayout.NORTH);
		this.add(dest_Pane,BorderLayout.NORTH);		
	}

	/**
	 * 
	 */
	public void setImage(JLabel img_src, JLabel img_dest)
	{
		l_src = img_src;
		l_dest = img_dest;
		img_src_Pane.add(l_src);
		img_dest_Pane.add(l_dest);
	}
}
