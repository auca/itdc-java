import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Task02 extends JFrame {
    private static final int STAR_COUNT = 10;

    private static final int INNER_STARS_LENGTH    = 50;
    private static final int INNER_STARS_RAY_COUNT = 10;
    private static final Color INNER_STAR_COLOR = Color.RED;

    private static final int OUTER_STAR_EVEN_RAY_LENGTH = 200;
    private static final int OUTER_STAR_ODD_RAY_LENGTH  = 100;

    String title = "Graphics Template";
    Color background = Color.BLACK;

    void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints hints = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setRenderingHints(hints);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        double angle = 0;
        double angleShift = 2 * Math.PI / STAR_COUNT;

        for (int i = 0; i < STAR_COUNT; ++i) {
            int[] endPoint = findEndPoint(
                i,
                angle,
                centerX, centerY,
                OUTER_STAR_EVEN_RAY_LENGTH,
                OUTER_STAR_ODD_RAY_LENGTH
            );

            drawStar(
                g2,
                INNER_STARS_RAY_COUNT,
                endPoint[0], endPoint[1],
                INNER_STARS_LENGTH,
                INNER_STAR_COLOR
            );

            angle += angleShift;
        }
    }

    private void drawStar(
                     Graphics2D g2,
                     int rayCount,
                     int centerX,
                     int centerY,
                     int length,
                     Color color
                 ) {
        double angle = 0;
        double angleShift = 2 * Math.PI / rayCount;

        int evenRayLength = length;
        int oddRayLength  = length / 3;

        for (int i = 0; i < rayCount; ++i) {
            int[] endPoint = findEndPoint(
                i, angle, centerX, centerY, evenRayLength, oddRayLength
            );

            g2.setColor(color);
            g2.drawLine(centerX, centerY, endPoint[0], endPoint[1]);

            angle += angleShift;

            int[] nextEndPoint = findEndPoint(
                i + 1, angle, centerX, centerY, evenRayLength, oddRayLength
            );

            g2.drawLine(endPoint[0], endPoint[1], nextEndPoint[0], nextEndPoint[1]);
        }
    }

    private static int[] findEndPoint(
                             int rayNumber,
                             double angle,
                             int centerX,
                             int centerY,
                             int evenRayLength,
                             int oddRayLength
                         ) {
        int rayLength =
            (rayNumber % 2 == 0 ? evenRayLength : oddRayLength);
        int endX =
            (int) (centerX + Math.cos(angle) * rayLength);
        int endY =
            (int) (centerY + Math.sin(angle) * rayLength);

        return new int[] { endX, endY };
    }

    public Task02() {
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
        new Task02();
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
