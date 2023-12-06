package coding.sp_net;
import java.util.ArrayList;
import java.util.Random;

public class SBox {
    //    private static boolean isTableCreate = false;
    public final int[][] table;

    public SBox(int pixelCount) {
        //Создаём таблицу замены только один раз на всю программу

        table = new int[(int) Math.pow(2, pixelCount)][2];

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

    public String[] sBox_encrypt(String line) {
        String resLine = this.substitution_encrypt(line);
        return lineToMas(resLine);
    }

    private String substitution_encrypt(String line) {

        int decimalNumber = Integer.parseInt(line, 2);
        int decimalResult = 0;
        for (int[] ints : this.table) {
            if (ints[0] == decimalNumber) {
                decimalResult = ints[1];
            }
        }

        StringBuilder res = new StringBuilder(Integer.toBinaryString(decimalResult));
        if (res.length() < line.length()) {
            while (res.length() != line.length()) {
                res.insert(0, "0");
            }
        }

        return res.toString();
    }

    public String[] sBox_decrypt(String line) {
        String resLine = this.substitution_decrypt(line);
        return lineToMas(resLine);
    }

    private String substitution_decrypt(String line) {

        int decimalNumber = Integer.parseInt(line, 2);
        int decimalResult = 0;
        for (int[] ints : this.table) {
            if (ints[1] == decimalNumber) {
                decimalResult = ints[0];
            }
        }

        StringBuilder res = new StringBuilder(Integer.toBinaryString(decimalResult));
        if (res.length() < line.length()) {
            while (res.length() != line.length()) {
                res.insert(0, "0");
            }
        }

        return res.toString();
    }

    private static String[] lineToMas(String line) {
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
