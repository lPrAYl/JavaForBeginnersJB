import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int min = k;
        int seed = a;
        for (int i = a; i < b; i++) {
            int max = 0;
            Random random = new Random(i);
            for (int j = 0; j < n; j++) {
                int next = random.nextInt(k);
                if (next > max) {
                    max = next;
                }
            }
            if (max < min) {
                min = max;
                seed = i;
            }
        }
        System.out.println(seed);
        System.out.println(min);
    }
}
