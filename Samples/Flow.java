import java.awt.*;
import java.awt.event.*;
import java.util.stream.IntStream;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

public class Flow extends JFrame {

    private static final String TITLE = "Fluid Flow Simulation";
    private static final Color BACKGROUND = Color.BLACK;
    private static final float DT = 0.1f;

    private int fieldSize = 128;
    private float diffuseRate = 0.0001f, viscosity = 0.00005f;
    private float force = 50000.0f, source = 5000000.0f;
    private boolean shouldDrawVelocity = false;

    private float[][] u, v, previousU, previousV;
    private float[][] density, previousDensity;

    private int mouseX = 0, previousMouseX = 0;
    private int mouseY = 0, previousMouseY = 0;

    private boolean isLeftMouseButtonPressed  = false;
    private boolean isRightMouseButtonPressed = false;

    private void start() {
        recreateDataArrays();
    }

    private void update() {
        processMouseInput();
        integrateVelocity(previousU, previousV, u, v, viscosity);
        integrateDensity(previousDensity, density, u, v, diffuseRate);
    }

    private void draw(Graphics g) {
        drawDensity(g);
        if (shouldDrawVelocity) {
            drawVelocity(g);
        }
    }

    private void processKeyboardInput(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if (code == KeyEvent.VK_C) {
            recreateDataArrays();
        } else if (code == KeyEvent.VK_V) {
            shouldDrawVelocity = !shouldDrawVelocity;
        }
    }

    private static <T> T swap(T... args) {
        return args[0];
    }

    private void recreateDataArrays() {
        int size = fieldSize + 2;
        u = new float[size][size];
        v = new float[size][size];
        previousU = new float[size][size];
        previousV = new float[size][size];
        density = new float[size][size];
        previousDensity = new float[size][size];
    }

    private void addSource(float[][] source, float[][] x) {
        for (int i = 0; i < x.length; ++i) {
            for (int j = 0; j < x[0].length; ++j)
                x[i][j] += source[i][j] * DT;
        }
    }

    private void updateBounds(float[][] x, int boundary) {
        int width =
            x.length;
        int height =
            x[0].length;

        for (int i = 1; i < height - 1; ++i) {
            for (int j = 1; j < width - 1; ++j) {
                x[0][j] =
                    boundary == 1 ?
                        -x[1][j] :
                         x[1][j];
                x[height - 1][j] =
                    boundary == 1 ?
                        -x[height - 2][j] :
                         x[height - 2][j];

                x[i][0] =
                    boundary == 2 ?
                        -x[i][1] :
                         x[i][1];
                x[i][width - 1] =
                    boundary == 2 ?
                        -x[i][width - 2] :
                         x[i][width - 2];
            }
        }

        x[0][0] =
            0.5f * (x[1][0] + x[0][1]);
        x[0][width - 1] =
            0.5f * (x[1][width - 1] + x[0][width - 2]);
        x[height - 1][0] =
            0.5f * (x[height - 2][0] + x[height - 1][1]);
        x[height - 1][width - 1] =
            0.5f * (x[height - 2][width - 1] + x[height - 1][width - 2]);
    }

    private void solve(float[][] x0, float[][] x, float a, float c, int boundary) {
        int width =
            x.length;
        int height =
            x[0].length;

        for (int k = 0; k < 20; ++k) {
            for (int i = 1; i < height - 1; ++i) {
                for (int j = 1; j < width - 1; ++j) {
                    x[i][j] =
                        (x0[i][j] + a * (x[i - 1][j] +
                                         x[i + 1][j] +
                                         x[i][j - 1] +
                                         x[i][j + 1])) / c;
                }
            }

            updateBounds(x, boundary);
        }
    }

    private void diffuse(float[][] x0, float[][] x, float diffusionRate, int boundary) {
        int width =
            x.length;
        int height =
            x[0].length;

        float cellCount = (width - 2) * (height - 2);
        diffusionRate = DT * diffusionRate * cellCount;
        float relaxation = 1.0f + 4.0f * diffusionRate;

        solve(x0, x, diffusionRate, relaxation, boundary);
    }

    private void advect(float[][] d0, float[][] d, float[][] u, float[][] v, int boundary) {
        int width =
            d.length;
        int height =
            d[0].length;

        float dt0 = DT * (width - 2);
        for (int i = 1; i < height - 1; ++i) {
            for (int j = 1; j < width - 1; ++j) {
                float x = i - dt0 * u[i][j];
                float y = j - dt0 * v[i][j];

                if (x < 0.5f) {
                    x = 0.5f;
                }
                if (x > (width - 2) + 0.5f) {
                    x = (width - 2) + 0.5f;
                }
                if (y < 0.5f) {
                    y = 0.5f;
                }
                if (y > (height - 2) + 0.5f) {
                    y = (height - 2) + 0.5f;
                }

                int i0 = (int) x;
                int i1 = i0 + 1;

                int j0 = (int) y;
                int j1 = j0 + 1;

                float s1 = x - i0;
                float s0 = 1 - s1;
                float t1 = y - j0;
                float t0 = 1 - t1;
                d[i][j] =
                    s0 * (t0 * d0[i0][j0] + t1 * d0[i0][j1]) +
                    s1 * (t0 * d0[i1][j0] + t1 * d0[i1][j1]);
            }
        }

        updateBounds(d, boundary);
    }

