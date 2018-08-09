import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {

    final String TITLE = "Pacman";
    final Color BACKGROUND = Color.BLACK;
    final int DELAY = 10;

    void start() {
        Field.loadLevel(0);
        ScreenData.recalculateScreenData(getWidth(), getHeight());

        HUD.init();
    }

    void update() {
        if (!Field.hasCoins()) {
            Field.loadNextLevel();
            ScreenData.recalculateScreenData(getWidth(), getHeight());
        }
    }

    void draw(Graphics2D g2) {
        if (!ScreenData.isWindowsVisible) { return; };

        Field.draw(g2);
        Pacman.draw(g2);
        HUD.draw(g2);
    }

    void input(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_UP:
                Pacman.turnUp();
                Pacman.move(1);
                break;
            case KeyEvent.VK_DOWN:
                Pacman.turnDown();
                Pacman.move(1);
                break;
            case KeyEvent.VK_LEFT:
                Pacman.turnLeft();
                Pacman.move(1);
                break;
            case KeyEvent.VK_RIGHT:
                Pacman.turnRight();
                Pacman.move(1);
                break;
        }
    }

    public Main() {
        setTitle(TITLE);
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

            javax.swing.Timer timer = new javax.swing.Timer(DELAY, new ActionListener() {
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
            setBackground(BACKGROUND);
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