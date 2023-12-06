package coding.contr_perm;

import java.util.Vector;

import services.BinaryService;

public class LogicalOperations {
    public static String xor(String x1, String x2) {
        int resInt = Integer.parseInt(x1, 2) ^ Integer.parseInt(x2, 2);
        String res = BinaryService.DecimalToBinary(resInt, x1.length());
        return res;
    }

    public static String xor2(String x1, String x2) {
        int a = Integer.parseInt(x1, 2);
        int b = Integer.parseInt(x2, 2);

        if(a == 0 && b == 0) {
            return "0";
        } else if(a == 0 && b == 1) {
            return "1";
        } else if(a == 1 && b == 0) {
            return "1";
        } else {
            return "0";
        }
    }

    public static String xor(String x1, String x2, int n) {
        int sum = Integer.parseInt(x1, 2) + Integer.parseInt(x2, 2);
        int resInt = sum % (int) Math.pow(2, n);
        String res = BinaryService.DecimalToBinary(resInt, x1.length());
        return res;
    }

    /**
     * grp перестановка, сначала нулевые, потом единичные
     * 
     * @param input входной сигнал
     * @param u управляющий сигнал
     * @return выходной сигнал
     */
    public static String permutation_grp(String input, String u) {
    String output = "";
    Vector<String> vector = new Vector<>(input.length());

    for (int i = 0; i < u.length(); i++) {
    if (u.charAt(i) == '0') {
    vector.add(String.valueOf(input.charAt(i)));
    }
    }
    for (int i = 0; i < u.length(); i++) {
    if (u.charAt(i) == '1') {
    vector.add(String.valueOf(input.charAt(i)));
    }
    }

    for (String y : vector) {
    output += y;
    }

    return output;
    }

    /**
     * дополняем для 8 битов на входе
     * @param input
     * @param expander
     * @return
     * 
     * */
    public static String extend8(String input, String expander) {
        String output = input;
        for (int i = 0; i < 4; i++) {
            String a = String.valueOf(input.charAt(i));
            String b = String.valueOf(input.charAt(i+1));
            String xor = xor2(a, b);

            output = xor + output;
        }

        // String output = input + expander.substring(0, 4);
        return output;
    }

