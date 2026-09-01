import java.util.HashMap;

class Solution {

    private boolean rightToLeft(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        HashMap<Long, Integer> remainingSide = new HashMap<>();
        HashMap<Long, Integer> leftSide = new HashMap<>();

        long remainingSum = 0;
        long upSum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long key = grid[i][j];
                remainingSide.put(key, remainingSide.getOrDefault(key, 0) + 1);
                remainingSum += key;
            }
        }

        for (int j = 0; j < m - 1; j++) {
            for (int i = 0; i < n; i++) {
                long key = grid[i][j];
                leftSide.put(key, leftSide.getOrDefault(key, 0) + 1);
                remainingSide.put(key, remainingSide.get(key) - 1);
                upSum += key;
                remainingSum -= key;
            }

            if (upSum == remainingSum)
                return true;

            long diff = Math.abs(upSum - remainingSum);
            if (upSum > remainingSum) {
                if (n == 1) {
                    if (grid[0][0] == diff || grid[0][j] == diff)
                        return true;
                } else if (j == 0) {
                    if (grid[0][0] == diff || grid[n - 1][0] == diff)
                        return true;
                } else {
                    if (leftSide.getOrDefault(diff, 0) > 0)
                        return true;
                }
            } else {
                if (n == 1) {
                    if (grid[0][j + 1] == diff || grid[0][m - 1] == diff)
                        return true;
                } else if (j == m - 2) {
                    if (grid[0][m - 1] == diff || grid[n - 1][m - 1] == diff)
                        return true;

                } else {
                    if (remainingSide.getOrDefault(diff, 0) > 0)
                        return true;
                }
            }
        }

        return false;
    }

    public boolean canPartitionGrid(int[][] grid) {
        if (rightToLeft(grid))
            return true;
        return upToDown(grid);
    }

    private boolean upToDown(int[][] grid) {
        int n = grid.length;
        if (n == 1)
            return false;
        int m = grid[0].length;
        HashMap<Long, Integer> remainingSide = new HashMap<>();
        HashMap<Long, Integer> upSide = new HashMap<>();

        long remainingSum = 0;
        long upSum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long key = grid[i][j];
                remainingSide.put(key, remainingSide.getOrDefault(key, 0) + 1);
                remainingSum += key;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                long key = grid[i][j];
                upSide.put(key, upSide.getOrDefault(key, 0) + 1);
                remainingSide.put(key, remainingSide.get(key) - 1);
                upSum += key;
                remainingSum -= key;
            }

            if (upSum == remainingSum)
                return true;

            long diff = Math.abs(upSum - remainingSum);
            if (upSum > remainingSum) {
                if (m == 1) {
                    if (grid[0][0] == diff || grid[i][0] == diff)
                        return true;
                } else if (i == 0) {
                    if (grid[0][0] == diff || grid[0][m - 1] == diff)
                        return true;

                } else {
                    if (upSide.getOrDefault(diff, 0) > 0)
                        return true;
                }
            } else {
                if (m == 1) {
                    if (grid[i + 1][0] == diff || grid[n - 1][0] == diff)
                        return true;
                } else if (i == n - 2) {
                    if (grid[n - 1][0] == diff || grid[n - 1][m - 1] == diff)
                        return true;

                } else {
                    if (remainingSide.getOrDefault(diff, 0) > 0)
                        return true;
                }
            }
        }

        return false;
    }
}