import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] incomes = new int[n];
        int[] taxes = new int[n];
        
        for (int i = 0; i < n; i++) {
            incomes[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            taxes[i] = scanner.nextInt();
        }

        double max = 0;
        int number = 0;
        for (int i = 0; i < n; i++) {
            double pays = incomes[i] * taxes[i] / 100.0;
            if (pays > max) {
                max = pays;
                number = i + 1;
            } 
        }
        System.out.println(number);
    }
}
