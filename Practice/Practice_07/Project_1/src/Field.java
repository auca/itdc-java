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

    static int levelIndex = 0;

    public static void loadNextLevel() {
        ++levelIndex;
        if (levelIndex >= Levels.LEVELS.length) {
            levelIndex = 0;
        }

        loadLevel(levelIndex);
    }

    public static void loadLevel(int newLevelIndex) {
        levelIndex = newLevelIndex;
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

    public static void draw(Graphics2D g2) {
        for (int y = 0; y < Field.height; ++y) {
            for (int x = 0; x < Field.width; ++x) {
                int screenX = ScreenData.gameAreaX + x * ScreenData.tileSize;
                int screenY = ScreenData.gameAreaY + y * ScreenData.tileSize;
                switch (Field.field[y][x]) {
                    case Field.EMPTY_CELL:
                        g2.setColor(EMPTY_CELL_COLOR);
                        g2.fillRect(screenX, screenY, ScreenData.tileSize - 1, ScreenData.tileSize - 1);
                        break;
                    case Field.WALL_CELL:
                        g2.drawImage(
                            WALL_CELL_IMAGE,
                            screenX,
                            screenY,
                            ScreenData.tileSize - 1,
                            ScreenData.tileSize - 1,
                            null,
                            null
                        );
                        break;
                }
            }
        }

        for (int i = 0; i < coins.length; ++i) {
            coins[i].draw(g2);
        }
    }

    public static Coin findCoin(int coinX, int coinY) {
        for (int i = 0; i < coins.length; ++i) {
            Coin coin = coins[i];
            if (coin.x == coinX && coin.y == coinY && !coin.isCollected) {
                return coin;
            }
        }

        return null;
    }

    public static boolean hasCoins() {
        for (int i = 0; i < coins.length; ++i) {
            Coin coin = coins[i];
            if (!coin.isCollected) {
                return true;
            }
        }

        return false;
    }

    public static boolean canMove(int x, int y) {
        return x >= 0 && x < width &&
               y >= 0 && y < height &&
               field[y][x] != WALL_CELL;
    }
}
