import java.util.Scanner;

public class punct_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        if (n < 100 || n > 999) {
            return;
        }
        int num = n;
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }

        if (num % 2 != 0 || sum % 2 != 0) {
            System.out.println("не дважды четное");
        } else {
            System.out.println("дважды четное");
        }
    }
}