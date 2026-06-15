import java.util.Arrays;

class Solution {

    private long invalidNum = Long.MIN_VALUE;
    private long notReached = Long.MAX_VALUE;

    private long dp[][][];

    private long helper(int[] nums, int k, int state, int i) {
        if(k < 0) return invalidNum;
        int n = nums.length;
        if (i == n) {
            if (state != 1)
                return invalidNum;
            return 0;
        }

        if(dp[k][i][state] != notReached) return dp[k][i][state];

        long ans = helper(nums, k, state, i + 1);

        if (state == 1 || state == 2) {
            long temp_ans = helper(nums, state == 1 ? k - 1 : k, state == 1 ? 0 : 1, i + 1);
            if (temp_ans != invalidNum)
                ans = Math.max(ans, temp_ans + nums[i]);
        }
        
        if (state == 1 || state == 0) {
            long temp_ans = helper(nums, state == 1 ? k - 1 : k, state == 1 ? 2 : 1, i + 1);
            if (temp_ans != invalidNum)
                ans = Math.max(ans, temp_ans - nums[i]);

        }
        return dp[k][i][state] = ans;
    }

    public long maximumScore(int[] nums, int k) {
        int n = nums.length;
        int minIdx = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
        }

        int[] startMin = new int[n];
        for (int i = 0; i < n; i++) {
            startMin[i] = nums[(minIdx + i) % n];
        }

        int[] endMin = new int[n];
        for (int i = 0; i < n; i++) {
            endMin[i] = nums[(minIdx + 1 + i) % n];
        }

        dp = new long[n + 1][n + 1][3];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], notReached);
            }
        }
        long a = helper(startMin, k, 1, 0);

        dp = new long[n + 1][n + 1][3];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], notReached);
            }
        }
        long b = helper(endMin, k, 1, 0);
        return Math.max(a, b);
    }

}
