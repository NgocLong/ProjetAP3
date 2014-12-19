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

/**Class dựng menu cho chương trình gồm các tính năng : mở file, save file, thoát
 * phụ lục : giới thiệu về producters
 * @author doanngoclong
 */
public class InitComponent {

	/**Biến menubar : chứa các công cụ trong menu */
	JMenuBar mb = new JMenuBar();
	
	/**Biến fichier : chứa các công cụ đọc-ghi file*/
	JMenu fichier = new JMenu("Fichier");
	
	/**Biến phụ lục : giới thiệu*/
	JMenu aide = new JMenu("Aide");
	
	/**Biến mở file*/
	JMenuItem ouvrir = new JMenuItem("Ouvrir");
	
	/**Biến lưu file*/
	JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
	
	/**Biến thoát chương trình*/
	JMenuItem quitter = new JMenuItem("Quitter");
	
	/**Biến phụ lục*/
	JMenuItem apropos = new JMenuItem("A Propos");
	
	/**Hàm dựng*/
	public InitComponent(Visualiseur visu)
	{
		// thêm các công cụ vào option Fichier
		fichier.setMnemonic('F');
		fichier.add(ouvrir);
		fichier.add(sauvegarder);
		fichier.add(quitter);
		// gán chức năng nút thoát
		quitter.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
		});
		// thêm các công cụ vào option Aide 
		aide.setMnemonic('A');
		aide.add(apropos);
		// gán chức năng nút A Propos De : mở 1 hộp thoại giới thiệu về projet
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
		mb.add(aide);
		visu.setJMenuBar(mb);
		visu.setVisible(true);
	}
	
	/**Hàm trả về biến mở file
	 * phục vụ cho việc thao tác tính năng trên biến ouvrir ở những class khác
	 * @return
	 */
	public JMenuItem getOuvrir()
	{
		return ouvrir;
	}
	
	/**Hàm trả về biến lưu file
	 * phục vụ cho việc thao tác tính năng trên biến sauvegarder ở những class khác
	 * @return
	 */
	public JMenuItem getSauvegarder()
	{
		return sauvegarder;
	}
}
