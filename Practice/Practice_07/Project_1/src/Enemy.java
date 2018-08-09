import java.awt.*;

public class Enemy {
    final Color ENEMY_COLOR = new Color(255, 0, 0);

    int x, y;

    Enemy(int newX, int newY) {
        x = newX;
        y = newY;
    }

    void update() {
        
    }

    void draw(Graphics2D g2) {
        double margin = ScreenData.tileSize * 0.42;
        double tileSizeWithMargin = ScreenData.tileSize - margin * 2;
        double screenX = ScreenData.gameAreaX + margin + x * ScreenData.tileSize;
        double screenY = ScreenData.gameAreaY + margin + y * ScreenData.tileSize;

        g2.setColor(ENEMY_COLOR);
        g2.fillOval(
            (int) screenX,
            (int) screenY,
            (int) (tileSizeWithMargin - 1),
            (int) (tileSizeWithMargin - 1)
        );
    }
}
