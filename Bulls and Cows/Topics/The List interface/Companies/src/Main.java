import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        String[] listOfString = scanner.nextLine().split(" ");
        for (String string: listOfString) {
            list.add(string);
        }

        System.out.println(list);
    }
}
