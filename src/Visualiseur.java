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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.File;

/**Ngày hoàn thành : 20-12-2014
 * Người thiết kế :
 * -Doan Ngoc Long, LINF13, Universite PUF-HCM, Vietnam
 * -Mai Trung Hieu, LINF13, Universite PUF-HCM, Vietnam
 * Projet JAVA chỉnh sửa hình ảnh - phục vụ cho việc giảng dạy môn học Programmation Objet - AP3 của 
 * Trung Tâm Đại Học Pháp - ĐHQG.TPHCM - giáo viên : Joris Sansen
 */

/**Lưu ý : 
 * 1/ Việc tùy chỉnh layout trong chương trình có sự kết hợp giữa layout tự động từ JAVA và 
 * tùy chỉnh vị trí tuyệt đối (absolute position) trong 1 số panel để đạt hiệu quả giao diện
 * cao nhất.
 * 2/ Chương trình tải và chỉnh sửa hình ảnh theo phương thức : chỉnh sửa trên BufferedImage,
 * sau đó đưa hình ảnh lên JLabel thông qua ImageIcon để hiện thị 
 */

/**Class Visualiseur - class chính kế thừa từ JFrame hiển thị các thành phần con của projet
 * chứa hàm main() để chạy chương trình
 * công năng các nút (button) và các option (từ menubar)
 * tổng hợp các class khác, tạo giao diện chính của chương trình
 * @author doanngoclong
 */
public class Visualiseur extends JFrame {
	
	/**Biến lưu giữ hình ảnh gốc*/
	private BufferedImage m_img_src = new BufferedImage( 500, 500 , BufferedImage.TYPE_INT_ARGB);
	
	/**Biến giữ hình ảnh đích (được chỉnh sửa)*/
	private BufferedImage m_img_dst = new BufferedImage( 500, 500 , BufferedImage.TYPE_INT_ARGB);
	
	/**Biến đếm số hình trong list hình, tối đa 10 hình*/
	int cpt = 0;
	
	/**Biến cập nhật chiều rộng hình */
	int width;
	
	/**Biến cập nhật chiều dài hình */
	int height;
	
	/**Biến tham chiếu mainPane*/
	mainPane main_Pane;
	
	/**Biến tham chiếu buttonPane*/
	buttonPane but_Pane;
	
	/**Biến tham chiếu SliderPane*/
	SliderPane slider_Pane;
	
	/**Biến tham chiếu ListeImgPane*/
	ListeImgPane liste_img_Pane;
	
	/**Biến label hiển thị hình gốc*/
	JLabel l_src;
	
	/**Biến label hiển thị hình đích*/
	JLabel l_dst;
	
	/**Biến lưu giữ các hình ảnh trong list hình*/
	JButton[] liste_img_but;
	
	/**Biến lưu trữ đường dẫn*/
	String url_src;
	
	/**Biến lưu trữ đường dẫn các hình ảnh trong list hình*/
	String[] url_liste_img_but;
	
	/**Hàm dựng Visualisuer*/
	public Visualiseur() throws IOException {
		super();
		// thiết kế cơ bản : chương trình có size rộng full màn hình
		// tắt chương trình bằng option Close
		this.setSize(this.getToolkit().getScreenSize());
		this.setTitle("Traitement des images");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		// dựng các component từ InitComponent
		InitComponent init = new InitComponent(this);
		init.getOuvrir().addActionListener(new BoutonOuvrirAction());
		init.getSauvegarder().addActionListener(new BoutonSauvegarderAction());
		// tải hình lên label
		l_src = new JLabel(new ImageIcon(m_img_src));
		l_dst = new JLabel(new ImageIcon(m_img_dst));		
		// khai báo các biến từ Panel từ những class khác 
		main_Pane = new mainPane();
		liste_img_Pane = new ListeImgPane();
		slider_Pane = new SliderPane();
		but_Pane = new buttonPane();
		// khai báo các button chứa hình ảnh trong list hình
		liste_img_but = new JButton[10];
		url_liste_img_but = new String[10];
		for(int i=0;i<10;i++)
		{
			liste_img_but[i] = new JButton(""+i);
			liste_img_but[i].setPreferredSize(new Dimension(100,100));
			url_liste_img_but[i] = "";
		}
		// tải ảnh lên biến main_Pane thông qua hàm setImage của class mainPane
		main_Pane.setImage(l_src,l_dst);
		// gán chức năng cho 2 thanh kéo slider_1 và slider_2
		slider_Pane.getSlider1().addChangeListener(new valuesChangeScale());
		slider_Pane.getSlider2().addChangeListener(new valuesChangeBlur());
		// khai báo các nút : chức năng như tên gọi
		JButton b1 = new JButton("Negatif");
		JButton b2 = new JButton("Dark");
		JButton b3 = new JButton("Light");
		JButton b4 = new JButton("Rotation");
		JButton b5 = new JButton("Reset");
		// gán chức năng cho các nút này
		b1.addActionListener(new buttonNegatifAction());		
		b2.addActionListener(new buttonColorDownAction());
		b3.addActionListener(new buttonColorUpAction());
		b4.addActionListener(new buttonRotationAction());
		b5.addActionListener(new buttonResetAction());
		// thêm các nút vào biến but_Pane từ class buttonPane chứa các button chỉnh hình
		but_Pane.addBut(b1);
		but_Pane.addBut(b2);
		but_Pane.addBut(b3);
		but_Pane.addBut(b4);
		but_Pane.addBut(b5);
		// thiết kế layout giao diện cho các Panel 
		this.add(liste_img_Pane,BorderLayout.WEST);
		this.add(main_Pane, BorderLayout.CENTER);
		this.add(slider_Pane,BorderLayout.EAST);
		this.add(but_Pane,BorderLayout.SOUTH);
	}
	
