package bullscows;

import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder secretCode = new StringBuilder();

        System.out.println("Please, enter the secret code's length:");
        String s = scanner.nextLine();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <'0' || s.charAt(i) > '9') {
                System.out.printf("Error: %s isn't a valid number.\n", s);
                return;
            }
        }
        int codeLength = Integer.parseInt(s);
        if (codeLength == 0) {
            System.out.printf("Error: %d isn't a valid number.\n", codeLength);
            return;
        }


        System.out.println("Input the number of possible symbols in the code:");
        int countOfSymbols = scanner.nextInt();
        if (countOfSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        if (codeLength > countOfSymbols) {
            System.out.printf("Error: it's not possible to generate a code " +
                    "with a length of %d with %d unique symbols.\n", codeLength, countOfSymbols);
            return;
        } else {
            Random random = new Random();

            for (int i = 0; i < codeLength; i++) {
                char symbol;
                int rnd = random.nextInt(countOfSymbols);

                if (rnd > 9) {
                    symbol = (char) (rnd - 10 + 'a');
                } else {
                    symbol = (char) (rnd + '0');
                }

                if (secretCode.toString().indexOf(symbol) != -1) {
                    System.out.println("hi");
                    i--;
                } else {
                    secretCode.append(symbol);
                }
            }
        }

        StringBuilder ranges = new StringBuilder("0-9");
        if (countOfSymbols > 10) {
            ranges.append(", a-").append((char) (countOfSymbols - 11 + 'a'));
        }

        System.out.printf("The secret is prepared: %s (%s).\n", "*".repeat(Math.max(0, codeLength)), ranges);
        System.out.println(secretCode);

        int move = 1;
        boolean youWinner = false;
        System.out.println("Okay, let's start a game!");

        while (!youWinner) {
            System.out.printf("Turn %d:\n", move);

            int cows = 0;
            int bulls = 0;

            String number = scanner.next();
            boolean isDifferent = false;
            for (int i = 1; i < number.length(); i++) {
                if (number.charAt(i) != number.charAt(0)) {
                    isDifferent = true;
                }
            }

            if(!isDifferent && secretCode.toString().indexOf(number.charAt(0)) != -1) {
                bulls = 1;
            }

            for (int i = 0; i < codeLength && isDifferent; i++) {
                if (number.charAt(i) == secretCode.toString().charAt(i)) {
                    bulls++;
                } else if (secretCode.toString().indexOf(number.charAt(i)) != -1) {
                    cows++;
                }
            }

            if (bulls != 0 && cows != 0) {
                System.out.printf("Grade: %s bull(s) and %s cow(s)\n", bulls, cows);
            } else if (bulls != 0) {
                System.out.printf("Grade: %s bull(s)\n", bulls);
            } else if (cows != 0) {
                System.out.printf("Grade: %s cow(s)\n", cows);
            } else {
                System.out.println("Grade: None");
            }

            if (bulls == codeLength) {
                System.out.println("Congratulations! You guessed the secret code.");
                youWinner = true;
            }
            move++;
        }
    }
}
