import java.util.*;
import java.awt.GraphicsEnvironment;

public class CATester {
   public static void main(String[] args) {
      // CAManager ca = new CAManager(90, 8);
      // System.out.println(Arrays.toString(ca.getCurrentGeneration()));
      // for(int i = 0; i < 40; i++) {
      //    ca.nextGeneration();
      //    System.out.println(Arrays.toString(ca.getCurrentGeneration()));
      // }

      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      String[] fontFamilies = ge.getAvailableFontFamilyNames();
      for(String s : fontFamilies) {
         System.out.println(s);
      }
   }
}