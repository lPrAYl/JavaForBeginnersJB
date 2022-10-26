import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] arr = new int[size];

        for(int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        int sum = 0;
        int n = scanner.nextInt();

        for(int i = 0; i < size; i++) {
            if (arr[i] > n) {
                sum += arr[i];
            }
        }

        System.out.println(sum);
    }
}