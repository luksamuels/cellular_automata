import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Graphics;

import java.util.List;
import java.util.LinkedList;

public class SettableNumberScroller extends JPanel implements ActionListener {
    private int value;

    private JLabel f_text;
    private JButton f_leftArrow;
    private JButton f_rightArrow;
    private JTextArea f_valueDisplay;

    private int f_lowBound = 0;
    private int f_highBound = Integer.MAX_VALUE;

    private List<ObjectListener> listeners;

    public SettableNumberScroller(String text, int defaultValue) {
        Font myFont = new Font("Batang", Font.PLAIN, 18);
    
        setLayout(null);
        setFocusable(true);

        // this.value = defaultValue;
        // this.listeners = new LinkedList<ObjectListener>();

        // f_text = new JLabel(text);
        // f_text.setFont(myFont);
        // f_text.setForeground(Color.BLACK);
        // f_text.setHorizontalAlignment(JButton.LEFT);
 
        f_leftArrow = new JButton();
         
        f_leftArrow.setBorderPainted(false);
        f_leftArrow.setContentAreaFilled(false);
        f_leftArrow.setActionCommand("decrease");
        f_leftArrow.addActionListener(this);

        // f_rightArrow = new JButton();
        // f_rightArrow.setIcon(new ImageIcon("rightArrow.png"));
        // f_rightArrow.setBorderPainted(false);
        // f_rightArrow.setContentAreaFilled(false);
        // f_rightArrow.setActionCommand("increase");
        // f_rightArrow.addActionListener(this);

        // f_valueDisplay = new JTextArea(Integer.toString(value));
        // f_valueDisplay.setFont(myFont);
        // f_valueDisplay.setEditable(false);

        add(f_leftArrow);
        // add(f_text);
        // add(f_rightArrow);
        // add(f_valueDisplay);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.DARK_GRAY);

        // just draw rectangles for now?
        int width = this.getWidth();
        int height = this.getHeight();

        // draw left arrow
        // g.setColor(Color.RED);
        // g.fillRect(0, 0, width / 6, height);
        
        // text area
        g.setColor(Color.BLUE);
        g.fillRect(width / 4, 0, width / 2, height);

        // value area
        g.setColor(Color.GREEN);
        g.fillRect(7 * (width / 12), 0, width / 6, height);

        // right arrow
        g.setColor(Color.ORANGE);
        g.fillRect(10 * (width / 12), 0, width / 6, height);
    }

    
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        // f_leftArrow.setBounds(0, 0, width / 6, height);
        // f_text.setBounds(width / 4, 0, width / 2, height);
        // f_valueDisplay.setBounds(7 * (width / 12), 0, width / 6, height);
        // f_rightArrow.setBounds(5 * (width / 6), 0, width / 6, height);
    }

    public void setValueBounds(int lower, int higher) {
        f_lowBound = lower;
        f_highBound = higher;
    }

    public int getValue() {
        return value;
    }

    public void addObjectListener(ObjectListener o) {
        listeners.add(o);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("increase")) { value++; }
        if(e.getActionCommand().equals("decrease")) { value--; }
        // verify and send
        if(value < f_lowBound) {
            value = f_highBound - 1;
        }
        if(value >= f_highBound) {
            value = f_lowBound;
        }
        f_valueDisplay.setText(Integer.toString(value));
        notifyListeners("value_changed");
    }

    // notifies all listeners that the value was updated.
    private void notifyListeners(String s) {
        for(ObjectListener o : listeners) {
            o.notified(s);
        }
    }
}
