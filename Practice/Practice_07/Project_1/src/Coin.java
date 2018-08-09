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

    void draw(Graphics2D g2) {
        if (!isCollected) {
            double margin = ScreenData.tileSize * 0.42;
            double tileSizeWithMargin = ScreenData.tileSize - margin * 2;
            double screenX = ScreenData.gameAreaX + margin + x * ScreenData.tileSize;
            double screenY = ScreenData.gameAreaY + margin + y * ScreenData.tileSize;

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