	/**Hàm dùng để mở Dialog đọc file hoặc ghi file tùy theo cách truyền biến (boolean) :
	 * truyền biến Boolean = true để mở Dialog đọc file
	 * truyền biến Boolean = false để mở Dialog ghi file
	 * @param open_save
	 * @return
	 */
	public String getImageFile(boolean open_save) {
		JFileChooser file = new JFileChooser();
		// quét các định dạng file hình
		FileFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "jpeg");
		file.setFileFilter(filter);
		int returnVal;
		if(open_save)
			returnVal = file.showOpenDialog(Visualiseur.this);
		else
			returnVal = file.showSaveDialog(Visualiseur.this);	
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			// lấy đường dẫn
			File f = file.getSelectedFile();
			return f.getPath();
		}
		else
			return null;	
	}
	
	/**Hàm chức năng mở file hình*/
	class BoutonOuvrirAction implements ActionListener {
		@Override		
		public void actionPerformed(ActionEvent e) {
			// xét list hình nếu quá 10 hình sẽ hiện ô thông báo lỗi
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
				// vẽ thumbnail hình khi đưa vào list hình
				BufferedImage k = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
				try {
					k = ImageIO.read(new File(url_src));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int w = k.getWidth();
				int h = k.getHeight();
				double f1 = 1.0/w * 100.0;
				double f2 = 1.0/h * 100.0;
				// scale hình lại với kích cỡ (100,100) để vẽ thumbnail
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
				// ô thông báo lỗi
				JOptionPane jop = new JOptionPane();
				ImageIcon img = new ImageIcon("./img/chui.jpg");
				String mess = "Chèn 10 hình là nhìu rồi đòi thêm bố tương vỡ mồm bây giờ !\n";
				mess += "Muốn thêm hình nữa thì tắt mở lại đi thằng ngu bò ! \n";
				jop.showMessageDialog(null, mess, "Error : List Full", JOptionPane.INFORMATION_MESSAGE, img);
			}
		}
	}
	
	/**Hàm chức năng lưu hình*/
	class BoutonSauvegarderAction implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String url_save = getImageFile(false);
			File outputfile = new File(url_save);
			try {
				ImageIO.write(m_img_dst,"png",outputfile);
			} catch(IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}
	}
	
	/**Hàm chức năng negatif*/
	class buttonNegatifAction implements ActionListener {
		public void actionPerformed( ActionEvent e)
		{
			negatif values_negatif = new negatif();
			try { values_negatif.SetNegatif(m_img_dst); }
			catch(Exception e1) {};	
			l_dst.setIcon(new ImageIcon(m_img_dst));
			repaint();
		}
	}
	
	/**Hàm chức năng Reset trở về như hình gốc ban đầu*/
	class buttonResetAction implements ActionListener {
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
	
	/**Hàm chức năng giảm độ sáng hình ảnh 
	 * độ sáng giảm 10 cho mỗi lần bấm 
	 */
	class buttonColorDownAction implements ActionListener {
		public void actionPerformed( ActionEvent e)
		{	
			colorUpDown value_color = new colorUpDown();
			try {
				value_color.setColorDown(m_img_dst,10);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			l_dst.setIcon(new ImageIcon(m_img_dst));
			repaint();
		}
	}

	/**Hàm chức năng tăng độ sáng hình ảnh
	 * độ sáng tăng 10 cho mỗi lần bấm
	 */
	class buttonColorUpAction implements ActionListener {
		public void actionPerformed( ActionEvent e)
		{	
			colorUpDown value_color = new colorUpDown();
			try {
				value_color.setColorUp(m_img_dst,10);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			l_dst.setIcon(new ImageIcon(m_img_dst));
			repaint();
		}
	}

	/**Hàm chức năng xoay 180 độ hình ảnh về bên trái cho mỗi lần bấm */
	class buttonRotationAction implements ActionListener {
		public void actionPerformed( ActionEvent e)
		{	
			int width = m_img_dst.getWidth();						
			int height = m_img_dst.getHeight();
			
			Rotation rot = new Rotation();
			rot.setrotation(m_img_dst);
			// sau khi xoay chỉnh cho kích thước hình gốc giống kích thước hình mới được xoay
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
	
	/**Hàm chức năng thay đổi hình ảnh chỉnh sửa trong list hình*/
	class buttonChangeImgInListe implements ActionListener {
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

	/**Hàm chức năng Scale hình bằng thanh kéo jslider
	 * @author doanngoclong
	 */
	class valuesChangeScale implements ChangeListener {
		float scale_old_value = 1;
		public void stateChanged(ChangeEvent e) {
		    JSlider source = (JSlider)e.getSource();
		    if (!source.getValueIsAdjusting()) {		    			 
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
	
	/**Hàm chức năng Blur hình bằng thanh kéo jslider
	 * @author doanngoclong
	 */
	class valuesChangeBlur implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
		    if (!source.getValueIsAdjusting()) {		    			 
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
	
	/**Hàm main*/
	public static void main(String[] args) throws IOException 
	{
		Visualiseur visu = new Visualiseur();
		visu.setVisible(true);
	}
}
