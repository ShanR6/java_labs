import java.util.Scanner;

public class punct_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.nextLine();

        int cur_x = 0;
        int cur_y = 0;

        int step = 0;

        while (true) {
            String direct = scanner.nextLine();

            if (direct.equals("стоп")) {
                break;
            }

            int dist = scanner.nextInt();
            scanner.nextLine();

            switch(direct) {
                case "восток":
                    cur_x += dist;
                    break;
                case "запад":
                    cur_x -= dist;
                    break;
                case "север":
                    cur_y += dist;
                    break;
                case "юг":
                    cur_y -= dist;
                    break;
            }

            if (cur_x != x || cur_y != y) {
                step++;
            }
        }
        System.out.println(step);
    }
}