import java.awt.*;

public class Coin {
    final Color COIN_COLOR = new Color(255, 193, 158);

    int x, y;
    boolean isCollected;

    Coin(int newX, int newY) {
        x = newX;
        y = newY;
        isCollected = false;
    }

    void draw(Graphics2D g2, int gameAreaX, int gameAreaY, int tileSize) {
        if (!isCollected) {
            double margin = tileSize * 0.42;
            double tileSizeWithMargin = tileSize - margin * 2;
            double screenX = gameAreaX + margin + x * tileSize;
            double screenY = gameAreaY + margin + y * tileSize;

            g2.setColor(COIN_COLOR);
            g2.fillOval(
                (int) screenX,
                (int) screenY,
                (int) (tileSizeWithMargin - 1),
                (int) (tileSizeWithMargin - 1)
            );
        }
    }

    void collect() {
        isCollected = true;
    }
}
