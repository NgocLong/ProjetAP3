import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.AffineTransform;

/**
 * Nom du projet: Traitement d'images
 * Fin du projet: 19/12/2014
 *		@author DOAN Ngoc Long
 * 		@author MAI Trung Hieu
 */

/**
 * REMARQUES:
 * 		1. L'organisation des Layouts  s'effectue grâce à la combinaison des layouts primitifs du JAVA et
 * sa position absolue (absolute position) dans certains panneaux pour avoir une bonne interface
 * 		2. Le traitement d'images utilise la méthode du BufferedImage, charge l'image sur JLabel et l'affiche à travers du ImageIcon
 * 		3. Lors de l'exécution du fichier exécutable jar, il faut placer le répertoire image "img" dans le même répertoire 
 * avec les fichiers source code
 */

/**
 * Cette classe modélise une application graphique de visualisation
 * des images. Elle dérive de JFrame, qui est une fenêtre SWING "light".
 * 
 */
public class Visualiseur extends JFrame 
{
	/**
	 * Référence l'objet contenant l'image source
	 */ 
	private BufferedImage m_img_src = new BufferedImage( 500, 500 , BufferedImage.TYPE_INT_ARGB);

	/**
	 * Référence l'objet contenant l'image modifiée (destination)
	 */
	private BufferedImage m_img_dst = new BufferedImage( 500, 500 , BufferedImage.TYPE_INT_ARGB);

	/**
	 * Variable compteur: le nombre d'images dans la liste d'images
	 */
	int cpt = 0;

	/**
	 * Variable contenant la largeur de l'image 
	 */
	int width;

	/**
	 * Variable contenant la longueur de l'image
	 */
	int height;

	/**
	 * Référence l'objet où sont placés tous les composants
	 */
	mainPane main_Pane;

	/**
	 * Référence l'objet où sont placés les boutons
	 */
	buttonPane but_Pane;

	/**
	 * Référence l'objet où sont placés les barres de défilement numérotées 
	 */
	SliderPane slider_Pane;

	/**
	 * Référence l'objet où est placée la liste d'images à traiter 
	 */
	ListeImgPane liste_img_Pane;

	/**
	 * Référence l'objet où est affichée l'image source
	 */
	JLabel l_src;

	/**
	 * Référence l'objet où est affichée l'image modifiée (destination)
	 */
	JLabel l_dst;

	/**
	 * Un tableau contenant les images dans la liste d'images 
	 */
	JButton[] liste_img_but;

	/**
	 * Référence l'objet contenant le chemin de l'image
	 */
	String url_src;

	/**
	 * Référence l'objet contenant le chemin des images dans la liste d'images
	 */
	String[] url_liste_img_but;

	/**
	 *	Constructeur par défaut de la classe Visualiseur qui crée les menus, place les composants 
	 */
	public Visualiseur() throws IOException 
	{
		super();

		this.setSize(this.getToolkit().getScreenSize()); // la fenêtre s'affiche en mode plein d'écran
		this.setTitle("Traitement des images");

		// Indique ce qu'il faut faire si on clic sur "fermer la fenetre".
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);

		// Construire les composants par l'intermédiaire de la classe InitComponent
		InitComponent init = new InitComponent(this);
		init.getOuvrir().addActionListener(new BoutonOuvrirAction());
		init.getSauvegarder().addActionListener(new BoutonSauvegarderAction());

		// Charger les images sur les Label
		l_src = new JLabel(new ImageIcon(m_img_src));
		l_dst = new JLabel(new ImageIcon(m_img_dst));		

		// Construire les panneaux de traitement d'images
		main_Pane = new mainPane();
		liste_img_Pane = new ListeImgPane();
		slider_Pane = new SliderPane();
		but_Pane = new buttonPane();		

		//Construire la liste d'images à traiter
		liste_img_but = new JButton[10];
		url_liste_img_but = new String[10];		
		for(int i=0;i<10;i++)
		{
			liste_img_but[i] = new JButton(""+i);
			liste_img_but[i].setPreferredSize(new Dimension(100,100));
			url_liste_img_but[i] = "";
		}

		// Charger image sur la panneau principal
		main_Pane.setImage(l_src,l_dst);

		// Affecter les fonctions aux barres de défilement numérotées
		slider_Pane.getSlider1().addChangeListener(new valuesChangeScale());
		slider_Pane.getSlider2().addChangeListener(new valuesChangeBlur());

		// Déclarer les boutons correspondant aux fonctions 
		JButton b1 = new JButton("Negatif");
		JButton b2 = new JButton("Dark");
		JButton b3 = new JButton("Light");
		JButton b4 = new JButton("Rotation");
		JButton b5 = new JButton("Reset");

		// Affecter les actions sur ces boutons (Ce qu'il faut faire avec ces boutons)
		b1.addActionListener(new buttonNegatifAction());		
		b2.addActionListener(new buttonColorDownAction());
		b3.addActionListener(new buttonColorUpAction());
		b4.addActionListener(new buttonRotationAction());
		b5.addActionListener(new buttonResetAction());

