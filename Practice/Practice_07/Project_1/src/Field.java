import java.awt.*;
import java.awt.image.BufferedImage;

public class Field {
    static final Color EMPTY_CELL_COLOR = Color.BLACK;

    final static BufferedImage WALL_CELL_IMAGE =
        ImageLoader.load("wall.png");

    static final char EMPTY_CELL  = ' ';
    static final char PACMAN_CELL = 'P';
    static final char WALL_CELL   = '#';
    static final char COIN_CELL   = 'o';

    static char[][] field;
    static int width, height;

    static Coin[] coins;

    public static void load(int levelIndex) {
        char[][] level = Levels.LEVELS[levelIndex];

        width = level[0].length;
        height = level.length;

        int coinCount = 0;

        field = new char[height][width];
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                char cell = level[y][x];
                switch (cell) {
                    case EMPTY_CELL:
                    case WALL_CELL:
                        field[y][x] = cell;
                        break;
                    case PACMAN_CELL:
                        field[y][x] = EMPTY_CELL;
                        Pacman.create(x, y);
                        break;
                    case COIN_CELL:
                        field[y][x] = EMPTY_CELL;
                        ++coinCount;
                        break;
                }
            }
        }

        coins = new Coin[coinCount];
        for (int coinIndex = 0, y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                char cell = level[y][x];
                if (cell == COIN_CELL) {
                    coins[coinIndex++] = new Coin(x, y);
                }
            }
        }
    }

    public static void draw(Graphics2D g2, int gameAreaX, int gameAreaY, int tileSize) {
        for (int y = 0; y < Field.height; ++y) {
            for (int x = 0; x < Field.width; ++x) {
                int screenX = gameAreaX + x * tileSize;
                int screenY = gameAreaY + y * tileSize;
                switch (Field.field[y][x]) {
                    case Field.EMPTY_CELL:
                        g2.setColor(EMPTY_CELL_COLOR);
                        g2.fillRect(screenX, screenY, tileSize - 1, tileSize - 1);
                        break;
                    case Field.WALL_CELL:
                        g2.drawImage(
                            WALL_CELL_IMAGE,
                            screenX,
                            screenY,
                            tileSize - 1,
                            tileSize - 1,
                            null,
                            null
                        );
                        break;
                }
            }
        }

        for (int i = 0; i < coins.length; ++i) {
            coins[i].draw(g2, gameAreaX, gameAreaY, tileSize);
        }
    }

    public static Coin getCoin(int coinX, int coinY) {
        for (int i = 0; i < coins.length; ++i) {
            Coin coin = coins[i];
            if (coin.x == coinX && coin.y == coinY && !coin.isCollected) {
                return coin;
            }
        }

        return null;
    }

    public static boolean canMove(int x, int y) {
        return x >= 0 && x < width &&
               y >= 0 && y < height &&
               field[y][x] != WALL_CELL;
    }
}
