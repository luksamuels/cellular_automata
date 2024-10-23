import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;


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
    private JTextField f_valueDisplay;

    private int f_lowBound = 0;
    private int f_highBound = Integer.MAX_VALUE;

    private List<ObjectListener> listeners;

    public SettableNumberScroller(String text, int defaultValue) {
        Font textFont = new Font("Batang", Font.PLAIN, 14);
        Font buttonFont = new Font("Batang", Font.BOLD, 10);
    
        setLayout(null);
        setOpaque(false);

        this.value = defaultValue;
        this.listeners = new LinkedList<ObjectListener>();

        f_text = new JLabel(text);
        f_text.setFont(textFont);
        f_text.setForeground(Color.BLACK);
        f_text.setHorizontalAlignment(JButton.CENTER);
 
        f_leftArrow = new JButton("<-");
        buttonInit(f_leftArrow, "decrease", buttonFont);
    
        f_rightArrow = new JButton("->");
        buttonInit(f_rightArrow, "increase", buttonFont);

        f_valueDisplay = new JTextField(Integer.toString(value), 3);
        f_valueDisplay.setFont(textFont);
        f_valueDisplay.setEditable(true);
        f_valueDisplay.setFont(textFont);
        f_valueDisplay.setActionCommand("updated");
        f_valueDisplay.addActionListener(this);

        add(f_leftArrow);
        add(f_text);
        add(f_rightArrow);
        add(f_valueDisplay);

    }

    void buttonInit(JButton button, String actionCommand, Font f) {
        button.setFont(f);
        button.setBorderPainted(true);
        button.setContentAreaFilled(true);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        f_leftArrow.setBounds(0, 0, (width / 4), height);
        f_text.setBounds(width / 4, 0, width / 3, height);
        f_valueDisplay.setBounds((7 * (width / 12)) - 10, 3, width / 6, height - 6);
        f_rightArrow.setBounds(3 * (width / 4), 0, (width / 4), height);
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
        if(e.getActionCommand().equals("updated")) { value = Integer.parseInt(f_valueDisplay.getText()); }
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
            o.notify(s);
        }
    }
}
