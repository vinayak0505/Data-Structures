import java.util.Arrays;

class Solution {

    public int maximumLength(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] %= k;
        }

        for (int i = 0; i < k; i++) {
            ans = Math.max(ans, solve(nums, k, i));
        }
        return ans;
    }

    private int solve(int[] nums, int k, int target) {
        int[] lastIndex = new int[k];
        Arrays.fill(lastIndex, -1);

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int a = (target - nums[i] + k) % k;
            if (a >= 0 && lastIndex[a] != -1) {
                dp[i] = dp[lastIndex[a]] + 1;
            }
            ans = Math.max(ans, dp[i]);
            lastIndex[nums[i]] = i;
        }
        return ans;
    }
}