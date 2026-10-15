import java.util.Arrays;

class Solution {

    private int helper(int i, int prev, int totalSum[][], int dp[][]) {
        int m = totalSum.length;
        if (i == m)
            return 0;
        if (prev != -1 && dp[i][prev] != -1) {
            return dp[i][prev];
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < 10; j++) {
            if (j == prev)
                continue;
            ans = Math.min(ans, helper(i + 1, j, totalSum, dp) + totalSum[i][10] - totalSum[i][j]);
        }
        if (prev != -1) {
            return dp[i][prev] = ans;
        }
        return ans;
    }

    public int minimumOperations(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // 10th item be total sum
        int[][] totalSum = new int[m][11];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                totalSum[j][grid[i][j]]++;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 10; j++) {
                totalSum[i][10] += totalSum[i][j];
            }
        }

        int[][] dp = new int[m][10];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        return helper(0, -1, totalSum, dp);
    }
}