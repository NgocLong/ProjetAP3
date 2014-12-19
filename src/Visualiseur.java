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

public class Visualiseur extends JFrame {

	private BufferedImage m_img_src = new BufferedImage( 500, 500 , BufferedImage.TYPE_INT_ARGB);
	private BufferedImage m_img_dst = new BufferedImage( 500, 500 , BufferedImage.TYPE_INT_ARGB);
	int cpt = 0;
	int width;
	int height;
	mainPane main_Pane;
	buttonPane but_Pane;
	SliderPane slider_Pane;
	ListeImgPane liste_img_Pane;
	JLabel l_src;
	JLabel l_dst;
	JButton[] liste_img_but;
	String url_src;
	String[] url_liste_img_but;
	
	public Visualiseur() throws IOException {
		super();
		this.setSize(this.getToolkit().getScreenSize());
		this.setTitle("Traitement des images");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		InitComponent init = new InitComponent(this);
		init.getOuvrir().addActionListener(new BoutonOuvrirAction());
		init.getSauvegarder().addActionListener(new BoutonSauvegarderAction());
		
		l_src = new JLabel(new ImageIcon(m_img_src));
		l_dst = new JLabel(new ImageIcon(m_img_dst));		
		
		main_Pane = new mainPane();
		but_Pane = new buttonPane(main_Pane);
		slider_Pane = new SliderPane(main_Pane);
		liste_img_Pane = new ListeImgPane(main_Pane);
		
		
		liste_img_but = new JButton[10];
		url_liste_img_but = new String[10];
		for(int i=0;i<10;i++)
		{
			liste_img_but[i] = new JButton(""+i);
			liste_img_but[i].setPreferredSize(new Dimension(100,100));
			url_liste_img_but[i] = "";
		}

		main_Pane.setImage(l_src,l_dst);		
		
		slider_Pane.getSlider1().addChangeListener(new valuesChangeScale());
		slider_Pane.getSlider2().addChangeListener(new valuesChangeBlur());
		
		main_Pane.add(liste_img_Pane);
		
		JButton b1 = new JButton("Negatif");
		JButton b2 = new JButton("Dark");
		JButton b3 = new JButton("Light");
		JButton b4 = new JButton("Rotation");
		JButton b5 = new JButton("Reset");
		//JButton b6 = new JButton("Blur");
		
		b1.addActionListener(new buttonNegatifAction());		
		b2.addActionListener(new buttonColorDownAction());
		b3.addActionListener(new buttonColorUpAction());
		b4.addActionListener(new buttonRotationAction());
		b5.addActionListener(new buttonResetAction());
		//b6.addActionListener(new BoutonBlurJobAction());
		
		but_Pane.addBut(b1);
		but_Pane.addBut(b2);
		but_Pane.addBut(b3);
		but_Pane.addBut(b4);
		but_Pane.addBut(b5);
		//but_Pane.addBut(b6);
		
		this.add(main_Pane, BorderLayout.CENTER);
	}
	
	public String getImageFile(boolean open_save) {
		JFileChooser file = new JFileChooser();
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
	
	class BoutonOuvrirAction implements ActionListener {
		@Override		
		public void actionPerformed(ActionEvent e) {
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

				liste_img_but[cpt].setIcon(new ImageIcon(url_src));
				liste_img_but[cpt].addActionListener(new buttonChangeImgInListe());
				liste_img_Pane.addImgBut(liste_img_but[cpt]);
				url_liste_img_but[cpt] = url_src;
				
				liste_img_Pane.revalidate();
				liste_img_Pane.remove(liste_img_but[cpt]);
				liste_img_Pane.revalidate();
				cpt++;
			}
			else
			{
				JOptionPane jop = new JOptionPane();
				ImageIcon img = new ImageIcon("./img/chui.jpg");
				String mess = "Chèn 10 hình là nhìu rồi đòi thêm bố tương vỡ mồm bây giờ !\n";
				mess += "Muốn thêm hình nữa thì tắt mở lại đi thằng ngu bò ! \n";
				jop.showMessageDialog(null, mess, "Error : List Full", JOptionPane.INFORMATION_MESSAGE, img);
			}
		}
	}
	
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

	class buttonRotationAction implements ActionListener {
		public void actionPerformed( ActionEvent e)
		{	
			int width = m_img_dst.getWidth();						
			int height = m_img_dst.getHeight();
			
			Rotation rot = new Rotation();
			rot.setrotation(m_img_dst);
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

			System.out.println(k);
			repaint();
		}
	}

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
	public static void main(String[] args) throws IOException 
	{
		Visualiseur visu = new Visualiseur();
		visu.setVisible(true);
		
	}
	
	
}
