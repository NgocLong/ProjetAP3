
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

public class DisplayImageOnLabel extends JPanel {
   
    public DisplayImageOnLabel(String path) {
        ImageIcon icon = createImageIcon(path, null/*"a pretty but meaningless splat"*/);
        JLabel label1 = new JLabel(icon);    
        add(label1);
    }
   
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path, String description) {
        String imgPath = path;
        if (imgPath != null) {
            return new ImageIcon(imgPath, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
   
    /*
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("LabelDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new DisplayImageOnLabel());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }       
   */
    
    //public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        /*
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
        //Turn off metal's use of bold fonts
            UIManager.put("swing.boldMetal", Boolean.FALSE);
               
        createAndShowGUI();
            }
        });*/
    	//createAndShowGUI();
    //}
}