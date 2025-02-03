import java.util.Scanner;

public class punct_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        if (n <= 0) {
            return;
        }
        int count = n;
        n = scanner.nextInt();
        int result = 0;
        boolean flag = true;
        while (count != 0) {
            if (flag) {
                result += n;
                flag = false;
            } else {
                result -= n;
                flag = true;
            }
            count--;
            if (count != 0) {
                n = scanner.nextInt();
            }
        }

        System.out.println(result);
    }
}