    public static String permutation8(String input, String u) {
        String uExt = extend8(u, u);
        String output = "";
        Vector<String> vector = new Vector<>(input.length());

        int b0 = 
        toInt(input.charAt(0)) * not(toInt(uExt.charAt(0))) * not(toInt(uExt.charAt(6))) * not(toInt(uExt.charAt(8))) + 
        toInt(input.charAt(1)) * toInt(uExt.charAt(0))* not(toInt(uExt.charAt(6))) * not(toInt(uExt.charAt(8))) + 
        toInt(input.charAt(2)) * toInt(uExt.charAt(1))* toInt(uExt.charAt(6)) * not(toInt(uExt.charAt(8))) + 
        toInt(input.charAt(3)) * not(toInt(uExt.charAt(1)))* toInt(uExt.charAt(6)) * not(toInt(uExt.charAt(8))) + 
        toInt(input.charAt(4)) * not(toInt(uExt.charAt(2)))* not(toInt(uExt.charAt(7)) )* toInt(uExt.charAt(8)) + 
        toInt(input.charAt(5)) * toInt(uExt.charAt(2))* not(toInt(uExt.charAt(7)) )* toInt(uExt.charAt(8)) +  
        toInt(input.charAt(6)) * toInt(uExt.charAt(3))* toInt(uExt.charAt(7))* toInt(uExt.charAt(8)) +  
        toInt(input.charAt(7)) * not(toInt(uExt.charAt(3)))* toInt(uExt.charAt(7))* toInt(uExt.charAt(8));

        int b1 = 
        toInt(input.charAt(0)) * toInt(uExt.charAt(0)) * not(toInt(uExt.charAt(4))) * not(toInt(uExt.charAt(9))) + 
        toInt(input.charAt(1)) * not(toInt(uExt.charAt(0))) * not(toInt(uExt.charAt(4))) * not(toInt(uExt.charAt(9))) + 
        toInt(input.charAt(2)) * not(toInt(uExt.charAt(1))) * toInt(uExt.charAt(4))  * not(toInt(uExt.charAt(9))) + 
        toInt(input.charAt(3)) * toInt(uExt.charAt(1)) * toInt(uExt.charAt(4)) * not(toInt(uExt.charAt(9))) + 
        toInt(input.charAt(4)) * toInt(uExt.charAt(2)) * not(toInt(uExt.charAt(5))) * toInt(uExt.charAt(9)) + 
        toInt(input.charAt(5)) * not(toInt(uExt.charAt(2))) * not(toInt(uExt.charAt(5))) * toInt(uExt.charAt(9)) +  
        toInt(input.charAt(6)) * not(toInt(uExt.charAt(3)))  * toInt(uExt.charAt(5)) * toInt(uExt.charAt(9)) +  
        toInt(input.charAt(7)) * toInt(uExt.charAt(3)) * toInt(uExt.charAt(5)) * toInt(uExt.charAt(9));

        int b2 = 
        toInt(input.charAt(0)) * toInt(uExt.charAt(0)) * toInt(uExt.charAt(4)) * not(toInt(uExt.charAt(10))) + 
        toInt(input.charAt(1)) * not(toInt(uExt.charAt(0))) * toInt(uExt.charAt(4)) * not(toInt(uExt.charAt(10))) + 
        toInt(input.charAt(2)) * not(toInt(uExt.charAt(1))) * not(toInt(uExt.charAt(4))) * not(toInt(uExt.charAt(10))) + 
        toInt(input.charAt(3)) * toInt(uExt.charAt(1)) * not(toInt(uExt.charAt(4))) * not(toInt(uExt.charAt(10))) + 
        toInt(input.charAt(4)) * toInt(uExt.charAt(2)) * toInt(uExt.charAt(5)) * toInt(uExt.charAt(10)) + 
        toInt(input.charAt(5)) * not(toInt(uExt.charAt(2))) * toInt(uExt.charAt(5))* toInt(uExt.charAt(10)) +  
        toInt(input.charAt(6)) * not(toInt(uExt.charAt(3))) * not(toInt(uExt.charAt(5))) * toInt(uExt.charAt(10)) +  
        toInt(input.charAt(7)) * toInt(uExt.charAt(3)) * not(toInt(uExt.charAt(5))) * toInt(uExt.charAt(10));

        int b3 = 
        toInt(input.charAt(0)) * not(toInt(uExt.charAt(0))) * toInt(uExt.charAt(6)) * not(toInt(uExt.charAt(11))) + 
        toInt(input.charAt(1)) * toInt(uExt.charAt(0)) * toInt(uExt.charAt(6)) * not(toInt(uExt.charAt(11))) + 
        toInt(input.charAt(2)) * toInt(uExt.charAt(1)) * not(toInt(uExt.charAt(6))) * not(toInt(uExt.charAt(11))) + 
        toInt(input.charAt(3)) * not(toInt(uExt.charAt(1))) * not(toInt(uExt.charAt(6))) * not(toInt(uExt.charAt(11))) + 
        toInt(input.charAt(4)) * not(toInt(uExt.charAt(2))) * toInt(uExt.charAt(7)) * toInt(uExt.charAt(11)) + 
        toInt(input.charAt(5)) * toInt(uExt.charAt(2)) * toInt(uExt.charAt(7))* toInt(uExt.charAt(11)) +  
        toInt(input.charAt(6)) * toInt(uExt.charAt(3))* not(toInt(uExt.charAt(7))) * toInt(uExt.charAt(11)) +  
        toInt(input.charAt(7)) * not(toInt(uExt.charAt(3))) * not(toInt(uExt.charAt(7))) * toInt(uExt.charAt(11));
        
        int b4 = 
        toInt(input.charAt(0)) * not(toInt(uExt.charAt(0))) * not(toInt(uExt.charAt(6))) * toInt(uExt.charAt(8)) + 
        toInt(input.charAt(1)) * toInt(uExt.charAt(0)) * not(toInt(uExt.charAt(6))) * toInt(uExt.charAt(8)) + 
        toInt(input.charAt(2)) * toInt(uExt.charAt(1)) * (toInt(uExt.charAt(6))) * (toInt(uExt.charAt(8))) + 
        toInt(input.charAt(3)) * not(toInt(uExt.charAt(1))) * (toInt(uExt.charAt(6))) * (toInt(uExt.charAt(8))) + 
        toInt(input.charAt(4)) * not(toInt(uExt.charAt(2))) * not(toInt(uExt.charAt(7))) * not(toInt(uExt.charAt(8))) + 
        toInt(input.charAt(5)) * (toInt(uExt.charAt(2))) * not(toInt(uExt.charAt(7))) * not(toInt(uExt.charAt(8))) +  
        toInt(input.charAt(6)) * toInt(uExt.charAt(3))* toInt(uExt.charAt(7)) * not(toInt(uExt.charAt(8))) +  
        toInt(input.charAt(7)) * not(toInt(uExt.charAt(3))) * (toInt(uExt.charAt(7))) * not(toInt(uExt.charAt(8)));

        int b5 = 
        toInt(input.charAt(0)) * (toInt(uExt.charAt(0))) * not(toInt(uExt.charAt(4))) * toInt(uExt.charAt(9)) + 
        toInt(input.charAt(1)) * not(toInt(uExt.charAt(0))) * not(toInt(uExt.charAt(4))) * toInt(uExt.charAt(9)) + 
        toInt(input.charAt(2)) * not(toInt(uExt.charAt(1)))* (toInt(uExt.charAt(4))) * (toInt(uExt.charAt(9))) + 
        toInt(input.charAt(3)) * (toInt(uExt.charAt(1))) * (toInt(uExt.charAt(4))) * (toInt(uExt.charAt(9))) + 
        toInt(input.charAt(4)) * (toInt(uExt.charAt(2))) * not(toInt(uExt.charAt(5))) * not(toInt(uExt.charAt(9))) + 
        toInt(input.charAt(5)) * not(toInt(uExt.charAt(2))) * not(toInt(uExt.charAt(5))) * not(toInt(uExt.charAt(9))) +  
        toInt(input.charAt(6)) * not(toInt(uExt.charAt(3)))* toInt(uExt.charAt(5)) * not(toInt(uExt.charAt(9))) +  
        toInt(input.charAt(7)) * (toInt(uExt.charAt(3))) * (toInt(uExt.charAt(5))) * not(toInt(uExt.charAt(9)));

        int b6 = 
        toInt(input.charAt(0)) * (toInt(uExt.charAt(0))) * (toInt(uExt.charAt(4))) * (toInt(uExt.charAt(10))) + 
        toInt(input.charAt(1)) * not(toInt(uExt.charAt(0))) * (toInt(uExt.charAt(4))) * (toInt(uExt.charAt(10))) + 
        toInt(input.charAt(2)) * not(toInt(uExt.charAt(1)))* not(toInt(uExt.charAt(4))) * (toInt(uExt.charAt(10))) + 
        toInt(input.charAt(3)) * (toInt(uExt.charAt(1))) * not(toInt(uExt.charAt(4))) * (toInt(uExt.charAt(10))) + 
        toInt(input.charAt(4)) * (toInt(uExt.charAt(2))) * (toInt(uExt.charAt(5))) * not(toInt(uExt.charAt(10))) + 
        toInt(input.charAt(5)) * not(toInt(uExt.charAt(2))) * (toInt(uExt.charAt(5))) * not(toInt(uExt.charAt(10))) +  
        toInt(input.charAt(6)) * not(toInt(uExt.charAt(3)))* not(toInt(uExt.charAt(5))) * not(toInt(uExt.charAt(10))) +  
        toInt(input.charAt(7)) * (toInt(uExt.charAt(3))) * not(toInt(uExt.charAt(5))) * not(toInt(uExt.charAt(10)));

        int b7 = 
        toInt(input.charAt(0)) * not(toInt(uExt.charAt(0))) * (toInt(uExt.charAt(6))) * (toInt(uExt.charAt(11))) + 
        toInt(input.charAt(1)) * (toInt(uExt.charAt(0))) * (toInt(uExt.charAt(6))) * (toInt(uExt.charAt(11))) + 
        toInt(input.charAt(2)) * (toInt(uExt.charAt(1)))* not(toInt(uExt.charAt(6))) * (toInt(uExt.charAt(11))) + 
        toInt(input.charAt(3)) * not(toInt(uExt.charAt(1))) * not(toInt(uExt.charAt(6))) * (toInt(uExt.charAt(11))) + 
        toInt(input.charAt(4)) * not(toInt(uExt.charAt(2))) * (toInt(uExt.charAt(7))) * not(toInt(uExt.charAt(11))) + 
        toInt(input.charAt(5)) * (toInt(uExt.charAt(2))) * (toInt(uExt.charAt(7))) * not(toInt(uExt.charAt(11))) +  
        toInt(input.charAt(6)) * (toInt(uExt.charAt(3)))* not(toInt(uExt.charAt(7))) * not(toInt(uExt.charAt(11))) +  
        toInt(input.charAt(7)) * not(toInt(uExt.charAt(3))) * not(toInt(uExt.charAt(7))) * not(toInt(uExt.charAt(11)));

        output = String.valueOf(b0) + String.valueOf(b1) + String.valueOf(b2) + String.valueOf(b3) + String.valueOf(b4) + String.valueOf(b5) + String.valueOf(b6) + String.valueOf(b7);

        return output;
    }

    public static int toInt(String bit) {
        return Integer.parseInt(bit, 2);
    }

    public static int toInt(char bit) {
        return Integer.parseInt(String.valueOf(bit), 2);
    }

    public static int not(int bit) {
        return (bit == 0) ? 1 : 0;
    }
}
