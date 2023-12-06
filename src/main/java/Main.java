import java.awt.image.BufferedImage;

import graphics.DataGraphics;
import image.Image;
import services.BinaryService;
import services.ColorToMonoConverter;
import coding.contr_perm.Controller;

public class Main {
    private static final int roundCount = 25;
    private static final int blockSize = 16;

    public static void main(String[] args) {
        encrypt();
        // decrypt();
    }

    private static void encrypt() {
        // final String inputImagePath = "resources/image2.jpg";
        // final String imageMonoPath = "resources/image2-bw.png";
        // final String resultPath = "resources/image2-encrypt.png";

        final String imageNumber = "3";
        final String inputImagePath = "resources/image" + imageNumber + ".jpg";
        final String imageMonoPath = "resources/image" + imageNumber + "-bw.png";
        final String resultPath = "resources/image" + imageNumber + "-encrypt.png";
        
        // переводим в чб и делаем изначальные графики
        ColorToMonoConverter.ColorToMono(inputImagePath, imageMonoPath);
        DataGraphics.initialDataGraphics(imageMonoPath);

        // переводим в двоичный вектор
        String imageBinary = BinaryService.imageToBinary(Image.loadAndReadImage(imageMonoPath));

        // шифруем
        String encryptMessage = Controller.encrypt(imageBinary, blockSize, roundCount);
        // сохраняем
        BufferedImage image = BinaryService.binaryToImage(encryptMessage);
        Image.saveImage(image, resultPath);

        // графики шифрованного изображения
        DataGraphics.encryptDataGraphics(resultPath);

    }

    private static void decrypt() {
        final String encryptPath = "resources/image2-encrypt.png";
        final String decryptPath = "resources/image2-decrypt.png";
        // DataGraphics.encryptDataGraphics(encryptPath);

        // переводим в двоичный вектор
        String imageBinary = BinaryService.imageToBinary(Image.loadAndReadImage(encryptPath));

        // расшифровываем
        String decryptMessage = Controller.decrypt(imageBinary, blockSize, roundCount);

        // сохраняем
        BufferedImage image = BinaryService.binaryToImage(decryptMessage);
        Image.saveImage(image, decryptPath);

        // графики расшифрованного изображения
        DataGraphics.decryptDataGraphics(decryptPath);
    }
}