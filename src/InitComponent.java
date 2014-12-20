import java.awt.event.*;
import javax.swing.*;

/**
 * Cette classe prépare la barre de menu
 */
public class InitComponent {

	/**
	 * Référence l'objet d'une barre de menu
	 */
	JMenuBar mb = new JMenuBar();
	
	/**
	 * Premier élément de la barre de menu
	 */
	JMenu fichier = new JMenu("Fichier");
	
	/**
	 * Deuxième élément de la barre de menu
	 */
	JMenu aide = new JMenu("Aide");
	
	/**
	 * Sous-élément du menu Fichier
	 */
	JMenuItem ouvrir = new JMenuItem("Ouvrir");
	
	/**
	 * Sous-élément du menu Fichier
	 */
	JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
	
	/**
	 * Sous-élément du menu Fichier
	 */
	JMenuItem quitter = new JMenuItem("Quitter");
	
	/**
	 * Sous-élément du menu Aide
	 */
	JMenuItem apropos = new JMenuItem("A Propos");
	
	/**
	 * Constructeur par défaut qui initialise la barre de menu
	 * @param visu Référence l'objet à la fenêtre principale
	 */
	public InitComponent(Visualiseur visu)
	{
		// Ajouter les éléments au menu Fichier
		fichier.setMnemonic('F');
		fichier.add(ouvrir);
		fichier.add(sauvegarder);
		fichier.add(quitter);
		
		// Affecter une action
		quitter.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
		});
		
		// Ajouter les éléments au menu Aide
		aide.setMnemonic('A');
		aide.add(apropos);
		// Affecter une action
		apropos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane jop = new JOptionPane();
			ImageIcon img = new ImageIcon("./img/clover.jpeg");
			String mess = "Merci d'avoir utilisé notre application !\n";
			mess += "J'espère que vous aurez de bons moments ^^ \n";
			jop.showMessageDialog(null, mess, "À Propos", JOptionPane.INFORMATION_MESSAGE, img);
		}
		});
		mb.add(fichier);
		mb.add(aide);
		visu.setJMenuBar(mb);
		visu.setVisible(true);
	}	
	
	public JMenuItem getOuvrir()
	{
		return ouvrir;
	}
	
	public JMenuItem getSauvegarder()
	{
		return sauvegarder;
	}	
	
}
