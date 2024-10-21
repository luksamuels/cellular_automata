import java.util.Arrays;

public class CAManager {
   private int[] current_generation;
   private int generation_size;
   private int rule;
   
   
   public CAManager(int rule, int generation_size) {
      if(rule > 255 || rule < 0) {
         throw new IllegalArgumentException("Rule must be a value between 0-255");
      }
      this.rule = rule;
      this.generation_size = generation_size;
      current_generation = new int[generation_size];
      
      // set up initial positions
      current_generation[generation_size / 2] = 1;
      //System.out.println(Arrays.toString(current_generation));
      //System.out.println(rule);
   }
   
   public void nextGeneration() {
      int[] next_generation = new int[generation_size];
      for(int i = 1; i < current_generation.length - 1; i++) {
         int neighborhood = getNeighborhood(i);
         if(neighborhood > 7 || neighborhood < 0) {
            System.out.println("neighborhood was out of bounds :(");
         }
         //System.out.print((byte)rule >> neighborhood);
         next_generation[i] = ((byte)rule >> neighborhood) & 0x01;
      }
      // get the edges
      int first = current_generation[0];
      int last = current_generation[generation_size - 1];
      int first_neighborhood = (last << 2) + (first << 1) + current_generation[1];
      int last_neighborhood = (current_generation[generation_size - 2] << 2) + (last << 1) + first;
      next_generation[0] = ((byte)rule >> first_neighborhood) & 0x01;
      next_generation[generation_size - 1] = ((byte)rule >> last_neighborhood) & 0x01;
      //System.out.println();
      //System.out.println(Arrays.toString(next_generation));
      current_generation = Arrays.copyOf(next_generation, generation_size);
   }
   
   public int[] getCurrentGeneration() {
      return current_generation;
   }
   
   public int at(int i) {
      if(i < 0 || i > generation_size - 1) {
         throw new IllegalArgumentException("Attempting to access generation with size " + generation_size + " at index " + i + ". Out of bounds.");
      }
      return current_generation[i];
   }
   
   private int getNeighborhood(int i) {
      return (current_generation[i-1] << 2) + (current_generation[i] << 1) + current_generation[i+1];
   }
   
   public void reset() {
      Arrays.fill(current_generation, 0);
      current_generation[generation_size / 2] = 1;
   }
   
   
   
   
//    public long nextGeneration() {
//       long next_gen = 0;
//       for(int i = 1; i < LONG_LENGTH - 1; i++) { // we'll carry over the first and last bits
//          int bit = (rule >> cellAndNeighbors(i)) & 0x01;
//          next_gen |= bit << i;
//       } 
//       long firstAndLast = ((cur_generation >> (LONG_LENGTH - 1)) & (long)0x01) + (cur_generation & (long)0x01);
//       //System.out.println(firstAndLast);
//       next_gen |= firstAndLast;
//       cur_generation = next_gen;
//       return next_gen;
//    }
   
  //  public int cellAndNeighbors(int i) {
//       if(i < 1 || i > (LONG_LENGTH - 2)) {
//          throw new IllegalArgumentException("Attempting to access long outside of indices 1..62");
//       }
//       return (int)(cur_generation >> (i - 1)) & 0x07;
//       
//    }  
   
}