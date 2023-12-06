public class EquationGenerator {

    public static void main(String[] args) {
        int numBits = 16; // Количество битов

        // Генерация уравнений
        for (int i = 0; i < numBits; i++) {
            StringBuilder equation = new StringBuilder("o_" + (i + 1) + " = ");

            for (int j = 0; j < numBits; j++) {
                equation.append("i_" + (j + 1) + " * (");

                for (int k = 0; k < numBits; k++) {
                    if (k != j) {
                        equation.append(simplifySignal("s_" + (k + 1)));
                    } else {
                        equation.append(simplifySignal("s_" + (k + 1), true));
                    }

                    if (k != numBits - 1) {
                        equation.append(" * ");
                    }
                }

                equation.append(") \n+ ");
            }

            equation.delete(equation.length() - 3, equation.length()); // Удаление лишних символов в конце строки
            equation.append(";");
            System.out.println(equation.toString());
        }
    }

    // Функция для инвертирования сигнала
    private static String simplifySignal(String signal) {
        return "!(" + signal + ")";
    }

    // Перегруженная функция для инвертирования сигнала или его оставления без изменений
    private static String simplifySignal(String signal, boolean invert) {
        return invert ? signal : simplifySignal(signal);
    }
}