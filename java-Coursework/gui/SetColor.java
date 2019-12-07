package coursework.gui;

import javax.swing.*;
import java.awt.Color;

/**
 * @author Lolipop
 * @lastUpdate 2019/11/5
 */
public class SetColor {
    private void init() {
        // basic
        JFrame jf = new JFrame("SetColor");
        jf.setVisible(true);
        jf.setSize(400, 300);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jp = new JPanel();
        jp.setBackground(Color.black);

        // buttons
        JButton redBtn = new JButton("set red");
        JButton greenBtn = new JButton("set green");
        JButton blueBtn = new JButton("set blue");

        // button-event
        redBtn.addActionListener(e -> jp.setBackground(Color.red));
        greenBtn.addActionListener(e -> jp.setBackground(Color.green));
        blueBtn.addActionListener(e -> jp.setBackground(Color.blue));

        // Panel
        jp.add(redBtn);
        jp.add(greenBtn);
        jp.add(blueBtn);
        jf.setContentPane(jp);
    }

    public static void main (String[] args) {
        new SetColor().init();
    }
}
