import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;


public class InitComponent {

	public InitComponent(Visualiseur visu)
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
		visu.setJMenuBar(mb);
		visu.setVisible(true);
		visu.add(tb, BorderLayout.NORTH);
		//ouvrir.addActionListener(new BoutonOuvrirAction());
		//sauvegarder.addActionListener(new BoutonSauvegarderAction());
	}
	
}
