package services;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Класс для создания монохромного изображения
 */
public class ColorToMonoConverter {
    /**
     * Метод для создания монохромного изображения
     *
     * @param inputImagePath  путь до исх. Изображения
     * @param outputImagePath путь для сохранения монохромного изображения
     */
    public static void ColorToMono(String inputImagePath, String outputImagePath) {
        try {
            // Загрузка цветного изображения
            BufferedImage inputImage = ImageIO.read(new File(inputImagePath));

            // Преобразование в монохромное изображение
            BufferedImage monoImage = convertToMono(inputImage);

            // Сохранение монохромного изображения
            ImageIO.write(monoImage, "png", new File(outputImagePath));

            System.out.println("Монохромное изображение создано успешно.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage convertToMono(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        BufferedImage monoImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Получение цвета пикселя
                Color color = new Color(inputImage.getRGB(x, y));

                // Вычисление яркости пикселя (среднее значение каналов RGB)
                int grayValue = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
            //    float grayValue = color.getRed()*0.2126f + color.getGreen()*0.7152f + color.getBlue()*0.0722f;

                // Создание нового монохромного пикселя
                Color grayColor = new Color(grayValue, grayValue, grayValue);
            //    Color grayColor = new Color(color.getRed()*0.2126f/256, color.getGreen()*0.7152f/256, color.getBlue()*0.0722f/256);

                monoImage.setRGB(x, y, grayColor.getRGB());
            }
        }
        return monoImage;
    }
}
