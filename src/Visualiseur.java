import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;

public class Visualiseur extends JFrame {
	
	//private static BufferedImage m_img = null;
	static BufferedImage m_img_src = new BufferedImage( 1000, 1000 , BufferedImage.TYPE_INT_ARGB);
	static BufferedImage m_img = new BufferedImage( 1000, 1000 , BufferedImage.TYPE_INT_ARGB);
	String url_src = new String();
	
	public Visualiseur() {
	super();
	this.setSize(this.getToolkit().getScreenSize());
	this.setTitle("Traitement des images");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	this.setResizable(false);
	initComponent();

	String url = "./img/clover.jpeg";
	
	
	BufferedImage test = new BufferedImage(150,150,BufferedImage.TYPE_INT_ARGB);
	BufferedImage img_src = new BufferedImage(150,150,BufferedImage.TYPE_INT_ARGB);
	
	int width = 0, height = 0;
	try {
           test = ImageIO.read(new File(url));
           img_src = ImageIO.read(new File(url));
           //m_img = ImageIO.read(new File(url_src));
           width = test.getWidth();
           height = test.getHeight();
       } catch (IOException e) {
       }
	repaint();
	//ImageIcon icon3 = new ImageIcon(test);
	JLabel l1 = new JLabel(new ImageIcon(test));//"./img/clover.jpeg"));
	JLabel l2 = new JLabel(new ImageIcon(m_img));//"./img/clover.jpeg"));
	
	JLabel l3 = new JLabel(new ImageIcon("./img/night.jpeg"));
	JLabel l4 = new JLabel(new ImageIcon("./img/night.jpeg"));
	//l2 = new JLabel(new ImageIcon(test));

	
	mainPane main_Pane = new mainPane();
	buttonPane but_Pane = new buttonPane(this);
	main_Pane.setImage(l1,l2);
	main_Pane.add(but_Pane);
	JButton b1 = new JButton("Negatif");
	JButton b2 = new JButton("Ouvrir");
	
	b1.addActionListener(new buttonNegatifAction());
	b2.addActionListener(new BoutonOuvrirAction());
	
	but_Pane.add(b1);
	but_Pane.add(b2);
	
	//main1.setImage(l3,l4);
	this.add(main_Pane, BorderLayout.CENTER);
	}
/**
* Méthode initComponent():
* Assume la fonction de charger la barre de menu et la barre d'outils
*/
	public void initComponent()
	{
		JToolBar tb = new JToolBar();
		JButton b1 = new JButton(new ImageIcon("img/Pointer_6127.png"));
		JButton b2 = new JButton(new ImageIcon("img/ZoomToFit.png"));
		tb.add(b1);
		tb.add(b2);
		JMenuBar mb = new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		JMenu edition = new JMenu("Edition");
		JMenu outils = new JMenu("Outils");
		JMenu aide = new JMenu("Aide");
		JMenuItem ouvrir = new JMenuItem("Ouvrir");
		JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
		JMenuItem quitter = new JMenuItem("Quitter");
		JMenuItem copier = new JMenuItem("Copier");
		JMenuItem coller = new JMenuItem("Coller");
		JMenuItem test = new JMenuItem("Test");
		JMenuItem choix1 = new JMenuItem("Choix 1");
		JMenuItem choix2 = new JMenuItem("Choix 2");
		JMenuItem apropos = new JMenuItem("A Propos");
		fichier.setMnemonic('F');
		fichier.add(ouvrir);
		fichier.add(sauvegarder);
		fichier.add(quitter);
		quitter.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
		});
		edition.setMnemonic('E');
		edition.add(copier);
		edition.add(coller);
		edition.addSeparator();
		outils = new JMenu("Outils");
		outils.add(choix1);
		outils.add(choix2);
		edition.add(outils);
		aide.setMnemonic('A');
		aide.add(apropos);
		apropos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane jop = new JOptionPane();
			ImageIcon img = new ImageIcon("./img/clover.jpeg");
			String mess = "Merci d'avoir utilisé notre application !\n";
			mess += "J'espère que vous avez eu de bons moments ^^ \n";
			jop.showMessageDialog(null, mess, "À Propos", JOptionPane.INFORMATION_MESSAGE, img);
		}
		});
		mb.add(fichier);
		mb.add(edition);
		mb.add(aide);
		this.setJMenuBar(mb);
		this.setVisible(true);
		this.add(tb, BorderLayout.NORTH);
		ouvrir.addActionListener(new BoutonOuvrirAction());
		sauvegarder.addActionListener(new BoutonSauvegarderAction());
	}
	
	class BoutonOuvrirAction implements ActionListener {
		@Override
//		public void actionPerformed(ActionEvent e) {
//		// k cần getDource() (trả về objet của control)
//		JFileChooser file = new JFileChooser();
//		int returnVal = file.showOpenDialog(Visualiseur.this);
//		if (returnVal == JFileChooser.APPROVE_OPTION){
//			File f = file.getSelectedFile();
//		}
//		}
		
		public void actionPerformed(ActionEvent e) {
			url_src = getImageFile();
			try { m_img = ImageIO.read(new File(url_src)); }
			catch (Exception e1){};
			//if (url_src != null)
			//{
				//Toolkit kit = Toolkit.getDefaultToolkit();
			//}
			repaint();
			//revalidate();
		}
		public String getImageFile() {
			JFileChooser file = new JFileChooser();
			FileFilter filter = new FileNameExtensionFilter("Images", "jpg", "png");
			file.setFileFilter(filter);
			int returnVal = file.showOpenDialog(Visualiseur.this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				File f = file.getSelectedFile();
				return f.getPath();
			}
			else
				return null;	
		}
		
	}
		
	class BoutonSauvegarderAction implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e){
		JFileChooser file = new JFileChooser();
		int returnVal = file.showSaveDialog(Visualiseur.this);
		if (returnVal == JFileChooser.APPROVE_OPTION){
		}
	}
	
				
	}
	
	class buttonNegatifAction implements ActionListener {
		public void actionPerformed( ActionEvent e)
		{
			negatif values_negatif = new negatif();
			try { values_negatif.SetNegatif(m_img);
			
			} catch(Exception e1) {};	
			repaint();
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		Visualiseur visu = new Visualiseur();
		visu.setVisible(true);
		
	}
	
	
}
