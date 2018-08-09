public class ScreenData {
    static boolean isWindowsVisible = false;

    static int tileSize;
    static int gameAreaX;
    static int gameAreaY;
    static int margin;
    static int fontSize;

    static void recalculateScreenData(int width, int height) {
        isWindowsVisible = true;
        int screenWidth = width;
        int screenHeight = height;

        tileSize = (int) (Math.min(screenWidth / Field.width, screenHeight / Field.height) * 0.9f);
        int gameAreaWidth = Field.width * tileSize;
        int gameAreaHeight = Field.height * tileSize;
        gameAreaX = (screenWidth - gameAreaWidth) / 2;
        gameAreaY = (screenHeight - gameAreaHeight) / 2;

        int minSide = Math.min(screenWidth, screenHeight);
        margin = (int) (minSide * 0.2);
        fontSize = (int) (minSide * 0.1);
    }
}
