package coding.sp_net;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import image.Image;
import services.BinaryService;

public class SP_Net extends Image {
    public final SBox sBox;
    public final PBox pBox;

    public SP_Net(int pixelCount, int countOfSBoxes) {
        sBox = new SBox(pixelCount * 8/ countOfSBoxes);
        pBox = new PBox(pixelCount * 8);
    }

    public BufferedImage SP_Encrypt(BufferedImage block, int blockSize, int roundCount, int countOfSBoxes) {
        //результат
        BufferedImage encrypt;
        //строка, в которой все пиксели блока
        String[] inputBlockBinary = BinaryService.blockToBinary(block);
        //строка всех пикселей после раундов
        String[] outputBlockBinary = new String[inputBlockBinary.length];

        for (int i = 0; i < roundCount; i++) {
            outputBlockBinary = round_Encrypt(inputBlockBinary, blockSize * blockSize, countOfSBoxes);
//            outputBlockBinary = inputBlockBinary;
        }

        encrypt = BinaryService.binaryToBlock(outputBlockBinary, blockSize);

        return encrypt;
    }

    private String[] round_Encrypt(String[] blockBinary, int pixCount, int countOfSBoxes) {
        String[] result;
        ArrayList<String> outputBlockBinary = new ArrayList<>();

        StringBuilder allBits = new StringBuilder();
        for (String pix : blockBinary) {
            allBits.append(pix);
        }
        String blockLine = allBits.toString();

        int partLength = pixCount * 8 / countOfSBoxes;

        for (int i = 0; i < blockLine.length(); i += partLength) {
            outputBlockBinary.addAll(List.of(sBox.sBox_encrypt(blockLine.substring(i, i + partLength))));
        }

        result = pBox.pBox_encrypt(outputBlockBinary.toArray(String[]::new));
//        result = pBox.pBox_encrypt(blockBinary);


//        return outputBlockBinary.toArray(String[]::new);
        return result;
    }

    public BufferedImage SP_Decrypt(BufferedImage block, int blockSize, int roundCount, int countOfSBoxes) {
        //результат
        BufferedImage decrypt;
        //строка, в которой все пиксели блока
        String[] inputBlockBinary = BinaryService.blockToBinary(block);
        //строка всех пикселей после раундов
        String[] outputBlockBinary = new String[inputBlockBinary.length];

        for (int i = 0; i < roundCount; i++) {
            outputBlockBinary = round_Decrypt(inputBlockBinary, blockSize * blockSize, countOfSBoxes);
//            outputBlockBinary = inputBlockBinary;
        }

        decrypt = BinaryService.binaryToBlock(outputBlockBinary, blockSize);


        return decrypt;
    }

    private String[] round_Decrypt(String[] blockBinary, int pixCount, int countOfSBoxes) {
//        String[] pDecr = pBox.pBox_decrypt(blockBinary);


        String[] result;
        ArrayList<String> outputBlockBinary = new ArrayList<>();

        StringBuilder allBits = new StringBuilder();
        for (String pix : blockBinary) {
            allBits.append(pix);
        }
        String blockLine = allBits.toString();

        int partLength = pixCount * 8 / countOfSBoxes;

        for (int i = 0; i < blockLine.length(); i += partLength) {
            outputBlockBinary.addAll(List.of(sBox.sBox_decrypt(blockLine.substring(i, i + partLength))));
        }

        result = outputBlockBinary.toArray(String[]::new);


        return result;
//        return pDecr;
    }

}
