import com.sun.istack.internal.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageLoader {

    public static final String BASE_DIR = "data";

    public static BufferedImage load(String filename) {
        BufferedImage result = null;
        try {
            File imageFile = new File(getImageFilePath(filename));
            result = ImageIO.read(imageFile);
        } catch (Exception e) {
            System.err.println("Failed to load images: " + e.getMessage());
            System.exit(-1);
        }

        return result;
    }

    public static String getImageFilePath(@NotNull String filename) {
        return BASE_DIR + File.separator + filename;
    }

}
