import java.util.Arrays;

class Solution {
    String s;

    private boolean helper(int i, int rem, boolean[][] dp, StringBuilder sb, int array[], int k, int n) {
        int size = dp.length;
        if (i == size) {
            if (rem != 0)
                return false;
            s = sb.toString();
            if (n % 2 != 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.reverse();
            s += sb.toString();
            return true;
        }
        if (dp[i][rem] == false)
            return false;
        boolean ans = false;
        for (int j = 9; j >= 0; j--) {
            sb.append((char) ('0' + j));
            int contribution = (j * array[i] + rem);
            if (i != n - 1 - i) {
                contribution += j * array[n - i - 1];
            }
            ans = helper(i + 1, contribution % k, dp, sb, array, k, n);
            if (ans)
                return true;
            sb.deleteCharAt(sb.length() - 1);

        }
        return dp[i][rem] = false;

    }

    public String largestPalindrome(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int array[] = new int[n];
        array[n - 1] = 1 % k;
        for (int i = n - 2; i >= 0; i--) {
            array[i] = (array[i + 1] * 10) % k;
        }
        int size = (n + 1) / 2;
        boolean dp[][] = new boolean[size][10];
        for (int i = 0; i < size; i++) {
            Arrays.fill(dp[i], true);
        }
        helper(0, 0, dp, sb, array, k, n);
        return s;

    }
}