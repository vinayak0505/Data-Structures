class Solution {
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = Integer.MAX_VALUE;
        for (int include = 0; include < 3; include++) {
            for (int exclude = 0; exclude < 3; exclude++) {
                if (include == exclude)
                    continue;
                int changes = 0;

                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid.length; j++) {
                        if (i < n / 2) {
                            if (i == j || i == m - j - 1) {
                                changes += grid[i][j] == include ? 0 : 1;
                            } else {
                                changes += grid[i][j] == exclude ? 0 : 1;
                            }
                        } else {
                            if (j == n / 2) {
                                changes += grid[i][j] == include ? 0 : 1;
                            } else {
                                changes += grid[i][j] == exclude ? 0 : 1;
                            }
                        }
                    }
                }
                ans = Math.min(ans, changes);
                changes = 0;
            }
        }
        return ans;
    }
}