    private void project(float[][] p, float[][] div, float[][] u, float[][] v) {
        int width =
            u.length;
        int height =
            u[0].length;

        for (int i = 1; i < height - 1; ++i) {
            for (int j = 1; j < width - 1; ++j) {
                div[i][j] = -0.5f * (u[i + 1][j] - u[i - 1][j] + v[i][j + 1] - v[i][j-1]) / (width - 2);
                p[i][j] = 0.0f;
            }
        }
        updateBounds(div, 0);
        updateBounds(p, 0);

        solve(div, p, 1, 4, 0);

        for (int i = 1; i < height - 1; ++i) {
            for (int j = 1; j < width - 1; ++j) {
                u[i][j] -= 0.5f * (width - 2) * (p[i + 1][j] - p[i - 1][j]);
                v[i][j] -= 0.5f * (height - 2) * (div[i][j + 1] - div[i][j - 1]);
            }
        }
        updateBounds(u, 1);
        updateBounds(v, 2);
    }

    private void integrateDensity(float[][] x0, float[][] x, float[][] u, float[][] v, float diffuseRate) {
        addSource(x0, x);
        x0 = swap(x, x = x0);
        diffuse(x0, x, diffuseRate, 0);
        x0 = swap(x, x = x0);
        advect(x0, x, u, v, 0);
    }

    private void integrateVelocity(float[][] u0, float[][] v0, float[][] u, float[][] v, float viscosity) {
        addSource(u0, u);
        addSource(v0, v);
        u0 = swap(u, u = u0);
        v0 = swap(v, v = v0);
        diffuse(u0, u, viscosity, 1);
        diffuse(v0, v, viscosity, 2);
        project(u0, v0, u, v);
        u0 = swap(u, u = u0);
        v0 = swap(v, v = v0);
        advect(u0, u, u0, v0, 1);
        advect(v0, v, u0, v0, 2);
        project(u0, v0, u, v);
    }

    private void processMouseInput() {
        int size = fieldSize + 2;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                previousU[i][j] = previousV[i][j] = previousDensity[i][j] = 0.0f;
            }
        }

        if (!isLeftMouseButtonPressed && !isRightMouseButtonPressed) return;

        int i = (int) (mouseX / (float) getWidth() * fieldSize);
        int j = (int) (mouseY / (float) getHeight() * fieldSize);

        if (i < 1 || i > fieldSize || j < 1 || j > fieldSize) return;

        if (isLeftMouseButtonPressed) {
            previousU[i][j] = force * (mouseX - previousMouseX);
            previousV[i][j] = force * (mouseY - previousMouseY);
        }

        if (isRightMouseButtonPressed) {
            previousDensity[i][j] = source;
        }

        previousMouseX = mouseX;
        previousMouseY = mouseY;
    }

    private void drawVelocity(Graphics g) {
        g.setColor(Color.RED);

        float h = 1.0f / fieldSize;
        for (int i = 1; i <= fieldSize; ++i) {
            float x1 = (i - 0.5f) * h;
            for (int j = 1; j <= fieldSize; ++j) {
                float y1 = (j - 0.5f) * h;

                float x2 = x1 + u[i][j];
                float y2 = y1 + v[i][j];

                g.drawLine(
                    (int) (x1 * getWidth()),
                    (int) (y1 * getHeight()),
                    (int) (x2 * getWidth()),
                    (int) (y2 * getHeight())
                );
            }
        }
    }

    private void drawDensity(Graphics g) {
        float cellWidth = getWidth() / fieldSize;
        float cellHeight = getHeight() / fieldSize;
        for (int i = 0; i < fieldSize; ++i) {
            for (int j = 0; j < fieldSize; ++j) {
                int grade = (int) Math.min(Math.max(0.0f, density[i + 1][j + 1]), 255.0f);
                g.setColor(new Color(grade, grade, grade));
                g.fillRect(
                    (int) (i * cellWidth),
                    (int) (j * cellHeight),
                    (int) cellWidth,
                    (int) cellHeight
                );
            }
        }
    }

    public Flow() {
        setTitle(TITLE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(512, 512);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        DrawPanel panel = new DrawPanel();
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                previousMouseX = mouseX = e.getX();
                previousMouseY = mouseY = e.getY();

                isLeftMouseButtonPressed = e.getButton() == MouseEvent.BUTTON1;
                isRightMouseButtonPressed = e.getButton() == MouseEvent.BUTTON3;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();

                isLeftMouseButtonPressed = SwingUtilities.isLeftMouseButton(e);
                isRightMouseButtonPressed = SwingUtilities.isRightMouseButton(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();

                isLeftMouseButtonPressed = false;
                isRightMouseButtonPressed = false;
            }
        };

        panel.addMouseListener(adapter);
        panel.addMouseMotionListener(adapter);
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                processKeyboardInput(e.getKeyCode());
            }
        });
        add(panel);

        start();

        javax.swing.Timer timer = new javax.swing.Timer(17, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        new Flow();
    }

    class DrawPanel extends JPanel {
        public DrawPanel() {
            setBackground(BACKGROUND);
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
