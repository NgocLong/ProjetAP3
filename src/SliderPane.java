import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Cette classe modélise les barres de défilement servant aux fonctions Flou et Redimensionnement
 */
public class SliderPane extends JPanelWithBackground {
	
	/**
	 * Référence l'objet d'une barre de défilement Redimensionnement
	 */
	JSlider slider_1 = new JSlider(JSlider.VERTICAL,0,20,10);
	
	/**
	 * Référence l'objet d'une barre de défilement Flou
	 */
	JSlider slider_2 = new JSlider(JSlider.VERTICAL,0,20,0);
	
	/**
	 * Un libellé Redimensionnement
	 */
	JLabel l1 = new JLabel("Scale");
	
	/**
	 * Un libellé Flou
	 */
	JLabel l2 = new JLabel("Blur");
	
	/**
	 * Constructeur par défaut qui initialise les barres de défilement servant aux fonctions Flou et Redimensionnement
	 */
	public SliderPane() throws IOException {
		super("./img/thanhkeo.jpg");
		// Initialiser les barres avec la graduation
		slider_1.setMajorTickSpacing(5);
		slider_1.setMinorTickSpacing(1);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		slider_2.setMajorTickSpacing(5);
		slider_2.setMinorTickSpacing(1);
		slider_2.setPaintTicks(true);
		slider_2.setPaintLabels(true);
		
		Font font = new Font("Serif", Font.ITALIC, 15);
		Font font_label = new Font("Serif", Font.BOLD, 20);
		slider_1.setFont(font);
		slider_2.setFont(font);
		
		this.setLayout(null);
		this.setPreferredSize(new Dimension(290,400));
		slider_1.setBounds(20,30,120,300);
		slider_2.setBounds(150,30,120,300);
		l1.setBounds(45,330,70,30);
		l2.setBounds(165,330,70,30);
		l1.setFont(font_label);
		l2.setFont(font_label);
		this.setBorder(new LineBorder(Color.red));
		slider_1.setBackground(null);
		this.add(slider_1);
		this.add(slider_2);	
		this.add(l1);
		this.add(l2);		
	}
	
	public JSlider getSlider1()
	{
		return slider_1;
	}	
	
	public JSlider getSlider2()
	{
		return slider_2;
	}
}
