package image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Image {
    /**
     * Сохраняет изображение по указанному пути
     * @param image изображение
     * @param filePath путь для сохранения
     */
    public static void saveImage(BufferedImage image, String filePath){
        try {
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает изображение по указанному пути
     * @param filePath путь до изображения
     */
    public static BufferedImage loadAndReadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage[] loadBlocks(String dirAndPrefix, int numBlocks) {
        BufferedImage[] blocks = new BufferedImage[numBlocks];

        for (int i = 0; i < numBlocks; i++) {
            String filePath = dirAndPrefix + i + ".png";
            BufferedImage block = Image.loadAndReadImage(filePath);
            blocks[i] = block;
        }
        return blocks;
    }
}

