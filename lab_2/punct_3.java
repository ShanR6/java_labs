public class punct_3 {
    public static int task(int[] arr) {
        int maxSum = arr[0], curSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            curSum = Math.max(arr[i], curSum + arr[i]);
            maxSum = Math.max(maxSum, curSum);
        }

        return maxSum;
    }
    public static void main(String[] args) {
        int[] arr = {1, -1, 5, 6, 10, -10, 0};
        int result = task(arr);
        System.out.println(result);
    }
}