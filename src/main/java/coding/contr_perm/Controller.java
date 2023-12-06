package coding.contr_perm;

public class Controller {

    private static String[] keys16 = new String[] {
            "1001011010010010",
            "0110010001010101",
            "1100001000011111"
    };
    private static String[] keysLeft8 = new String[] {
        "01100111","01110010","01000100","11110001","10101000","00110111","11010001","11100110",
        "10011011","01100100","11101011","01001011","01001000","10001100","10010000","01001100",
        "00111011","01101000","11111101","10100110","11101011","11110111","01010101","00100000", 
        "11110011" ,
        "01100111","01110010","01000100","11110001","10101000","00110111","11010001","11100110",
        "10011011","01100100","11101011","01001011","01001000","10001100","10010000","01001100",
        "00111011","01101000","11111101","10100110","11101011","11110111","01010101","00100000", 
        "11110011" 
    };


    private static String[] keysRight8 = new String[] {
        "10111100","11011110","01000010","00001100","00000101","11111101","11100000","10110100",
        "00101100","10100000","10000001","10010011","10100101","11100110","00100000","01111011",
        "01011001","00001010","11110000","00000000","11001100","00000000","11010011","10001001",
        "11111000",
        "10111100","11011110","01000010","00001100","00000101","11111101","11100000","10110100",
        "00101100","10100000","10000001","10010011","10100101","11100110","00100000","01111011",
        "01011001","00001010","11110000","00000000","11001100","00000000","11010011","10001001",
        "11111000"
    };

    public static String encrypt(String message, int blockSize, int roundCount) {
        String encrypt = new String();

        String[] blocks = getBlocks(message, blockSize);
        String[] encrypt_blocks = new String[blocks.length];

        for (int i = 0; i < roundCount; i++) {
            for (int j = 0; j < blocks.length; j++) {
                Block block = new Block(blocks[j]);

                if (block.getLeft().length() == 8) {
                    block = Encrypt.encrypt(block, keysLeft8[i], keysRight8[i]);
                }
                // if (block.getLeft().length() == 16) {
                //     block = Encrypt.encrypt(block, keys16[i], keys16[i]);
                // }
                encrypt_blocks[j] = block.getBlock(block.getLeft(), block.getRight());
            }
        }

        encrypt = getMessage(encrypt_blocks);

        return encrypt;
    }

    public static String decrypt(String message, int blockSize, int roundCount) {
        String decrypt = new String();

        String[] blocks = getBlocks(message, blockSize);
        String[] decrypt_blocks = new String[blocks.length];

        for (int i = 0; i < roundCount; i++) {
            for (int j = 0; j < blocks.length; j++) {
                Block block = new Block(blocks[j]);
                block = Decrypt.decrypt(block, keys16[roundCount - i], keys16[roundCount - i]);
                decrypt_blocks[j] = block.getBlock(block.getLeft(), block.getRight());
            }
        }

        decrypt = getMessage(decrypt_blocks);

        return decrypt;
    }

    private static String[] getBlocks(String message, int blockSize) {
        String[] blocks = new String[(int) (message.length() / blockSize)];
        int k = 0;
        for (int i = 0; i < message.length(); i += blockSize) {

            String block = message.substring(i, i + blockSize);
            blocks[k] = block;

            k++;
        }
        return blocks;
    }

    private static String getMessage(String[] blocks) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < blocks.length; i++) {
            stringBuilder.append(blocks[i]);
        }

        return stringBuilder.toString();
    }
}