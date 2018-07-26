import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {

    String title = "Animation Template";
    Color background = Color.BLACK;
    int delay = 10;

    // Ваши переменные

    void start() {
        Field.create(Field.DEFAULT_WIDTH, Field.DEFAULT_HEIGHT);

    }

    void update() {
        // код для обновления свойств объектов

    }

    void draw(Graphics2D g2) {
        if (Field.field == null) { return; };

        int screenWidth = getWidth();
        int screenHeight = getHeight();

        int tileSize = (int) (Math.min(screenWidth / Field.width, screenHeight / Field.height) * 0.9f);
        int gameAreaWidth = Field.width * tileSize;
        int gameAreaHeight = Field.height * tileSize;
        int gameAreaX = (screenWidth - gameAreaWidth) / 2;
        int gameAreaY = (screenHeight - gameAreaHeight) / 2;

        for (int y = 0; y < Field.height; ++y) {
            for (int x = 0; x < Field.width; ++x) {
                int screenX = gameAreaX + x * tileSize;
                int screenY = gameAreaY + y * tileSize;

                if (Turtle.x == x && Turtle.y == y) {
                    g2.setColor(Color.RED);
                    g2.fillRect(screenX, screenY, tileSize - 1, tileSize - 1);
                } else {
                    switch (Field.field[x][y]) {
                        case Field.EMPTY_CELL:
                            g2.setColor(Color.WHITE);
                            g2.fillRect(screenX, screenY, tileSize - 1, tileSize - 1);
                            break;
                        case Field.MARKED_CELL:
                            g2.setColor(new Color(150, 0, 0));
                            g2.fillRect(screenX, screenY, tileSize - 1, tileSize - 1);
                            break;
                    }
                }
            }
        }
    }

    void input(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_LEFT:
                Turtle.turnLeft();
                break;
            case KeyEvent.VK_RIGHT:
                Turtle.turnRight();
                break;
            case KeyEvent.VK_UP:
                Turtle.move(1);
                break;
            case KeyEvent.VK_P:
                if (Turtle.isPenDown) {
                    Turtle.putPenUp();
                } else {
                    Turtle.putPenDown();
                }
                break;
        }
    }

    public Main() {
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        DrawPanel panel = new DrawPanel();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                input(e.getKeyCode());
            }
        });
        add(panel);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent arg0) {
                start();

                javax.swing.Timer timer = new javax.swing.Timer(delay, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        update();
                        repaint();
                    }
                });
                timer.start();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    class DrawPanel extends JPanel {
        public DrawPanel() {
            setBackground(background);
            setFocusable(true);
            requestFocusInWindow();
            setDoubleBuffered(true);
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            g2.setRenderingHints(hints);

            super.paintComponent(g);
            draw(g2);
        }
    }

}
