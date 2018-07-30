import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pacman {
    final static int COIN_POINTS = 10;

    static int x = 0, y = 0;
    static int dx = 1, dy = 0;
    static int score = 0;

    static BufferedImage upImage;
    static BufferedImage downImage;
    static BufferedImage leftImage;
    static BufferedImage rightImage;

    public static void create(int newX, int newY) {
        x = newX; y = newY;
        try {
            final String BASE = "pacman";
            File upImageFile = new File(getImageFilePath(BASE, "up.png"));
            upImage = ImageIO.read(upImageFile);
            File downImageFile = new File(getImageFilePath(BASE, "down.png"));
            downImage = ImageIO.read(downImageFile);
            File leftImageFile = new File(getImageFilePath(BASE, "left.png"));
            leftImage = ImageIO.read(leftImageFile);
            File rightImageFile = new File(getImageFilePath(BASE, "right.png"));
            rightImage = ImageIO.read(rightImageFile);
        } catch (Exception e) {
            System.err.println("Failed to load images: " + e.getMessage());
            System.exit(-1);
        }
    }

    public static String getImageFilePath(String base, String suffix) {
        return base + File.separator + base + "_" + suffix;
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

            int coinIndex;
            if ((coinIndex = Coins.getCoinIndex(nextX, nextY)) >= 0) {
                Coins.collect(coinIndex);

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
            image = upImage;
        } else if (Pacman.dx == 0 && Pacman.dy == 1) {
            image = downImage;
        } else if (Pacman.dx == -1 && Pacman.dy == 0) {
            image = leftImage;
        } else {
            image = rightImage;
        }

        g2.setColor(Color.RED);
        g2.drawImage(
            image,
            screenX,
            screenY,
            tileSize - 1,
            tileSize - 1,
            Color.WHITE,
            null
        );
    }
}
