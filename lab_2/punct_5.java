import java.util.Arrays;


public class punct_5 {
    public static int[] task(int[] arr, int target) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i < j && arr[i] + arr[j] == target) {
                    return new int[]{arr[i], arr[j]};
                }
            }
        }
        return null;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 8;
        int[] result = task(arr, target);
        if (result == null) {
            System.out.println("null");
        } else {
            System.out.println(Arrays.toString(result));
        }
    }
}