import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.*;

import java.util.Arrays;

public class CAPanel extends JPanel implements MouseListener {
   private CAManager ca;
   private int num_generations;
   private int generation_size;
   
   private int click_count = 0;
   
   private int pixel_size;

   // todo: make able to operate on more than 1 generation width
   public CAPanel(int num_generations, int generation_size, int pixel_size, int rule) {
      setLayout(null);
      this.setBounds(0, 0, pixel_size * generation_size, pixel_size * num_generations);
      
      this.pixel_size = pixel_size;
      this.num_generations = num_generations;
      this.generation_size = generation_size;
      ca = new CAManager(rule, generation_size);
      this.addMouseListener(this);
   }
   
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      setBackground(Color.DARK_GRAY);
      ca.reset();

      paintGeneration(0, ca.getCurrentGeneration(), g);
      for(int i = 1; i < num_generations; i++) {
         ca.nextGeneration();
         // paintGeneration(i * pixel_size, ca.getCurrentGeneration(), g);
         for(int j = 0; j < generation_size; j++) {
            int curBit = ca.at(j);
            g.setColor(new Color(0xFFFFFF - (curBit * 0xFFFFFF)));
            g.fillRect(pixel_size * j, pixel_size * i, pixel_size, pixel_size);
         }
      }
   }
   
   public void paintGeneration(int y, int[] generation, Graphics g) {
      for(int i = 0; i < generation.length; i++) {
         int curBit = generation[i];
         g.setColor(new Color(0xFFFFFF - (curBit * 0xFFFFFF)));
         g.fillRect(pixel_size * i, y, pixel_size, pixel_size);
      }
   }
   
   public void mouseClicked(MouseEvent e) {}
   public void mousePressed(MouseEvent e) {
      click_count++;
      ca = new CAManager(click_count, generation_size);
      System.out.println(click_count);
      
      repaint();
   }
   public void mouseReleased(MouseEvent e) {}
   public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}

}