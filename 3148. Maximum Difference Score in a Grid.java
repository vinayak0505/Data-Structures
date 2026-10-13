import java.util.List;

class Solution {
    public int maxScore(List<List<Integer>> grid) {
        int n = grid.size();
        int m = grid.get(0).size();
        int[][] dp = new int[n][m];
        int prev = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            prev = Math.max(prev, grid.get(i).get(m - 1));
            dp[i][m - 1] = prev;
        }
        prev = Integer.MIN_VALUE;
        for (int i = m - 1; i >= 0; i--) {
            prev = Math.max(prev, grid.get(n - 1).get(i));
            dp[n - 1][i] = prev;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                dp[i][j] = Math.max(grid.get(i).get(j), Math.max(dp[i + 1][j], dp[i][j + 1]));
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == n - 1 && j == m - 1) {
                    continue;
                }
                if (i == n - 1) {
                    ans = Math.max(ans, dp[i][j + 1] - grid.get(i).get(j));
                    continue;
                }
                if (j == m - 1) {
                    ans = Math.max(ans, dp[i + 1][j] - grid.get(i).get(j));
                    continue;
                }
                ans = Math.max(ans, Math.max(dp[i + 1][j], dp[i][j + 1]) - grid.get(i).get(j));
            }
        }
        return ans;
    }
}