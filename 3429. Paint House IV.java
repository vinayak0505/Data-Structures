import java.util.Arrays;

class Solution {
    public long minCost(int n, int[][] cost) {
        int till = n / 2;
        long dp[][][] = new long[till][3][3];

        for (int i = 0; i < till; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(dp[i][j], Long.MAX_VALUE);
            }
        }

        for (int i = 0; i < till; i++) {
            for (int curColor = 0; curColor < 3; curColor++) {
                for (int adjacentColor = 0; adjacentColor < 3; adjacentColor++) {
                    if (curColor == adjacentColor)
                        continue;
                    for (int prevColor = 0; prevColor < 3; prevColor++) {
                        if (prevColor == curColor)
                            continue;
                        if(i == 0){
                            dp[0][curColor][adjacentColor] = cost[0][curColor] + cost[n - 1][adjacentColor];
                            continue;
                        }
                        for (int adjacentNextColor = 0; adjacentNextColor < 3; adjacentNextColor++) {
                            if (prevColor == adjacentNextColor)
                                continue;
                            if (adjacentColor == adjacentNextColor)
                                continue;
                            if (dp[i - 1][prevColor][adjacentNextColor] == Long.MAX_VALUE)
                                continue;
                            dp[i][curColor][adjacentColor] = Math.min(dp[i][curColor][adjacentColor],
                                    dp[i - 1][prevColor][adjacentNextColor] + cost[i][curColor]
                                            + cost[n - 1 - i][adjacentColor]);
                        }
                    }
                }
            }
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j)
                    continue;
                ans = Math.min(ans, dp[till - 1][i][j]);
            }
        }
        return ans;

    }
}