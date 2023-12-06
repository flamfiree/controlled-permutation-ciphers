package coding.sp_net;
import java.util.ArrayList;
import java.util.Random;

public class PBox {
    public final int[][] table;

    public PBox(int pixelCount) {
        table = new int[pixelCount][2];

        for (int i = 0; i < table.length; i++) {
            table[i][0] = i;
            table[i][1] = i;
        }

        Random rnd = new Random();
        for (int i = table.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = table[index][1];
            table[index][1] = table[i][1];
            table[i][1] = temp;
        }

//            for (int[] ints : table) {
//                System.out.println(ints[0] + " , " + ints[1]);
//            }
    }

    public String[] pBox_encrypt(String[] pixAllBinary) {

        StringBuilder allBits = new StringBuilder();
        for (String pix : pixAllBinary) {
            allBits.append(pix);
        }

        String line = allBits.toString();
        String resLine = permutation_encrypt(line);

        return lineToMas(resLine);
    }

    private String permutation_encrypt(String line) {
        char[] bites = line.toCharArray();
        char[] res = new char[bites.length];

        for (int i = 0; i < bites.length; i++) {
            res[table[i][0]] = bites[table[i][1]];
        }


        return new String(res);
    }

    public String[] pBox_decrypt(String[] pixAllBinary) {

        StringBuilder allBits = new StringBuilder();
        for (String pix : pixAllBinary) {
            allBits.append(pix);
        }

        String line = allBits.toString();
        String resLine = permutation_decrypt(line);

        return lineToMas(resLine);
    }

    private String permutation_decrypt(String line) {
        char[] bites = line.toCharArray();
        char[] res = new char[bites.length];

        for (int i = 0; i < bites.length; i++) {
            res[table[i][1]] = bites[table[i][0]];
        }

        return new String(res);
    }

    private String[] lineToMas(String line) {
//        String[] mas = new String[line.length()/8];
        ArrayList<String> mas = new ArrayList<>();

        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i += 8) {
            StringBuilder pix = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                pix.append(chars[i + j]);
            }
            mas.add(pix.toString());
        }

        return mas.toArray(String[]::new);
    }
}
