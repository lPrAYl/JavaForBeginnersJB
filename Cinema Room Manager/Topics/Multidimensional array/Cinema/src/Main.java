import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] seats = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                seats[i][j] = scanner.nextInt();
            }
        }
        int k = scanner.nextInt();
        int row = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (seats[i][j] == 0) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == k) {
                    row = i + 1;
                    break;
                }
            }
            if (row > 0) {
                break;
            }
        }
        System.out.println(row);
    }
}
