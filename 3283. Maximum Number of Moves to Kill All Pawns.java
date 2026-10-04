import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int n = 50;

    private int[][] direction = new int[][] {
            { -1, -2 },
            { -2, -1 },
            { -1, 2 },
            { -2, 1 },
            { 1, -2 },
            { 2, -1 },
            { 1, 2 },
            { 2, 1 },
    };

    private int[][] fill(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();

        int[][] ans = new int[n][n];
        for (int k = 0; k < ans.length; k++) {
            Arrays.fill(ans[k], -1);
        }
        queue.add(new int[] { i, j });
        ans[i][j] = 0;
        int count = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            count++;
            while (size-- > 0) {
                int[] poll = queue.poll();
                for (int[] d : direction) {
                    int nextx = poll[0] + d[0];
                    int nexty = poll[1] + d[1];
                    if (nextx < 0 || nexty < 0 || nextx >= n || nexty >= n)
                        continue;
                    if (ans[nextx][nexty] != -1)
                        continue;
                    ans[nextx][nexty] = count;
                    queue.add(new int[] { nextx, nexty });
                }
            }
        }
        return ans;
    }

    private int helper(int index, int[][] position, int taken, int makeMax, int dp[][][], int dp2[][][]) {
        if (dp2[makeMax][index][taken] != -1)
            return dp2[makeMax][index][taken];

        int alltaken = (1 << position.length) - 1;
        if (alltaken == taken) {
            return dp2[makeMax][index][taken] = 0;
        }

        int[][] is = dp[index];

        int ans = makeMax == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 1; i < position.length; i++) {
            if (((1 << i) & taken) != 0)
                continue;
            int posAns = helper(i, position, taken | (1 << i), makeMax == 0 ? 1 : 0, dp,
                    dp2);
            if (makeMax == 1) {
                ans = Math.max(ans, posAns + is[position[i][0]][position[i][1]]);
            } else {
                ans = Math.min(ans, posAns + is[position[i][0]][position[i][1]]);
            }
        }
        return dp2[makeMax][index][taken] = ans;
    }

    public int maxMoves(int kx, int ky, int[][] positions) {
        // from postion[x, y] to which position and how many moves[x][y] -> z;
        int[][] newpositions = new int[positions.length + 1][2];
        newpositions[0] = new int[] { kx, ky };
        for (int i = 1; i < newpositions.length; i++) {
            newpositions[i] = positions[i - 1];
        }

        int dp[][][] = new int[newpositions.length][n][n];
        for (int i = 0; i < newpositions.length; i++) {
            dp[i] = fill(newpositions[i][0], newpositions[i][1]);
        }

        int dp2[][][] = new int[2][newpositions.length][(1 << newpositions.length)];

        for (int i = 0; i < newpositions.length; i++) {
            Arrays.fill(dp2[0][i], -1);
            Arrays.fill(dp2[1][i], -1);
        }

        return helper(0, newpositions, 1, 1, dp, dp2);
    }
}