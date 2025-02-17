public class punct_7 {
    public static int[] task(int[][] arr) {
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int maxNum = arr[i][0];
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > maxNum) {
                    maxNum = arr[i][j];
                }
            }
            result[i] = maxNum;
        }

        return result;
    }
    public static void main(String[] args) {
        int[][] arr = {
                {1, 3, 2},
                {4, 6, 5},
                {9, 7, 8}
        };
        int[] result = task(arr);
        for (int num : result) {
            System.out.println(num);
        }
    }
}