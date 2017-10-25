import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Task04 extends JFrame {

    private static final int CIRCLE_COUNT = 1000;

    private static final int CIRCLE_MIN_SIZE = 1;
    private static final int CIRCLE_MAX_SIZE = 100;

    private static final int RED_MAX_VALUE   = 255;
    private static final int GREEN_MAX_VALUE = 255;
    private static final int BLUE_MAX_VALUE  = 255;

    String title = "Graphics Template";
    Color background = Color.BLACK;

    void draw(Graphics g) {
        for (int i = 0; i < CIRCLE_COUNT; ++i) {
            int size =
                (int) (CIRCLE_MIN_SIZE + Math.random() * 
                        (CIRCLE_MAX_SIZE - CIRCLE_MIN_SIZE + 1));
            int x = (int) (-size + Math.random() * (getWidth()  + size + 1));
            int y = (int) (-size + Math.random() * (getHeight() + size + 1));
            int red   = (int) (Math.random() * (RED_MAX_VALUE   + 1));
            int green = (int) (Math.random() * (GREEN_MAX_VALUE + 1));
            int blue  = (int) (Math.random() * (BLUE_MAX_VALUE  + 1));
            Color color = new Color(red, green, blue);

            g.setColor(color);
            g.fillOval(x, y, size, size);
        }
    }

    public Task04() {
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
        new Task04();
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
