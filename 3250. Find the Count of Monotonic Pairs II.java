class Solution {
    int mod = (int) 1e9 + 7;

    public int countOfPairs(int[] nums) {
        int maxNum = 0;
        for (int i : nums) {
            maxNum = Math.max(i, maxNum);
        }
        int n = nums.length;
        int dp[][] = new int[n][maxNum + 1];

        for (int i = 0; i <= nums[0]; i++) {
            dp[0][i] = i + 1;
        }

        for (int i = 1; i < n; i++) {
            int prev = 0;
            int prevNum = nums[i - 1];
            int curNum = nums[i];
            int diff = curNum - prevNum;
            for (int j = 0; j <= maxNum; j++) {
                int value = Math.min(j, j - diff);
                if (value < 0)
                    continue;
                if (j <= nums[i]) {
                    prev = (prev + dp[i - 1][value]) % mod;
                }
                dp[i][j] = prev;
            }
        }
        return dp[n - 1][maxNum];
    }
}