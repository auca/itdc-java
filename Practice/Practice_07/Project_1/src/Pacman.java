import java.awt.*;
import java.awt.image.BufferedImage;

public class Pacman {
    final static int COIN_POINTS = 10;

    static int x = 0, y = 0;
    static int dx = 1, dy = 0;
    static int score = 0;

    final static BufferedImage UP_IMAGE =
        ImageLoader.load("pacman_up.png");
    final static BufferedImage DOWN_IMAGE =
        ImageLoader.load("pacman_down.png");
    final static BufferedImage LEFT_IMAGE =
        ImageLoader.load("pacman_left.png");
    final static BufferedImage RIGHT_IMAGE =
        ImageLoader.load("pacman_right.png");

    public static void create(int newX, int newY) {
        x = newX; y = newY;
    }

    public static void turnUp() {
        dx = 0;
        dy = -1;
    }

    public static void turnDown() {
        dx = 0;
        dy = 1;
    }

    public static void turnLeft() {
        dx = -1;
        dy = 0;
    }

    public static void turnRight() {
        dx = 1;
        dy = 0;
    }

    public static void move(int stepCount) {
        for (int i = 0; i < stepCount; ++i) {
            int nextX = x + dx;
            int nextY = y + dy;

            if (!Field.canMove(nextX, nextY)) {
                break;
            }

            Coin coin;
            if ((coin = Field.getCoin(nextX, nextY)) != null) {
                coin.collect();

                score += COIN_POINTS;
            }

            x = nextX;
            y = nextY;
        }
    }

    public static void draw(Graphics2D g2, int gameAreaX, int gameAreaY, int tileSize) {
        int screenX = gameAreaX + x * tileSize;
        int screenY = gameAreaY + y * tileSize;

        BufferedImage image;
        if (Pacman.dx == 0 && Pacman.dy == -1) {
            image = UP_IMAGE;
        } else if (Pacman.dx == 0 && Pacman.dy == 1) {
            image = DOWN_IMAGE;
        } else if (Pacman.dx == -1 && Pacman.dy == 0) {
            image = LEFT_IMAGE;
        } else {
            image = RIGHT_IMAGE;
        }

        g2.drawImage(
            image,
            screenX,
            screenY,
            tileSize - 1,
            tileSize - 1,
            null,
            null
        );
    }
}
