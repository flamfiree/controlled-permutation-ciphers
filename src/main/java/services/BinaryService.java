package services;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class BinaryService {
    public static String[] blockToBinary(BufferedImage block) {
        List<String> binaryPixelValues = new ArrayList<>();

        for (int y = 0; y < block.getHeight(); y++) {
            for (int x = 0; x < block.getWidth(); x++) {

                int pixelValue = block.getRGB(x, y) & 0xFF; // Получаем значение пикселя (монохромное изображение)

                String binaryPixelValue = String.format("%8s", Integer.toBinaryString(pixelValue)).replace(' ', '0');
                binaryPixelValues.add(binaryPixelValue);
            }
        }
        return binaryPixelValues.toArray(new String[block.getHeight() * block.getWidth()]);
    }

    public static BufferedImage binaryToBlock(String[] blockBinary, int blockSize) {
        BufferedImage block = new BufferedImage(blockSize, blockSize, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < block.getHeight(); y++) {
            for (int x = 0; x < block.getWidth(); x++) {
                String binaryPixelValue = blockBinary[y * block.getWidth() + x];
                int pixelValue = Integer.parseInt(binaryPixelValue, 2);

                // Create a grayscale color using the pixel value
                Color color = new Color(pixelValue, pixelValue, pixelValue);

                // Set the color for the corresponding pixel in the image
                block.setRGB(x, y, color.getRGB());
            }
        }
        return block;
    }

    public static String imageToBinary(BufferedImage img) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                int pixelValue = img.getRGB(x, y) & 0xFF; // Получаем значение пикселя (монохромное изображение)

                String binaryPixelValue = String.format("%8s", Integer.toBinaryString(pixelValue)).replace(' ', '0');
                stringBuilder.append(binaryPixelValue);
            }
        }
        return stringBuilder.toString();
    }

    public static BufferedImage binaryToImage(String blockBinary) {

        StringBuilder stringBuilder = new StringBuilder(blockBinary);
        int width = (int) Math.sqrt(blockBinary.length() / 8);

        BufferedImage img = new BufferedImage(width, width, BufferedImage.TYPE_BYTE_GRAY);
        int counter = 0;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                String binaryPixelValue = stringBuilder.substring(counter, counter + 8);
                counter += 8;

                int pixelValue = Integer.parseInt(binaryPixelValue, 2);

                // Create a grayscale color using the pixel value
                Color color = new Color(pixelValue, pixelValue, pixelValue);

                // Set the color for the corresponding pixel in the image
                img.setRGB(x, y, color.getRGB());

            }
        }
        return img;
    }

    public static String DecimalToBinary(int decimal, int length){
        String binary = String.format("%8s", Integer.toBinaryString(decimal)).replace(' ', '0');
        while(binary.length() < length){
            binary = "0".concat(binary);
        }

        return binary;
    }
}
