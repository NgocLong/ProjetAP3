import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

/**
 * Cette classe modélise la liste des images à traiter.
 * Cette liste contient au maximum 10 images
 */
public class ListeImgPane extends JPanelWithBackground {
	
	/**
	 * Référence l'objet où sont placés les images
	 */ 
	JPanel l_img_Pane = new JPanel();
	
	/**
	 * Référence l'objet qui initialise la barre de défilement
	 */
	JScrollPane scroll_Pane = new JScrollPane(l_img_Pane);
	
	/**
	 * Constructeur par défaut qui initialise la liste des images à traiter
	 */
	public ListeImgPane() throws IOException 
	{
		super("./img/list.jpg");	
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.red));
		scroll_Pane.setBorder(new LineBorder(Color.red));
		this.setPreferredSize(new Dimension(150,600));
		scroll_Pane.setBounds(10,10,130,500);
		l_img_Pane.setPreferredSize(new Dimension(110,500));
		this.add(scroll_Pane);
	}
	
	/**
	 * Ajouter les images à la barre de défilement
	 */
	public void addImgBut(JButton but_img)
	{
		l_img_Pane.add(but_img);
	}

}
