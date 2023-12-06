package image;
import java.awt.image.BufferedImage;

public class BlocksCreate extends Image {

    public static BufferedImage[] blocksCreate(int blockSize, String imagePath) {
        // Загрузите изображение
        BufferedImage image = Image.loadAndReadImage(imagePath);

        // Разделите изображение на блоки
        assert image != null;
        BufferedImage[] blocks = divideImageIntoBlocks(image, blockSize);

        System.out.println("Блоки созданы успешно.");

        return blocks;
    }

    // Метод для разделения изображения на блоки
    private static BufferedImage[] divideImageIntoBlocks(BufferedImage image, int blockSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        int numBlocksX = width / blockSize;
        int numBlocksY = height / blockSize;
        int totalBlocks = numBlocksX * numBlocksY;

        BufferedImage[] blocks = new BufferedImage[totalBlocks];

        int blockIndex = 0;

        for (int y = 0; y < numBlocksY; y++) {
            for (int x = 0; x < numBlocksX; x++) {
                int startX = x * blockSize;
                int startY = y * blockSize;

                BufferedImage block = new BufferedImage(blockSize, blockSize, BufferedImage.TYPE_BYTE_GRAY);

                for (int i = 0; i < blockSize; i++) {
                    for (int j = 0; j < blockSize; j++) {
                        int pixel = image.getRGB(startX + j, startY + i);
                        block.setRGB(j, i, pixel);
                    }
                }

                blocks[blockIndex] = block;
                blockIndex++;
            }
        }

        return blocks;
    }
}
