import javax.swing.JFrame;

import java.awt.Insets;

public class CAFrame extends JFrame {

   // don't change! must be a multiple of 64!
   public static final int GENERATION_SIZE = 128;
   public static final int NUM_GENERATIONS = 128;
   public static final int PIXEL_SIZE = 6;
   
   private CAPanel panel;

   public CAFrame(int rule) {
      // set up the frame
      setVisible(true);
      Insets windowsInsets = getInsets();
      setBounds(100, 
         100, 
         GENERATION_SIZE * PIXEL_SIZE + windowsInsets.left + windowsInsets.right,
         NUM_GENERATIONS * PIXEL_SIZE + windowsInsets.top + windowsInsets.bottom);
      setLayout(null);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // set up the content panel
      panel = new CAPanel(NUM_GENERATIONS, GENERATION_SIZE, PIXEL_SIZE, rule);
      panel.setBounds(0, 0, GENERATION_SIZE * PIXEL_SIZE, NUM_GENERATIONS * PIXEL_SIZE);
      
      add(panel);
      
      
      
   }
}