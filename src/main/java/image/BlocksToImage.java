package image;
import java.awt.image.BufferedImage;

public class BlocksToImage extends Image {

    public static void blocksToImage(int blockSize, String resultPath, BufferedImage[] blocks) {
        // Соберите блоки в изображение
        BufferedImage reconstructedImage = assembleBlocks(blocks, blockSize);

        // Сохраните восстановленное изображение
        Image.saveImage(reconstructedImage, resultPath);

        System.out.println("Собранное изображение создано успешно.");
    }

    // Метод для сборки блоков в изображение
    private static BufferedImage assembleBlocks(BufferedImage[] blocks, int blockSize) {
        int numBlocksX = (int) Math.sqrt(blocks.length);
        int numBlocksY = numBlocksX;
        int width = numBlocksX * blockSize;
        int height = numBlocksY * blockSize;

        BufferedImage reconstructedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        int blockIndex = 0;

        for (int y = 0; y < numBlocksY; y++) {
            for (int x = 0; x < numBlocksX; x++) {
                int startX = x * blockSize;
                int startY = y * blockSize;

                BufferedImage block = blocks[blockIndex];

                for (int i = 0; i < blockSize; i++) {
                    for (int j = 0; j < blockSize; j++) {
                        int pixel = block.getRGB(j, i);
                        reconstructedImage.setRGB(startX + j, startY + i, pixel);
                    }
                }

                blockIndex++;
            }
        }

        return reconstructedImage;
    }
}
