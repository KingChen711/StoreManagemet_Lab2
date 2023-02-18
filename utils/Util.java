package utils;

import java.util.Scanner;
import java.util.function.Predicate;

public final class Util {

    private Util() {

    }

    public static String inputString(String inputName) {
        return inputString(inputName, "not empty");
    }

    public static String inputString(String inputName, String condition) {
        String result;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(" + condition + "): ");
            result = sc.nextLine().trim();
            if ("".equals(result)) {
                System.out.println("Your " + inputName + " is empty. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputStringUpdate(String inputName, String oldValue) {
        return inputStringUpdate(inputName, "get old value if blank", oldValue);
    }

    public static String inputStringUpdate(String inputName, String condition, String oldValue) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter " + inputName + "(" + condition + "): ");
        String result = sc.nextLine().trim();
        return result.equals("") ? oldValue : result;
    }

    public static String inputStringLength(String inputName, int minLength, int maxLength) {
        String result;
        while (true) {
            result = inputString(inputName, "length from " + minLength + " to " + maxLength + " characters");
            if (result.length() < minLength || result.length() > maxLength) {
                System.out.println("Your " + inputName + " is too short or too long. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputStringLengthUpdate(
            String inputName,
            int minLength,
            int maxLength,
            String oldValue) {
        String result;
        while (true) {
            result = inputStringUpdate(inputName,
                    "get old value if blank, length from " + minLength + " to " + maxLength + " characters",
                    oldValue);
            if (result.length() < minLength || result.length() > maxLength) {
                System.out.println("Your " + inputName + " is too short or too long. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputWithFormat(String inputName, String condition, String format) {
        String result;
        while (true) {
            result = inputString(inputName, condition);
            if (!result.matches(format)) {
                System.out.println("Your " + inputName + " is wrong format. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputId(String inputName, String condition, Predicate<String> checkUnique) {
        String result;
        while (true) {
            result = inputString(inputName, condition);
            if (!checkUnique.test(result)) {
                System.out.println("Your " + inputName + " is not unique. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputExistId(String inputName, String condition, String format,
            Predicate<String> checkExistId) {
        String result;
        while (true) {
            result = inputWithFormat(inputName, condition, format);
            if (!checkExistId.test(result)) {
                System.out.println("Your " + inputName + " is not exist in database. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputIdWithFormat(String inputName, String condition, Predicate<String> checkUnique,
            String format) {
        String result;
        while (true) {
            result = inputId(inputName, condition, checkUnique);
            if (!result.matches(format)) {
                System.out.println("Your " + inputName + " is wrong format. Try again!");
                continue;
            }
            return result;
        }
    }

    public static int inputPositiveInteger(String inputName) {
        int result;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(greater than 0, integer): ");
            if (!sc.hasNextInt()) {
                System.out.println("Your " + inputName + " is not a number or not integer. Try again!");
                continue;
            }
            result = sc.nextInt();
            if (result <= 0) {
                System.out.println("Your " + inputName + " is 0 or less than 0. Try again!");
                continue;
            }
            return result;
        }
    }

    public static int inputPositiveIntegerUpdate(String inputName, int oldValue) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(greater than 0, integer, get old value if blank): ");

            String userInput = sc.nextLine().trim();
            if (userInput.isEmpty()) {
                return oldValue;
            }
            try {
                int newValue = Integer.parseInt(userInput);
                if (newValue <= 0) {
                    System.out.println("Your " + inputName + " is 0 or less than 0. Try again!");
                    continue;
                }
                return newValue;
            } catch (NumberFormatException e) {
                System.out.println("Your " + inputName + " is not a number or not integer. Try again!");
            }
        }
    }

    public static boolean inputBoolean(String inputName) {
        boolean result;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(true or false): ");
            if (!sc.hasNextBoolean()) {
                System.out.println("Your " + inputName + " is not a boolean value. Try again!");
                continue;
            }
            result = sc.nextBoolean();
            return result;
        }
    }

    public static boolean inputBooleanUpdate(String inputName, boolean oldValue) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(true or false, get old value if blank): ");

            String userInput = sc.nextLine().trim();
            if (userInput.isEmpty()) {
                return oldValue;
            }
            try {
                return Boolean.parseBoolean(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Your " + inputName + " is not a boolean value. Try again!");
            }
        }
    }

    public static int inputIntegerWithRange(String message, int minValue, int maxValue) {
        int val = Integer.MIN_VALUE;
        message = message + " in the range [" + minValue + ", " + maxValue + "]: ";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message);
            try {
                val = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println(">>>>> Error: " + ex.getMessage());
            }
        } while (val < minValue || maxValue < val);
        return val;
    }
}
