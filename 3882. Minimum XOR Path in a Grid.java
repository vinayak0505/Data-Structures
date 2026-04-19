import java.util.HashSet;
import java.util.Set;

class Solution {

    private Set<Integer> helper(int[][] grid, int i, int j, Set<Integer> dp[][]) {
        int n = grid.length;
        int m = grid[0].length;

        if (i == n || j == m) {
            return null;
        }

        if (i == n - 1 && j == m - 1) {
            return new HashSet<>() {
                {
                    add(grid[i][j]);
                }
            };
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }
        Set<Integer> right = helper(grid, i, j + 1, dp);
        Set<Integer> down = helper(grid, i + 1, j, dp);
        Set<Integer> result = new HashSet<>();
        if (right != null) {
            for (int x : right) {
                result.add(x ^ grid[i][j]);
            }
        }
        if (down != null) {
            for (int y : down) {
                result.add(y ^ grid[i][j]);
            }
        }

        dp[i][j] = result;
        return result;
    }

    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Set<Integer> dp[][] = new HashSet[n][m];
        Set<Integer> result = helper(grid, 0, 0, dp);
        int ans = Integer.MAX_VALUE;
        for (int x : result) {
            ans = Math.min(ans, x);
        }
        return ans;
    }
}