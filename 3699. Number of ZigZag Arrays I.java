import java.util.Arrays;

class Solution {
    private int[][][] memo;
    private int mod = (int) 1e9 + 7;

    private int helper(int n, int l, int r, boolean greater, int prevNum) {
        if (n == 0) {
            return 1;
        }

        if (greater && prevNum >= r)
            return 0;
        if (greater == false && prevNum <= l)
            return 0;
        if (memo[n][greater ? 1 : 0][prevNum] != -1)
            return memo[n][greater ? 1 : 0][prevNum];
        int ans = 0;
        if (greater) {
            // Current choice (prevNum + 1) + Next choice's accumulated sum (prevNum + 1)
            ans = (helper(n - 1, l, r, false, prevNum + 1) + helper(n, l, r, true, prevNum + 1)) % mod;
        } else {
            // Current choice (prevNum - 1) + Next choice's accumulated sum (prevNum - 1)
            ans = (helper(n - 1, l, r, true, prevNum - 1) + helper(n, l, r, false, prevNum - 1)) % mod;
        }

        return memo[n][greater ? 1 : 0][prevNum] = ans;
    }

    public int zigZagArrays(int n, int l, int r) {
        memo = new int[n + 1][2][r + 2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return (helper(n, l, r, true, l - 1) + helper(n, l, r, false, r + 1)) % mod;
    }
}