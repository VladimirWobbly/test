import java.util.Scanner;

public class Main {

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String str1 = in.nextLine();
        try {
            System.out.println(calc(str1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String calc(String input) throws Exception {
        String[] str2 = input.split(" ");
        if (str2.length != 3) {
            throw new Exception("Выражение должно иметь вид a + b, где a и b - целые числа от 1 до 10 включительно.");
        }

        String result;
        if (isDigit(str2[0]) && isDigit(str2[2])) {
            int sym1 = Integer.parseInt(str2[0]);
            int sym2 = Integer.parseInt(str2[2]);
            if (sym1 <= 10 && sym2 <= 10 && sym1 >= 1 && sym2 >= 1) {
                switch (str2[1]) {
                    case "+":
                        result = String.valueOf(sym1 + sym2);
                        break;
                    case "-":
                        result = String.valueOf(sym1 - sym2);
                        break;
                    case "*":
                        result = String.valueOf(sym1 * sym2);
                        break;
                    case "/":
                        if (sym2 == 0) {
                            throw new ArithmeticException("Деление на ноль.");
                        }
                        result = String.valueOf(sym1 / sym2);
                        break;
                    default:
                        throw new Exception("Использована некорректная операция.");
                }
            } else {
                throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно.");
            }
        } else {
            boolean isValidRoman = true;
            try {
                Rome.valueOf(str2[0]);
                Rome.valueOf(str2[2]);
            } catch (IllegalArgumentException e) {
                isValidRoman = false;
            }

            if (isValidRoman) {
                int sym1 = Rome.valueOf(str2[0]).getznachen();
                int sym2 = Rome.valueOf(str2[2]).getznachen();
                if (sym1 <= 10 && sym2 <= 10 && sym1 >= 1 && sym2 >= 1) {
                    switch (str2[1]) {
                        case "+":
                            result = String.valueOf(Rome.getByValue(sym1 + sym2));
                            break;
                        case "-":
                            if (sym1 - sym2 <= 0) {
                                throw new Exception("Результат работы с римскими числами должен быть положительным.");
                            }
                            result = String.valueOf(Rome.getByValue(sym1 - sym2));
                            break;
                        case "*":
                            result = String.valueOf(Rome.getByValue(sym1 * sym2));
                            break;
                        case "/":
                            if (sym2 == 0) {
                                throw new ArithmeticException("Деление на ноль.");
                            }
                            result = String.valueOf(Rome.getByValue(sym1 / sym2));
                            break;
                        default:
                            throw new Exception("Использована некорректная операция.");
                    }
                } else {
                    throw new Exception("Числа должны быть от I до X включительно.");
                }
            } else {
                throw new Exception("Выражение должно содержать либо только арабские, либо только римские числа.");
            }
        }
        return result;
    }
}