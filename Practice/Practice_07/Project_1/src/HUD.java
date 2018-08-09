import java.awt.*;

public class HUD {

    static final Color SCORE_COLOR = Color.WHITE;

    static void init() {
        FontLoader.load("ARCADECLASSIC.TTF");
    }

    static void draw(Graphics2D g2) {
        Font scoreFont = new Font("ARCADECLASSIC", Font.PLAIN, ScreenData.fontSize);

        g2.setColor(Color.BLACK);
        g2.setFont(scoreFont);
        g2.drawString("Score " + Pacman.score, ScreenData.margin, ScreenData.margin);

        g2.setColor(SCORE_COLOR);
        g2.drawString("Score " + Pacman.score, ScreenData.margin + 2, ScreenData.margin + 2);
    }

}
