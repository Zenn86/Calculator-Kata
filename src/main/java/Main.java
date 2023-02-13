import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter math expression with space-bar delimiter. Use numbers from 1 to 10. Type \"exit\" ");
        int toExit = 0;
        while (toExit != 1) {
            String inputParams = sc.nextLine().trim();
            if (inputParams.equals("exit")) {
                toExit = 1;
                System.out.println("Goodbye!");
            } else {
                System.out.println(calc(inputParams));
            }
        }
        sc.close();
    }

    public static String calc(String input) throws Exception {
        int a = -1000;
        int b = -1000;
        int result;
        char action = '?';
        char[] inputParams = input.toCharArray();
        int i = 0;
        while (i < inputParams.length) {
            if (isDigit(inputParams[i])) {
                if (inputParams[i] == '1') {
                    if (i + 1 == inputParams.length || inputParams[i + 1] == ' ' || isMathSign(inputParams[i + 1])) {
                        if (a == -1000) {
                            a = 1;
                        } else {
                            b = 1;
                        }
                        i++;
                    } else if (inputParams[i + 1] == '0') {
                        if (a == -1000) {
                            a = 10;
                        } else {
                            b = 10;
                        }
                        i += 2;
                    } else {
                        throw new Exception("You can't use numbers bigger than 10");
                    }
                } else {
                        if (a == -1000) {
                            a = (int) inputParams[i] - 48;
                        } else {
                            b = (int) inputParams[i] - 48;
                        }
                        i++;
                    }
            } else if (isMathSign(inputParams[i])) {
                if (i > 1 && i < 4 && inputParams[i + 1] == ' ') {
                    action = inputParams[i];
                    i++;
                } else {
                    throw new Exception("Math sign isn't where he should be!");
                }
            } else {
                throw new Exception("Wrong expression!");
            }
            i++;
        }
        switch (action) {
            case '+' -> result = a + b;
            case '-' -> result = a - b;
            case '*' -> result = a * b;
            case '/' -> result = a / b;
            default -> throw new Exception("Something went wrong");
        }
        return String.valueOf(result);
    }

    private static boolean isDigit(char ch) {
        return ch <= '9' && ch >= '0';
    }

    private static boolean isMathSign(char ch) {
        return ch == '+' || ch == '-' || ch == '/' || ch == '*';
    }

}
