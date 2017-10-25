import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Task05 extends JFrame {

    private static final int CIRCLE_COUNT = 20;

    String title = "Graphics Template";
    Color background = Color.BLACK;

    void draw(Graphics g) {
        int red = 0;
        int redShift = 255 / CIRCLE_COUNT;

        int size = Math.min(getWidth(), getHeight());
        int sizeShift = size / CIRCLE_COUNT;

        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        for (int i = 0; i < CIRCLE_COUNT; ++i) {
            g.setColor(new Color(red, 0, 0));
            g.fillOval(x, y, size, size);

            red += redShift;
            size -= sizeShift;

            x += sizeShift / 2;
            y += sizeShift / 2;
        }
    }

    public Task05() {
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawPanel panel = new DrawPanel();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.exit(0);
            }
        });
        add(panel);

        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Task05();
    }

    class DrawPanel extends JPanel {
        public DrawPanel() {
            setBackground(background);
            setFocusable(true);
            requestFocusInWindow();
            setDoubleBuffered(true);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }
    }

}
