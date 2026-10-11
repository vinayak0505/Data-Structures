class Solution {

    private int minValue(int[][] grid, int istart, int iend, int jstart, int jend) {
        int finalIstart = Integer.MAX_VALUE;
        int finalJstart = Integer.MAX_VALUE;
        int finalIend = 0;
        int finalJend = 0;
        for (int i = istart; i < iend; i++) {
            for (int j = jstart; j < jend; j++) {
                if (grid[i][j] == 1) {
                    finalIend = Math.max(finalIend, i + 1);
                    finalJend = Math.max(finalJend, j + 1);
                    finalIstart = Math.min(finalIstart, i);
                    finalJstart = Math.min(finalJstart, j);
                }
            }
        }

        if (finalIstart == Integer.MAX_VALUE) {
            return 1;
        }

        return (finalIend - finalIstart) * (finalJend - finalJstart);

    }

    public int minimumSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int ans = n * m;

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans = Math.min(ans, minValue(grid, 0, i, 0, m)
                        + minValue(grid, i, j, 0, m)
                        + minValue(grid, j, n, 0, m));
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                ans = Math.min(ans, minValue(grid, 0, n, 0, i)
                        + minValue(grid, 0, n, i, j)
                        + minValue(grid, 0, n, j, m));
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                ans = Math.min(ans, minValue(grid, 0, i, 0, m)
                        + minValue(grid, i, n, 0, j)
                        + minValue(grid, i, n, j, m));

                ans = Math.min(ans, minValue(grid, 0, i, 0, j)
                        + minValue(grid, i, n, 0, j)
                        + minValue(grid, 0, n, j, m));

                ans = Math.min(ans, minValue(grid, 0, n, 0, j)
                        + minValue(grid, 0, i, j, m)
                        + minValue(grid, i, n, j, m));

                ans = Math.min(ans, minValue(grid, 0, i, 0, j)
                        + minValue(grid, 0, i, j, m)
                        + minValue(grid, i, n, 0, m));

            }
        }

        return ans;
    }
}