import java.util.Scanner;
public class App {

   public static int RULE = 90;

   public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
      System.out.print("Enter the rule you would like to generate: ");
      RULE = s.nextInt();
      new CAFrame(RULE);

   }
}