
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Graphics;

public class CAPanel extends JPanel implements ActionListener, ObjectListener {

    public static final int TOTAL_PRESCALE_WIDTH = 18;
    public static final int TOTAL_PRESCALE_HEIGHT = 26;

    private final Font buttonFont = new Font("Batang", Font.PLAIN, 18);

    private int rule;
    private int scale_size;
    private int board_size;

    private JLabel titleCard;
    private CAGrid grid;
    private JButton advanceButton;
    private JButton resetButton;
    private JButton fillButton;
    private SettableNumberScroller valueScroller;
    

    public CAPanel(int board_size, int scale_size) {
        // set up panel itself
        setLayout(null);

        // init fields
        rule = 57;
        this.scale_size = scale_size;
        this.board_size = board_size;

        // The title card
        titleCard = new JLabel();
        titleCard.setText("Cellular Automata");
        titleCard.setFont(new Font("Batang", Font.PLAIN, 28));
        titleCard.setBounds(1 * scale_size, 1 * scale_size, 8 * scale_size, 1 * scale_size);

        // The grid;
        grid = new CAGrid(board_size, board_size, rule);
        grid.setBounds(1 * scale_size, 3 * scale_size, 16 * scale_size, 16 * scale_size);

        // The fill button;
        fillButton = new JButton("Fill");
        fillButton.setBounds(1 * scale_size, 20 * scale_size, 10 * scale_size, 2 * scale_size);
        initButton(fillButton, new Color(0xCC5AF0), "fill");

        // The reset button;
        resetButton = new JButton("Reset");
        resetButton.setBounds(1 * scale_size, 23 * scale_size, 10 * scale_size, 2 * scale_size);
        initButton(resetButton, new Color(0xB7194A), "reset");

        // The advance button;
        advanceButton = new JButton("Advance");
        advanceButton.setBounds(12 * scale_size, 20 * scale_size, 5 * scale_size, 5*scale_size);
        initButton(advanceButton, new Color(0x0FC179), "advance");

        // The value scroller;
        valueScroller = new SettableNumberScroller("Value: ", rule);
        valueScroller.setBounds(11 * scale_size, 1 * scale_size, 6 * scale_size, 1 * scale_size);
        // valueScroller.addObjectListener(this);

        add(titleCard);
        add(grid);
        add(fillButton);
        add(resetButton);
        add(advanceButton);
        add(valueScroller);
    }

    public void initButton(JButton b, Color bgColor, String command) {
        b.setActionCommand(command);
        b.addActionListener(this);
        b.setFont(buttonFont);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(true);
        b.setBackground(bgColor);
        b.setForeground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.LIGHT_GRAY);

        // draw border for grid
        g.setColor(Color.BLACK);
        int border_width = 1;
        g.fillRect(
            (1 * scale_size) - border_width, 
            (3 * scale_size) - border_width, 
            (16 * scale_size) + (2*border_width), 
            (16 * scale_size) + (2*border_width));

        // draw rectangles for buttons
        // g.setColor(new Color(0x84E291));
        // g.setColor(new Color(0x84, 0xE2, 0x91, 128));
        // g.fillRect(10 * scale_size, 1 * scale_size, 1 * scale_size, 1 * scale_size); // left button
        // g.fillRect(16 * scale_size, 1 * scale_size, 1 * scale_size, 1 * scale_size); // right button
        // g.fillRect(12 * scale_size, 1 * scale_size, 3 * scale_size, 1 * scale_size);


    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("fill")) {
            grid.fill();
        } else if(e.getActionCommand().equals("reset")) {
            grid.reset();
        } else if(e.getActionCommand().equals("advance")) {
            grid.advance();
        }
    }

    public void notified(String s) {
        if(s.equals("value_changed")) {
            grid = new CAGrid(board_size, board_size, valueScroller.getValue());
            repaint();
        }
    }
}