		// Placer les boutons sur le panneau des boutons 
		but_Pane.addBut(b1);
		but_Pane.addBut(b2);
		but_Pane.addBut(b3);
		but_Pane.addBut(b4);
		but_Pane.addBut(b5);

		// Fixer les layouts pour chaque panneau
		this.add(liste_img_Pane,BorderLayout.WEST);
		this.add(main_Pane, BorderLayout.CENTER);
		this.add(slider_Pane,BorderLayout.EAST);
		this.add(but_Pane,BorderLayout.SOUTH);
	}

	/**
	 * La fonction servant à ouvrir la boite dialogue de sélectionner ou de sauvegarder un fichier
	 * @param open_save distinguer la boite de dialogue Ouvrir ou Sauvegarder
	 * @return le chemin de l'image
	 */
	public String getImageFile(boolean open_save) 
	{
		JFileChooser file = new JFileChooser();
		// Un filtre des extensions possibles à ouvrir
		FileFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "jpeg");
		file.setFileFilter(filter);
		int returnVal;
		if(open_save)
			returnVal = file.showOpenDialog(Visualiseur.this);
		else
			returnVal = file.showSaveDialog(Visualiseur.this);	
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File f = file.getSelectedFile();
			return f.getPath();
		}
		else
			return null;	
	}

	/** 
	 * Cette classe modélise la réaction d'ouverture d'un fichier suite
	 * à un clic sur un bouton. C'est une classe interne à Visualiseur.
	 */
	class BoutonOuvrirAction implements ActionListener 
	{
		@Override		
		public void actionPerformed(ActionEvent e) 
		{
			// La liste d'images à traiter doit contenir au maximum 10 images
			if(cpt < 10)
			{
				url_src = getImageFile(true);		
				BufferedImage in_src;	
				try 
				{
					in_src = ImageIO.read(new File(url_src));
					width = in_src.getWidth();
					height = in_src.getHeight();
					m_img_src = ImageIO.read(new File(url_src));
					m_img_dst = ImageIO.read(new File(url_src));
				}
				catch (IOException e1) {
				}
				l_dst.setIcon(new ImageIcon(m_img_dst));
				l_src.setIcon(new ImageIcon(m_img_src));    

				BufferedImage k = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
				try 
				{
					k = ImageIO.read(new File(url_src));
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				int w = k.getWidth();
				int h = k.getHeight();
				double f1 = 1.0/w * 100.0;
				double f2 = 1.0/h * 100.0;
				// Redimensionner l'image à (100,100)
				AffineTransform tx = new AffineTransform();
				tx.scale(f1,f2);
				AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
				k = op.filter(k, null);
				liste_img_but[cpt].setIcon(new ImageIcon(k));
				liste_img_but[cpt].addActionListener(new buttonChangeImgInListe());
				liste_img_Pane.addImgBut(liste_img_but[cpt]);
				url_liste_img_but[cpt] = url_src;
				liste_img_Pane.revalidate();
				cpt++;
			}
			else
			{				
				JOptionPane jop = new JOptionPane();				
				String mess = "La liste d'images à traiter est pleine \n";
				mess += "Veuillez redémarrer l'application ! \n";
				jop.showMessageDialog(null, mess, "Error : Liste est pleine", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Cette classe modélise la réaction de sauvegarde d'un fichier suite
	 * à un clic sur un bouton. C'est une classe interne à Visualiseur.
	 */
	class BoutonSauvegarderAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String url_save = getImageFile(false);
			File outputfile = new File(url_save);
			try 
			{
				ImageIO.write(m_img_dst,"png", outputfile);
			} 
			catch(IOException e1) 
			{
				e1.printStackTrace();
			}		
		}
	}

	/**
	 * Cette classe modélise la réaction de la fonction Negatif sur une image suite
	 * à un clic sur un bouton. C'est une classe interne à Visualiseur.
	 */
	class buttonNegatifAction implements ActionListener 
	{
		@Override
		public void actionPerformed( ActionEvent e)
		{
			negatif values_negatif = new negatif();
			try 
			{ 
				values_negatif.SetNegatif(m_img_dst); 
			}
			catch(Exception e1) {};	
			l_dst.setIcon(new ImageIcon(m_img_dst));
			repaint();
		}
	}

	/**
	 * Cette classe modélise la réaction de la bouton Reser qui remet l'image à son état initial suite
	 * à un clic sur ce bouton. C'est une classe interne à Visualiseur.
	 */
	class buttonResetAction implements ActionListener 
	{
		@Override
		public void actionPerformed( ActionEvent e)
		{			
			int width = m_img_src.getWidth();
			int height = m_img_src.getHeight();
			m_img_dst = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
			for (int i = 0; i < width; i++)
				for (int j = 0; j < height; j++)
				{
					m_img_dst.setRGB(i,j, m_img_src.getRGB(i, j));
				}
			l_dst.setIcon(new ImageIcon(m_img_dst));
			repaint();
		}
	}

	/**
	 * Cette classe modélise la réaction de la fonction Luminosité sur une image suite
	 * à un clic sur le bouton "Dark" (moins lumineux). C'est une classe interne à Visualiseur.
	 */
	class buttonColorDownAction implements ActionListener 
	{
		@Override
		public void actionPerformed( ActionEvent e)
		{	
			colorUpDown value_color = new colorUpDown();
			try 
			{
				value_color.setColorDown(m_img_dst,10);
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			l_dst.setIcon(new ImageIcon(m_img_dst));
			repaint();
		}
	}

	/**
	 * Cette classe modélise la réaction de la fonction Luminosité sur une image suite
	 * à un clic sur le bouton "Light" (plus lumineux). C'est une classe interne à Visualiseur.
	 */
	class buttonColorUpAction implements ActionListener 
	{
		@Override
		public void actionPerformed( ActionEvent e)
		{	
			colorUpDown value_color = new colorUpDown();
			try 
			{
				value_color.setColorUp(m_img_dst,10); 
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			l_dst.setIcon(new ImageIcon(m_img_dst));
			repaint();
		}
	}

	/**
	 * Cette classe modélise la réaction de la fonction Rotation sur une image suite
	 * à un clic sur le bouton Rotation. C'est une classe interne à Visualiseur.
	 */
	class buttonRotationAction implements ActionListener 
	{
		@Override
		public void actionPerformed( ActionEvent e)
		{	
			int width = m_img_dst.getWidth();						
			int height = m_img_dst.getHeight();

			Rotation rot = new Rotation();
			rot.setrotation(m_img_dst);
			// après avoir fait la rotation, redimensionner l'image source à la taille de l'image modifiée
			m_img_dst = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
				{
					m_img_dst.setRGB(i,j, rot.getImgRot().getRGB(i, j));
				}
			l_dst.setIcon(new ImageIcon(m_img_dst));	
			repaint();
		}
	}	

	/**
	 * Cette classe modélise la réaction lors du passage à une autre image dans la liste d'image à traiter.
	 * C'est une classe interne à Visualiseur.
	 */
	class buttonChangeImgInListe implements ActionListener 
	{
		@Override
		public void actionPerformed( ActionEvent e)
		{			
			JButton source = (JButton)e.getSource();
			String path = source.getText();
			int k = Integer.parseInt(path);
			try
			{
				m_img_src = ImageIO.read(new File(url_liste_img_but[k]));
				m_img_dst = ImageIO.read(new File(url_liste_img_but[k]));
			}
			catch (IOException e1) {
			}
			l_dst.setIcon(new ImageIcon(m_img_dst));
			l_src.setIcon(new ImageIcon(m_img_src));    

			repaint();
		}
	}

	/**
	 * Cette classe modélise la réaction de la fonction Redimensionnement sur une image suite
	 * au déplacement de la barre de défilement. C'est une classe interne à Visualiseur.
	 */
	class valuesChangeScale implements ChangeListener 
	{
		float scale_old_value = 1;
		public void stateChanged(ChangeEvent e) 
		{
			JSlider source = (JSlider)e.getSource();
			if (!source.getValueIsAdjusting()) 
			{		    			 
				float scale = (float)source.getValue()/10;
				int new_width = (int)(width*scale);
				int new_height = (int)(height*scale);		        
				l_dst.setIcon(new ImageIcon(m_img_dst.getScaledInstance((int)(width*scale), (int)(height*scale), Image.SCALE_FAST)));		       		       		        
				AffineTransform tx = new AffineTransform();
				tx.scale(scale/scale_old_value, scale/scale_old_value);
				AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
				m_img_dst = op.filter(m_img_dst, null);		        
				scale_old_value = scale;		        		    	
				repaint();
			}
		}
	}

	/**
	 * Cette classe modélise la réaction de la fonction Flou sur une image suite
	 * au déplacement de la barre de défilement. C'est une classe interne à Visualiseur.
	 */
	class valuesChangeBlur implements ChangeListener 
	{
		public void stateChanged(ChangeEvent e) 
		{
			JSlider source = (JSlider)e.getSource();
			if (!source.getValueIsAdjusting()) 
			{		    			 
				float blur_value = (float)source.getValue();
				if(blur_value != 0)
				{
					float[] matrix = new float[(int)(blur_value*blur_value)];
					for (int i = 0; i < (int)(blur_value*blur_value); i++)
						matrix[i] = 1.0f/blur_value;				
					BufferedImageOp op = new ConvolveOp( new Kernel((int)blur_value, (int)blur_value, matrix), ConvolveOp.EDGE_NO_OP, null );
					m_img_dst = op.filter(m_img_src, m_img_dst);
					l_dst.setIcon(new ImageIcon(m_img_dst));
					slider_Pane.revalidate();
					revalidate();
					repaint();
				}
			}
		}
	}	

	/**
	 * Créer une application Visualiseur
	 * @param args
	 */
	public static void main(String[] args) throws IOException 
	{
		Visualiseur visu = new Visualiseur();
		visu.setVisible(true);
	}
}
