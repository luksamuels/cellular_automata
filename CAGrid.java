import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CAGrid extends JPanel implements MouseListener {
    private CAManager ca;

    private int width;
    private int height;
    private int rule;

    private int[][] grid;
    private int filledGenerations;
    private boolean isFresh;

    

   /**   --------------------------------- CONSTRUCTOR -------------------------------
    */
    public CAGrid(int width, int height, int rule) {
        // initialize the panel itself;
        setLayout(null);
        setFocusable(true);
        addMouseListener(this);

        // fields init
        this.width = width;
        this.height = height;
        this.rule = rule;

        grid = null;
        filledGenerations = 0;
        isFresh = false;
        
        reset();
    }

    /*  -------------------------------- FUNCTIONALITY ----------------------------------
    */
    public void fill() {
        while(filledGenerations < height) {
            advance();
        }
    }

    public void advance() {
        if(filledGenerations < height) {
            ca.nextGeneration();
            for(int i = 0; i < width; i++) {
                grid[filledGenerations][i] = ca.at(i);
            }
            filledGenerations++;
            isFresh = false;
            repaint();
        }
    }

    public void reset() {
        grid = new int[height][width];
        ca = new CAManager(rule, width);
        isFresh = true;
        filledGenerations = 1;
        for(int i = 0; i < width; i++) {
            grid[0][i] = ca.at(i);
        }
        repaint();
    }


    /**   ------------------------------  DRAWING METHODS --------------------------------
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        int pixel_size = this.getWidth() / width;

        for(int i = 0; i < filledGenerations; i++) {
            for(int j = 0; j < width; j++) {
                // 0 paints white, 1 paints black
                g.setColor(new Color(0xFFFFFF - (grid[i][j] * 0xFFFFFF)));
                g.fillRect(pixel_size * j, pixel_size * i, pixel_size, pixel_size);
            }
        }

        // try gridlines?
        g.setColor(new Color(0x5866EA));
        for(int i = 1; i < height; i++) {
            g.drawLine(0, i * pixel_size, width * pixel_size, i * pixel_size);
        }
        for(int i = 1; i < width; i ++) {
            g.drawLine(i * pixel_size, 0, i * pixel_size, height * pixel_size);
        }
    }

   /**   ---------------------------- MOUSE LISTENER METHODS ---------------------------
     */
    public void mouseClicked(MouseEvent e) {
        if(isFresh) {
            int x = e.getX() / (getWidth() / width);
            int y = e.getY() / (getHeight() / height);
            if(y == 0 && x >= 0 && x < width) { // x has to be in bounds!
                ca.flip(x);
                grid[0][x] ^= 0x01;
                repaint();
            }
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
