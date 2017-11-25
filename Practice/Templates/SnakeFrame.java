import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MainFrame extends JFrame {

	// Field
	
    private static final float SCORE_MULTIPLIER = 0.2f;

	static final int DEFAULT_FIELD_SIZE = 10;
    static final Color DEFAULT_FIELD_COLOR = Color.DARK_GRAY;
    
    // Snake
    
    static final int DEFAULT_SNAKE_LENGTH = 1;
    static final Color DEFAULT_SNAKE_COLOR = Color.WHITE;
    
    // Apple
    
    static final Color DEFAULT_APPLE_COLOR = Color.RED;
    
    // Windows Parameters
    
	String title = "Snake";
    Color background = Color.BLUE;
    int delay = 100;
    
    // Field
    
    int fieldCellCount;
    Color fieldColor;

    // Snake
    
    int snakeLength;
    Color snakeColor;
    int[][] snake;
    int snakeDX, snakeDY;
    
    // Apple
    int applesEaten;
    Color appleColor;
    int appleX, appleY;
    
    // Game Parameters
    boolean isPlaying = true;
    int score = 0;
    javax.swing.Timer timer;

    void start() {
    	timer = new javax.swing.Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	restart();
            }
        });
    	restart();
    }
    
    void restart() {
    	timer.stop();

    	createField();
    	createSnake();
    	createApple();
    	
    	isPlaying = true;
    }

    void update() {
    	if (!isPlaying) {
    		// Показать сообщение 
    		timer.start();

    		return;
    	}
    	
        moveSnake();
        checkSnakeCollisionWithWalls();
        checkSnakeCollisionWithItself();
        checkSnakeCollisionWithApple();
    }

    void draw(Graphics2D g2) {
        drawField(g2);
        drawSnake(g2);
        drawApple(g2);
    }

    void input(int keyCode) {
    	if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } // else if / switch...

        if (!isPlaying) return;
        
        switch (keyCode) {
	        case KeyEvent.VK_UP:
	        	turnSnakeUp();
	        	break;
	        case KeyEvent.VK_DOWN:
	        	turnSnakeDown();
	        	break;
	        case KeyEvent.VK_LEFT:
	        	turnSnakeLeft();
	        	break;
	        case KeyEvent.VK_RIGHT:
	        	turnSnakeRight();
	        	break;
        }
    }
    
    // ---
    
    // Field
    
    void createField() {
    	fieldCellCount = DEFAULT_FIELD_SIZE;
    	fieldColor = DEFAULT_FIELD_COLOR;
    }
   
    boolean isInsideField(int x, int y) {
    	return (x >= 0 && x < fieldCellCount) &&
    		   (y >= 0 && y < fieldCellCount);
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
    }

    // Snake
    
    void createSnake() {
        snakeLength = DEFAULT_SNAKE_LENGTH;
        snakeColor = DEFAULT_SNAKE_COLOR;
        snake = new int[snakeLength][2];

        snakeDX = 1;
        snakeDY = 0;
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
    
    void checkSnakeCollisionWithWalls() {
    	int head = snakeLength - 1;
    	int headX = snake[head][0];
    	int headY = snake[head][1];

    	if (!isInsideField(headX, headY)) {
    		isPlaying = false;
    	}
    }

    void checkSnakeCollisionWithItself() {
    	int head = snakeLength - 1;
    	int headX = snake[head][0];
    	int headY = snake[head][1];

    	for (int i = 0; i < head; ++i) {
    		int segmentX = snake[i][0];
        	int segmentY = snake[i][1];
        	
	    	if (headX == segmentX && headY == segmentY) {
	    		isPlaying = false;
	    		break;
	    	}
    	}
    }
    
    void checkSnakeCollisionWithApple() {
    	int head = snakeLength - 1;
    	int headX = snake[head][0];
    	int headY = snake[head][1];

    	if (headX == appleX && headY == appleY) {
    		growSnake();

    		++applesEaten;
    		score += (int) (Math.ceil(applesEaten * SCORE_MULTIPLIER));
    		createApple();
    	}
    }
    
    void growSnake() {
    	int[][] temp = new int[snakeLength + 1][2];
    	int i;
    	for (i = 0; i < snakeLength; ++i) {
    		temp[i][0] = snake[i][0];
    		temp[i][1] = snake[i][1];
    	}
    	temp[i][0] = temp[i - 1][0];
    	temp[i][1] = temp[i - 1][1];
    	++snakeLength;

    	snake = temp;
    }

    void turnSnakeUp() {
    	if (snakeDY != 1) {
	    	snakeDX = 0;
	    	snakeDY = -1;
    	}
    }

    void turnSnakeDown() {
    	if (snakeDY != -1) {
	    	snakeDX = 0;
	    	snakeDY = 1;
    	}
    }
    
    void turnSnakeLeft() {
    	if (snakeDX != 1) {
	    	snakeDX = -1;
	    	snakeDY = 0;
    	}
    }
 
 	void turnSnakeRight() {
 		if (snakeDX != -1) {
 			snakeDX = 1;
 			snakeDY = 0;
 		}
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
    		
        	if (isInsideField(x, y)) {
		    	g2.fillRect(
					centerShiftX + x * cellSize,
					centerShiftY + y * cellSize,
					cellSize - 1,
					cellSize - 1
				);
        	}
    	}
    }

 	// Apple
 
 	void createApple() {
 		boolean isFound = false;
 		do {
	 		appleX = random(0, fieldCellCount);
	 		appleY = random(0, fieldCellCount);
	 		int i;
	 		for (i = 0; i < snakeLength; ++i) {
	 			int snakeSegmentX = snake[i][0];
	 			int snakeSegmentY = snake[i][1];
	 			
	 			if (appleX == snakeSegmentX &&
	 				appleY == snakeSegmentY) {
	 				break;
	 			}
	 		}
	 		if (i == snakeLength) {
	 			isFound = true;
	 		}
 		} while(!isFound);
 		
 		appleColor = DEFAULT_APPLE_COLOR;
 	}
 	
 	void drawApple(Graphics2D g2) {
    	int fieldSize = Math.min(getWidth(), getHeight());
    	int cellSize = fieldSize / fieldCellCount;
    	
    	int centerShiftX = (getWidth() - fieldSize) / 2;
    	int centerShiftY = (getHeight() - fieldSize) / 2;
		
    	g2.setColor(appleColor);
    	g2.fillRect(
			centerShiftX + appleX * cellSize,
			centerShiftY + appleY * cellSize,
			cellSize - 1,
			cellSize - 1
		);
    }

 	// Utility

 	static int random(int min, int max) {
 		return (int) (min + Math.random() * (max - min));
 	}
 	
 	// ---
 
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
