package tictactoe;

import java.util.Scanner;

public class Main {

    public static void printArray(char[][] array) {
        for (int i = 0; i < 9; i++) {
            System.out.print('-');
        }
        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("|");
        }

        for (int i = 0; i < 9; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    public static void changeArray(Scanner scanner, char[][] array, int move) {

        try {
            int x = Integer.parseInt(scanner.next());
            int y = Integer.parseInt(scanner.next());

            if (x > 3 || x < 1 || y > 3 || y < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                changeArray(scanner, array, move);
            } else if (array[x - 1][y - 1] != ' ') {
                System.out.println("The cell is occupied! choose another one!");
                changeArray(scanner, array, move);
            } else {
                array[x - 1][y - 1] = move % 2 == 0 ? 'X' : 'O';
            }
        } catch (NumberFormatException e) {
            System.err.println("You should enter numbers!");
            changeArray(scanner, array, move);
        }
    }

    public static int checkResult(char[][] array) {
        int result = -1;
        for (int i = 0; i < 3 && result == -1; i++) {
            if (array[i][0] == array[i][1] && array[i][0] == array[i][2]) {
                if (array[i][0] == 'X') {
                    result = 1;
                } else if (array[i][0] == 'O') {
                    result = 0;
                }
            }
        }

        for (int j = 0; j < 3 && result == -1; j++) {
            if (array[0][j] == array[1][j] && array[0][j] == array[2][j]) {
                if (array[0][j] == 'X') {
                    result = 1;
                } else if (array[0][j] == 'O') {
                    result = 0;
                }
            }
        }

        if ((array[0][0] == array[1][1] && array[0][0] == array[2][2]) ||
                (array[0][2] == array[1][1] && array[0][2] == array[2][0])) {
            if (array[1][1] == 'X') {
                result = 1;
            } else if (array[1][1] == 'O') {
                result = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        char[][] array = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = ' ';
            }
        }
        printArray(array);
        int result = -1;
        for (int move = 0; move < 9 && result == -1; move++) {
            changeArray(scanner, array, move);
            if (move > 3) {
                result = checkResult(array);
            }
            printArray(array);
        }


        switch (result) {
            case 0 -> System.out.println("O wins");
            case 1 -> System.out.println("X wins");
            default -> System.out.println("Draw");
        }
    }
}
