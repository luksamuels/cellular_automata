import javax.swing.JFrame;

import java.awt.Insets;

public class CAFrame extends JFrame {

   // don't change! must be a multiple of 64!
   public static final int BOARD_SIZE = 128;
   public static final int SCALE_SIZE = 32;
   
   private CAPanel panel;

   public CAFrame() {
      // set up the frame
      setLayout(null);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      // set up the content panel
      panel = new CAPanel(BOARD_SIZE, SCALE_SIZE);
      panel.setBounds(0,
            0,
            CAPanel.TOTAL_PRESCALE_WIDTH * SCALE_SIZE,
            CAPanel.TOTAL_PRESCALE_HEIGHT * SCALE_SIZE
        );
      
      add(panel);

      // set it visible
      setVisible(true);
      Insets windowsInsets = getInsets();
      setBounds(100, 
         100, 
         CAPanel.TOTAL_PRESCALE_WIDTH * SCALE_SIZE + windowsInsets.left + windowsInsets.right,
         CAPanel.TOTAL_PRESCALE_HEIGHT * SCALE_SIZE + windowsInsets.top + windowsInsets.bottom);
   }
}