class Solution {
    public int[] maximumSubarrayXor(int[] nums, int[][] queries) {
        int n = nums.length;
        int dp[][] = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = nums[i];
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i + 1][j] ^ dp[i][j - 1];
            }
        }

        for (int i = 0; i < n; i++) {
            int prev = dp[i][0];
            for (int j = 0; j < n; j++) {
                prev = dp[i][j] = Math.max(prev, dp[i][j]);
            }
        }

        for (int j = 0; j < n; j++) {
            int prev = dp[n - 1][j];
            for (int i = n - 1; i >= 0; i--) {
                prev = dp[i][j] = Math.max(prev, dp[i][j]);
            }
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = dp[queries[i][0]][queries[i][1]];
        }
        return ans;
    }
}