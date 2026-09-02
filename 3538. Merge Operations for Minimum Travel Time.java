class Solution {

    int max = Integer.MAX_VALUE / 2;

    Integer dp[][][][];

    private int helper(int i, int n, int k, int[] position, int[] time, int curTime, int prevTime) {
        if (i == n) {
            if (k == 0) {
                return 0;
            }
            return max;
        }
        if(dp[i][k][curTime][prevTime] != null){
            return dp[i][k][curTime][prevTime];
        }
        int ans = helper(i + 1, n, k, position, time, i == n - 1 ? 0: time[i + 1], curTime)
                + ((position[i] - position[i - 1]) * prevTime);

        if (k > 0 && i != 0 && i != n - 1) {
            int value = helper(i + 1, n, k - 1, position, time, time[i + 1] + curTime, prevTime)
                    + (position[i] - position[i - 1]) * prevTime;
            ans = Math.min(value, ans);
        }
        return dp[i][k][curTime][prevTime] = ans;
    }

    public int minTravelTime(int l, int n, int k, int[] position, int[] time) {
        dp = new Integer[n][k + 1][101][101];
        return helper(1, n, k, position, time, time[1], time[0]);
    }
}