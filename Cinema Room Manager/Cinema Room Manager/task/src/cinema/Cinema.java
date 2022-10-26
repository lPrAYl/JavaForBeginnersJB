package cinema;

import java.util.Scanner;

public class Cinema {
    static boolean seatIsBusy(char[][] array, int row, int seat) {
        if (row > array.length || row < 1 || seat > array[0].length || seat < 1) {
            System.out.println("Wrong input!");
            return true;
        } else if (array[row - 1][seat - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            return true;
        }
        return false;
    }
    static void printCinema (char[][] array) {
        System.out.println("Cinema:");
        int rows = array.length;
        int seats = array[0].length;
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print(" ");
                } else if (i == 0) {
                    System.out.print(" " + j);
                } else if (j == 0) {
                    System.out.print(i);
                } else {
                    System.out.print(" " + array[i - 1][j - 1]);
                }
            }
            System.out.println();
        }
    }
    static int printPrice(char[][] array, Scanner scanner) {

        int row;
        int seat;

        do {
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seat = scanner.nextInt();
        } while (seatIsBusy(array, row, seat));
        array[row - 1][seat - 1] = 'B';

        int price;
        int rows = array.length;
        int seats = array[0].length;

        if (rows * seats > 60 && row > rows / 2) {
            price = 8;
        } else {
            price = 10;
        }
        System.out.printf("Ticket price: $%d\n", price);
        return price;
    }

    static void printStatistics(int purchasedTickets, int countTickets, int currentIncome, int totalIncome) {
        System.out.printf("Number of purchased tickets: %d\n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", purchasedTickets * 100.0 / countTickets);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        char[][] cinema = new char[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S';
            }
        }

        int currentIncome = 0;
        int totalIncome;
        int purchasedTickets = 0;
        int countTickets;

        countTickets = rows * seats;
        if (countTickets > 60) {
            totalIncome = (rows / 2 * 10 + (rows - rows /2) * 8) * seats;
        } else {
            totalIncome = rows * seats * 10;
        }

        int choice;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    printCinema(cinema);
                    break;
                case 2:
                    currentIncome += printPrice(cinema, scanner);
                    purchasedTickets += 1;
                    break;
                case 3:
                    printStatistics(purchasedTickets, countTickets, currentIncome, totalIncome);
                    break;
            }
        } while (choice != 0);
    }
}