class Solution {

    private int[][] direction = new int[][] {
            { 1, 1 },
            { 1, -1 },
            { -1, -1 },
            { -1, 1 }
    };

    private boolean canGoNext(int i, int j, int[] dir, int n, int m) {
        int nextI = i + dir[0];
        int nextJ = j + dir[1];
        return nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < m;
    }

    public int lenOfVDiagonal(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][][] dpFrom1 = new int[4][n][m];
        int[][][] dpMaxPattern = new int[4][n][m];
        boolean has1 = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    has1 = true;
                    dpFrom1[0][i][j] = 1;
                    dpFrom1[1][i][j] = 1;
                } else if (grid[i][j] == 2) {
                    dpMaxPattern[0][i][j] = 1;
                    dpMaxPattern[1][i][j] = 1;
                    if (canGoNext(i, j, direction[2], n, m)) {
                        if (grid[i + direction[2][0]][j + direction[2][1]] == 0) {
                            dpMaxPattern[0][i][j] = dpMaxPattern[0][i + direction[2][0]][j + direction[2][1]] + 1;
                            if (dpFrom1[0][i + direction[2][0]][j + direction[2][1]] > 0) {
                                dpFrom1[0][i][j] = dpFrom1[0][i + direction[2][0]][j + direction[2][1]] + 1;
                            }
                        } else if (grid[i + direction[2][0]][j + direction[2][1]] == 1) {
                            dpFrom1[0][i][j] = dpFrom1[0][i + direction[2][0]][j + direction[2][1]] + 1;
                        }

                    }
                    if (canGoNext(i, j, direction[3], n, m)) {
                        if (grid[i + direction[3][0]][j + direction[3][1]] == 0) {
                            dpMaxPattern[1][i][j] = dpMaxPattern[1][i + direction[3][0]][j + direction[3][1]] + 1;
                            if (dpFrom1[1][i + direction[3][0]][j + direction[3][1]] > 0) {
                                dpFrom1[1][i][j] = dpFrom1[1][i + direction[3][0]][j + direction[3][1]] + 1;
                            }
                        } else if (grid[i + direction[3][0]][j + direction[3][1]] == 1) {
                            dpFrom1[1][i][j] = dpFrom1[1][i + direction[3][0]][j + direction[3][1]] + 1;
                        }

                    }
                } else if (grid[i][j] == 0) {
                    dpMaxPattern[0][i][j] = 1;
                    dpMaxPattern[1][i][j] = 1;
                    if (canGoNext(i, j, direction[2], n, m) && grid[i + direction[2][0]][j + direction[2][1]] == 2) {
                        if (dpFrom1[0][i + direction[2][0]][j + direction[2][1]] > 0) {
                            dpFrom1[0][i][j] = dpFrom1[0][i + direction[2][0]][j + direction[2][1]] + 1;
                        }
                        dpMaxPattern[0][i][j] = dpMaxPattern[0][i + direction[2][0]][j + direction[2][1]] + 1;
                    }
                    if (canGoNext(i, j, direction[3], n, m) && grid[i + direction[3][0]][j + direction[3][1]] == 2) {
                        if (dpFrom1[1][i + direction[3][0]][j + direction[3][1]] > 0) {
                            dpFrom1[1][i][j] = dpFrom1[1][i + direction[3][0]][j + direction[3][1]] + 1;
                        }
                        dpMaxPattern[1][i][j] = dpMaxPattern[1][i + direction[3][0]][j + direction[3][1]] + 1;
                    }
                }

            }
        }

        if (has1 == false) {
            return 0;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dpFrom1[2][i][j] = 1;
                    dpFrom1[3][i][j] = 1;
                } else if (grid[i][j] == 2) {
                    dpMaxPattern[2][i][j] = 1;
                    dpMaxPattern[3][i][j] = 1;
                    if (canGoNext(i, j, direction[0], n, m)) {
                        if (grid[i + direction[0][0]][j + direction[0][1]] == 0) {
                            dpMaxPattern[2][i][j] = dpMaxPattern[2][i + direction[0][0]][j + direction[0][1]] + 1;
                            if (dpFrom1[2][i + direction[0][0]][j + direction[0][1]] > 0) {
                                dpFrom1[2][i][j] = dpFrom1[2][i + direction[0][0]][j + direction[0][1]] + 1;
                            }
                        } else if (grid[i + direction[0][0]][j + direction[0][1]] == 1) {
                            dpFrom1[2][i][j] = dpFrom1[2][i + direction[0][0]][j + direction[0][1]] + 1;
                        }

                    }
                    if (canGoNext(i, j, direction[1], n, m)) {
                        if (grid[i + direction[1][0]][j + direction[1][1]] == 0) {
                            dpMaxPattern[3][i][j] = dpMaxPattern[3][i + direction[1][0]][j + direction[1][1]] + 1;
                            if (dpFrom1[3][i + direction[1][0]][j + direction[1][1]] > 0) {
                                dpFrom1[3][i][j] = dpFrom1[3][i + direction[1][0]][j + direction[1][1]] + 1;
                            }
                        } else if (grid[i + direction[1][0]][j + direction[1][1]] == 1) {
                            dpFrom1[3][i][j] = dpFrom1[3][i + direction[1][0]][j + direction[1][1]] + 1;
                        }

                    }
                } else if (grid[i][j] == 0) {
                    dpMaxPattern[2][i][j] = 1;
                    dpMaxPattern[3][i][j] = 1;
                    if (canGoNext(i, j, direction[0], n, m) && grid[i + direction[0][0]][j + direction[0][1]] == 2) {
                        if (dpFrom1[2][i + direction[0][0]][j + direction[0][1]] > 0) {
                            dpFrom1[2][i][j] = dpFrom1[2][i + direction[0][0]][j + direction[0][1]] + 1;
                        }
                        dpMaxPattern[2][i][j] = dpMaxPattern[2][i + direction[0][0]][j + direction[0][1]] + 1;
                    }
                    if (canGoNext(i, j, direction[1], n, m) && grid[i + direction[1][0]][j + direction[1][1]] == 2) {
                        if (dpFrom1[3][i + direction[1][0]][j + direction[1][1]] > 0) {
                            dpFrom1[3][i][j] = dpFrom1[3][i + direction[1][0]][j + direction[1][1]] + 1;
                        }
                        dpMaxPattern[3][i][j] = dpMaxPattern[3][i + direction[1][0]][j + direction[1][1]] + 1;
                    }
                }
            }
        }

        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((grid[i][j] != 0 && grid[i][j] != 2))
                    continue;
                for (int d = 0; d < 4; d++) {
                    if (dpFrom1[d][i][j] > 0) {
                        maxLen = Math.max(maxLen, dpFrom1[d][i][j] + dpMaxPattern[(d + 3) % 4][i][j] - 1);
                    }
                }

            }
        }
        return maxLen;
    }
}