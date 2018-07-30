import java.awt.*;

public class Coins {
    static int x[], y[];
    static boolean isCollected[];

    static int count;

    public static void create(int coinCount) {
        count = coinCount;

        x = new int[coinCount];
        y = new int[coinCount];
        isCollected = new boolean[coinCount];
    }

    public static void createCoin(int coinIndex, int newX, int newY) {
        x[coinIndex] = newX;
        y[coinIndex] = newY;
        isCollected[coinIndex] = false;
    }

    public static void draw(Graphics2D g2, int gameAreaX, int gameAreaY, int tileSize) {
        for (int i = 0; i < count; ++i) {
            if (!isCollected[i]) {
                int margin = (int) (tileSize * 0.1);
                int tileSizeWithMargin = tileSize - margin * 2;
                int screenX = gameAreaX + margin + x[i] * tileSize;
                int screenY = gameAreaY + margin + y[i] * tileSize;

                g2.setColor(Color.YELLOW);
                g2.fillOval(screenX, screenY, tileSizeWithMargin - 1, tileSizeWithMargin - 1);
            }
        }
    }

    public static int getCoinIndex(int coinX, int coinY) {
        for (int i = 0; i < count; ++i) {
            if (x[i] == coinX && y[i] == coinY && !isCollected[i]) {
                return i;
            }
        }

        return -1;
    }

    public static void collect(int coinIndex) {
        isCollected[coinIndex] = true;
    }
}
