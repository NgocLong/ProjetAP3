import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Cette classe modélise un panneau contenant les boutons de traitement.
 * Elle hérite de JPanel.
 */
public class buttonPane extends JPanel {
		
	/**
	 * Variable servant à positionner les boutons par rapport à la fenêtre
	 */
	int position = 125;
	
	/**
	 * Un panneau secondaire aidant à positionner le panneau principal 
	 */
	JPanel sup = new JPanel();
	
	/**
	 * Un panneau principal  
	 */
	JPanelWithBackground but = new JPanelWithBackground("./img/button.jpg");
	
	/**
	 * Constructeur par défaut qui initialise le panneau des boutons
	 */
	public buttonPane() throws IOException {
		super();		
		but.setLayout(null);	
		this.setBorder(new LineBorder(Color.red));		
		this.setPreferredSize(new Dimension(830,130));
		but.setPreferredSize(new Dimension(900,130));
		but.setBorder(new LineBorder(Color.green));
		sup.setPreferredSize(new Dimension(140,130));
		
		this.add(but,BorderLayout.CENTER);
		this.add(sup,BorderLayout.CENTER);
	}
	
	/**
	 * Ajouter un nouveau bouton à une position désirée 
	 * @param b Un nouveau bouton
	 */
	public void addBut(JButton b)
	{
		b.setBounds(position,40,110,50);
		position += 130;
		but.add(b);
	}
}
