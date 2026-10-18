class Solution {
    private Long helper(int i, int k, boolean taking, int[] nums, int startWithK, Long dp[][][]) {
        int n = nums.length;
        if (k == 0 && taking == false) {
            return 0l;
        }
        if (i == n) {
            return null;
        }
        if (dp[i][k][taking ? 1 : 0] != Long.MIN_VALUE) {
            return dp[i][k][taking ? 1 : 0];
        }
        Long ans = null;
        if (taking) {
            ans = helper(i + 1, k, true, nums, startWithK, dp);
            if (ans != null) {
                ans += (long) Math.pow(-1, startWithK - k) * ((long) nums[i] * k);
            }
            Long ans2 = helper(i + 1, k - 1, false, nums, startWithK, dp);
            if (ans2 != null) {
                ans2 += (long) Math.pow(-1, startWithK - k) * ((long) nums[i] * k);
            }
            if (ans == null) {
                ans = ans2;
            } else if (ans2 != null) {
                ans = Math.max(ans, ans2);
            }
        } else {
            ans = helper(i + 1, k, false, nums, startWithK, dp);
            Long ans2 = helper(i, k, true, nums, startWithK, dp);
            if (ans == null) {
                ans = ans2;
            } else if (ans2 != null) {
                ans = Math.max(ans, ans2);
            }
        }
        return dp[i][k][taking ? 1 : 0] = ans;
    }

    public long maximumStrength(int[] nums, int k) {
        Long dp[][][] = new Long[nums.length][k + 1][2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j][0] = Long.MIN_VALUE;
                dp[i][j][1] = Long.MIN_VALUE;
            }
        }
        Long value = helper(0, k, false, nums, k, dp);
        if (value == null) {
            return -1;
        }
        return value;
    }
}