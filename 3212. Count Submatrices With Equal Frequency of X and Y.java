class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] count = new int[n][m];
        boolean[][] atLeast1X = new boolean[n][m];
        int[][] iCount = new int[n][m];
        int[][] jCount = new int[n][m];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = 0;
                if (grid[i][j] == 'X') {
                    atLeast1X[i][j] = true;
                    value++;
                } else if (grid[i][j] == 'Y') {
                    value--;
                }
                if (i != 0) {
                    atLeast1X[i][j] |= atLeast1X[i - 1][j];
                }
                if (j != 0) {
                    atLeast1X[i][j] |= atLeast1X[i][j - 1];
                }
                iCount[i][j] = value + (i == 0 ? 0 : iCount[i - 1][j]);
                jCount[i][j] = value + (j == 0 ? 0 : jCount[i][j - 1]);
                count[i][j] = value + ((i == 0 || j == 0) ? 0 : count[i - 1][j - 1]) +
                        (i == 0 ? 0 : iCount[i - 1][j]) + (j == 0 ? 0 : jCount[i][j - 1]);
                if (count[i][j] == 0 && atLeast1X[i][j]) {
                    ans++;
                }
            }
        }
        return ans;
    }
}