import java.util.Arrays;

class Solution {

    int dp[][][];

    public int helper(int[][] grid, int k, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;
        if (n == i || m == j)
            return -1;

        k -= (grid[i][j] == 2 ? 1 : grid[i][j]);
        if (k < 0)
            return -1;
        if (dp[k][i][j] != -2)
            return dp[k][i][j];

        if (i == n - 1 && j == m - 1) {
            System.out.println(k);
            return dp[k][i][j] = grid[i][j];
        }

        int right = helper(grid, k, i + 1, j);
        int bottom = helper(grid, k, i, j + 1);
        if (right == -1 && bottom == -1)
            return dp[k][i][j] = -1;
        return dp[k][i][j] = Math.max(right, bottom) + grid[i][j];

    }

    public int maxPathScore(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        dp = new int[k + 1][n][m];

        for (int[][] layers : dp) {
            for (int[] rows : layers) {
                Arrays.fill(rows, -2);
            }
        }
        return helper(grid, k, 0, 0);
    }
}