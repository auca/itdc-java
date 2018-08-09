import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.io.File;

public class FontLoader {

    public static final String BASE_DIR = "data";

    public static void load(String filename) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(getFontFilePath(filename))));
        } catch (Exception e) {
            System.err.println("Failed to load the font: " + e.getMessage());
            System.exit(-1);
        }
    }

    public static String getFontFilePath(@NotNull String filename) {
        return BASE_DIR + File.separator + filename;
    }

}
