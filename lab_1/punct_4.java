import java.util.Scanner;

public class punct_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int roadCount = scanner.nextInt();

        int bestRoad = 0;
        int maxHeight = 0;

        for (int i = 1; i <= roadCount; i++) {
            int numTunnel = scanner.nextInt();
            int minHeightRoad = Integer.MAX_VALUE;

            for (int j = 0; j < numTunnel; j++) {
                int tunnelHeight = scanner.nextInt();
                minHeightRoad = Math.min(minHeightRoad, tunnelHeight);
            }

            if (minHeightRoad > maxHeight) {
                maxHeight = minHeightRoad;
                bestRoad = i;
            }
        }

        System.out.println(bestRoad + " " + maxHeight);
    }
}