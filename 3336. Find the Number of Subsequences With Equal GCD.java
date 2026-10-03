class Solution {

    private int MAX_VALUE = 201;
    int mod = (int) 1e9 + 7;

    private int gcp(int a, int b) {
        if (a == MAX_VALUE && b == MAX_VALUE) {
            return MAX_VALUE;
        }
        if (a == MAX_VALUE) {
            return b;
        }
        if (b == MAX_VALUE) {
            return a;
        }

        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return Math.abs(a);
    }

    private Integer dp[][][];

    private int helper(int i, int agcd, int bgcd, int[] nums) {
        int n = nums.length;
        if (i == n) {
            if (agcd == bgcd && agcd != MAX_VALUE)
                return 1;
            return 0;
        }

        if (dp[i][agcd][bgcd] != null)
            return dp[i][agcd][bgcd];

        int ans = helper(i + 1, agcd, bgcd, nums);
        ans = (ans + helper(i + 1, gcp(agcd, nums[i]), bgcd, nums)) % mod;
        ans = (ans + helper(i + 1, agcd, gcp(bgcd, nums[i]), nums)) % mod;
        return dp[i][agcd][bgcd] = ans;
    }

    public int subsequencePairCount(int[] nums) {
        dp = new Integer[nums.length][MAX_VALUE + 1][MAX_VALUE + 1];

        return helper(0, MAX_VALUE, MAX_VALUE, nums);
    }
}