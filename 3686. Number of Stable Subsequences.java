import java.util.Arrays;

class Solution {
    private int mod = (int) 1e9 + 7;
    private int[][] dp;
    private int max_state = 5;

    private int helper(int[] nums, int oddCount, int i) {
        int n = nums.length;
        if (i == n)
            return 1;
        if (dp[oddCount + 2][i] != -1)
            return dp[oddCount + 2][i];
        int ans = helper(nums, oddCount, i + 1);
        if ((nums[i] & 1) == 1 && oddCount != 2) {
            ans = (ans + helper(nums, Math.max(0, oddCount) + 1, i + 1)) % mod;
        } else if ((nums[i] & 1) == 0 && oddCount != -2) {
            ans = (ans + helper(nums, Math.min(0, oddCount) - 1, i + 1)) % mod;
        }
        return dp[oddCount + 2][i] = ans;
    }

    public int countStableSubsequences(int[] nums) {
        dp = new int[max_state][nums.length];
        for (int i = 0; i < max_state; i++) {
            Arrays.fill(dp[i], -1);
        }
        return helper(nums, 0, 0) - 1;
    }
}