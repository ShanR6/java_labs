import java.util.Arrays;


public class punct_8 {
    public static int[][] task(int[][] arr) {
        int n = arr.length;
        int[][] rotated = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[n - 1 - j][i] = arr[i][j];
            }
        }

        return rotated;
    }
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] result = task(arr);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}