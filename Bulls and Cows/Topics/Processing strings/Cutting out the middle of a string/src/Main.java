import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String str = scanner.next();

        for (int i = 0; i < str.length(); i++) {
            if (i == str.length() / 2) {
                continue;
            }
            if (str.length() % 2 == 0 && i == str.length() / 2 - 1) {
                continue;
            }
            System.out.print(str.charAt(i));
            
        }  
        System.out.println();
    }
}
