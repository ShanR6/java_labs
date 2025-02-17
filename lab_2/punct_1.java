import java.util.Arrays;

public class punct_1 {
    public static String task(String str) {
        if (str.isEmpty()) {
            return "";
        }

        int[] lastIndex = new int[128];
        Arrays.fill(lastIndex, -1);

        int maxLen = 0;
        int curStart = 0;
        int maxLenStart = 0;

        for (int end = 0; end < str.length(); end++) {
            char curChar = str.charAt(end);

            if (lastIndex[curChar] >= curStart) {
                curStart = lastIndex[curChar] + 1;
            }

            lastIndex[curChar] = end;

            if (end - curStart + 1 > maxLen) {
                maxLen = end - curStart + 1;
                maxLenStart = curStart;
            }
        }

        return str.substring(maxLenStart, maxLenStart + maxLen);
    }
    public static void main(String[] args) {
        String input = "abcabca";
        String result = task(input);
        System.out.println(result);
    }
}