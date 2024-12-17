import java.util.Scanner;

public class text1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        try {
            System.out.println(calc(input));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    public static String calc(String input) {
        String[] parts = input.split(" "); // разделяем строку

        if (parts.length != 3) {
            throw new IllegalArgumentException("throws Exception");
        }
        boolean arabic1 = parts[0].matches("\\d+"); // проверяем чтобы числа были одной системы счисления
        boolean roman1 = parts[0].matches("[IVX]+");
        boolean arabic2 = parts[2].matches("\\d+");
        boolean roman2 = parts[2].matches("[IVX]+");

        if ((arabic1 && roman2) || (roman1 && arabic2)) {  // выводится ошибка, если используются одновременно разные системы счисления
            throw new IllegalArgumentException("throws Exception");
        }

        int num1;
        int num2;
        String operator = parts[1];

        if (arabic1) {
            num1 = Integer.parseInt(parts[0]);
            if(num1 < 1 || num1 > 10) {
            throw new IllegalArgumentException("throws Exception");
            }
        } else {
            num1 = romanToArabic(parts[0]);
            if(num1 < 1 || num1 > 10){
                throw new IllegalArgumentException("throws Exception");
            }
        }
        if (arabic2) {
            num2 = Integer.parseInt(parts[2]);
            if(num2 < 1 || num2 > 10){
                throw new IllegalArgumentException("throws Exception");
            }
        } else {
            num2 = romanToArabic(parts[2]);
            if(num2 < 1 || num2 > 10){
                throw new IllegalArgumentException("throws Exception");
            }
        }

        int res;
        switch (operator) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "*":
                res = num1 * num2;
                break;

            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("throws Exception");
                }
                res = num1 / num2;

                break;
            default:
                throw new IllegalArgumentException("throws Exception");
        }
        if (arabic1  && arabic2) {
            return String.valueOf(res);
        } else if (res > 0) {
            return arabicToRoman(res);}
        else {
            throw new IllegalArgumentException("throws Exception");
        }
    }

    public static int romanToArabic(String roman) { // переводим из риских в арабские
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new IllegalArgumentException("throws Exception");
        };
    }
    public static String arabicToRoman(int num) { // переводим в римские
        StringBuilder roman = new StringBuilder();
        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                roman.append(symbols[i]);
                num -= values[i];
            }
        }
        return roman.toString();
    }
}
