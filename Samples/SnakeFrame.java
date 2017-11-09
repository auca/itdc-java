import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MainFrame extends JFrame {

    String title = "Animation Template";
    Color background = Color.BLUE;
    int delay = 100;

    int fieldCellCount = 30;
    Color fieldColor = Color.WHITE;

    int snakeLength = 10;
    Color snakeColor = Color.GREEN;
    int[][] snake = new int[snakeLength][2];
    int snakeDX = 1; int snakeDY = 0;

    boolean isPlaying = true;

    void start() {
        // код для инициализации

    }

    void update() {
        if (!isPlaying) return;

        moveSnake();
        checkCollisionWithWalls();
    }

    void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints hints = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setRenderingHints(hints);

        drawField(g2);
        drawSnake(g2);
    }

    void input(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } // else if / switch...

        if (!isPlaying) return;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                turnUp();
                break;
            case KeyEvent.VK_DOWN:
                turnDown();
                break;
            case KeyEvent.VK_LEFT:
                turnLeft();
                break;
            case KeyEvent.VK_RIGHT:
                turnRight();
                break;
        }
    }

    void drawField(Graphics2D g2) {
        int fieldSize = Math.min(getWidth(), getHeight());
        int cellSize = fieldSize / fieldCellCount;

        int centerShiftX = (getWidth() - fieldSize) / 2;
        int centerShiftY = (getHeight() - fieldSize) / 2;

        g2.setPaint(fieldColor);
        for (int y = 0; y < fieldCellCount; ++y) {
            for (int x = 0; x < fieldCellCount; ++x) {
                g2.fillRect(
                    centerShiftX + x * cellSize,
                    centerShiftY + y * cellSize,
                    cellSize - 1,
                    cellSize - 1
                );
            }
        }

        g2.setColor(Color.RED);
        Font font = new Font("Arial", Font.PLAIN, 50);
        g2.setFont(font);
        g2.drawString("Вы проиграли", 100, 200);
    }

    void drawSnake(Graphics2D g2) {
        int fieldSize = Math.min(getWidth(), getHeight());
        int cellSize = fieldSize / fieldCellCount;

        int centerShiftX = (getWidth() - fieldSize) / 2;
        int centerShiftY = (getHeight() - fieldSize) / 2;

        g2.setPaint(snakeColor);
        for (int i = 0; i < snakeLength; ++i) {
            int x = snake[i][0];
            int y = snake[i][1];

            if (isInside(x, y)) {
                g2.fillRect(
                    centerShiftX + x * cellSize,
                    centerShiftY + y * cellSize,
                    cellSize - 1,
                    cellSize - 1
                );
            }
        }
    }

    void moveSnake() {
        int head = snakeLength - 1;
        for (int i = 0; i < head; ++i) {
            snake[i][0] = snake[i + 1][0];
            snake[i][1] = snake[i + 1][1];
        }
        snake[head][0] += snakeDX;
        snake[head][1] += snakeDY;
    }

    boolean isInside(int x, int y) {
        return (x >= 0 && x < fieldCellCount) &&
               (y >= 0 && y < fieldCellCount);
    }

    void checkCollisionWithWalls() {
        int head = snakeLength - 1;
        int headX = snake[head][0];
        int headY = snake[head][1];

        if (!isInside(headX, headY)) {
            isPlaying = false;
        }
    }

    void turnUp() {
        if (snakeDY != 1) {
            snakeDX = 0;
            snakeDY = -1;
        }
    }

    void turnDown() {
        if (snakeDY != -1) {
            snakeDX = 0;
            snakeDY = 1;
        }
    }

    void turnLeft() {
        if (snakeDX != 1) {
            snakeDX = -1;
            snakeDY = 0;
        }
    }

    void turnRight() {
        if (snakeDX != -1) {
            snakeDX = 1;
            snakeDY = 0;
        }
    }

    public MainFrame() {
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawPanel panel = new DrawPanel();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                input(e.getKeyCode());
            }
        });
        add(panel);

        javax.swing.Timer timer = new javax.swing.Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });

        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        start();
        timer.start();
    }

    public static void main(String[] args) {
        new MainFrame();
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
