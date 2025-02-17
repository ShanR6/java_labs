public class punct_6 {
    public static int task(int[][] arr) {
        int sum = 0;

        for (int[] row : arr) {
            for (int num : row) {
                sum += num;
            }
        }

        return sum;
    }
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2},
                {3, 4}
        };
        int result = task(arr);
        System.out.println(result);
    }
}