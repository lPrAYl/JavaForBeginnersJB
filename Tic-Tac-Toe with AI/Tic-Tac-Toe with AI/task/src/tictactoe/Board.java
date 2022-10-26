//package tictactoe;
//
//public class Board {
//
//    int xCount;
//    int oCount;
//    private char[] board = new char[9];
//
//    Board() {
//        this.oCount = 0;
//        this.xCount = 0;
//
//        for (int i = 0; i < 9; i++) {
//            this.board[i] = ' ';
//        }
//    }
//
//    public char getCell(int index) {
//        return board[index];
//    }
//
//    public void setCell(int index, char ch) {
//        board[index] = ch;
//        if (ch == 'X') {
//            xCount++;
//        } else {
//            oCount++;
//        }
//    }
//
//    public char[] getBoard() {
//      return board.clone();
//    }
//
//
//    public void printBoard() {
//        for (int i = 0; i < 9; i++) {
//            System.out.print('-');
//        }
//        System.out.println();
//        for (int i = 0; i < 9; i++) {
//            if (i % 3 == 0) {
//                System.out.print("| ");
//            }
//            System.out.print(board[i] + " ");
//            if (i % 3 == 2) {
//                System.out.println("|");
//            }
//        }
//        for (int i = 0; i < 9; i++) {
//            System.out.print('-');
//        }
//        System.out.println();
//    }
//
//    public static boolean winnerFound(char[] board, char player) {
//
//        if ((board[0] == player && board[1] == player && board[2] == player) ||
//            (board[3] == player && board[4] == player && board[5] == player) ||
//            (board[6] == player && board[7] == player && board[8] == player) ||
//            (board[0] == player && board[3] == player && board[6] == player) ||
//            (board[1] == player && board[4] == player && board[7] == player) ||
//            (board[2] == player && board[5] == player && board[8] == player) ||
//            (board[0] == player && board[4] == player && board[8] == player) ||
//            (board[2] == player && board[4] == player && board[6] == player)) {
//            return true;
//        }
//        return false;
//    }
//}
