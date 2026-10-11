import java.util.Arrays;

class Solution {
    public long helper(int i, int[] nums, int positive, long[][] dp) {
        int n = nums.length;
        if (i == n) {
            return 0;
        }
        if (dp[positive][i] != -1) {
            return dp[positive][i];
        }
        if (positive == 1) {
            return dp[positive][i] = helper(i + 1, nums, 0, dp) + nums[i];
        }
        long same = helper(i + 1, nums, 1, dp) - nums[i];
        long brea = helper(i + 1, nums, 0, dp) + nums[i];
        return dp[positive][i] = Math.max(same, brea);
    }

    public long maximumTotalCost(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[2][n];
        Arrays.fill(dp[0], -1);
        Arrays.fill(dp[1], -1);
        return helper(0, nums, 1, dp);
    }
}