class Solution {

    private int helper(int i, int op1, int op2, int[] nums, int k, Integer[][][] dp) {
        int n = nums.length;
        if (i == n)
            return 0;
        if (dp[i][op1][op2] != null)
            return dp[i][op1][op2];
        int ans = helper(i + 1, op1, op2, nums, k, dp) + nums[i];

        if (op1 > 0) {
            ans = Math.min(ans, helper(i + 1, op1 - 1, op2, nums, k, dp) + Math.ceilDiv(nums[i], 2));
        }

        if (nums[i] >= k && op2 > 0) {
            ans = Math.min(ans, helper(i + 1, op1, op2 - 1, nums, k, dp) + nums[i] - k);
            if (op1 > 0) {
                ans = Math.min(ans, helper(i + 1, op1 - 1, op2 - 1, nums, k, dp) + Math.ceilDiv(nums[i] - k, 2));
            }
        }
        if (Math.ceilDiv(nums[i], 2) >= k && op2 > 0 && op1 > 0) {
            ans = Math.min(ans, helper(i + 1, op1 - 1, op2 - 1, nums, k, dp) + Math.ceilDiv(nums[i], 2) - k);
        }

        return dp[i][op1][op2] = ans;
    }

    public int minArraySum(int[] nums, int k, int op1, int op2) {
        Integer[][][] dp = new Integer[nums.length + 1][op1 + 1][op2 + 1];

        return helper(0, op1, op2, nums, k, dp);
    }
}