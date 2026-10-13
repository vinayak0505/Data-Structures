class Solution {
    public long sumDigitDifferences(int[] nums) {
        long ans = 0;
        int count = 0;
        int temp_num = nums[0];
        int n = nums.length;
        while (temp_num > 0) {
            temp_num /= 10;
            count++;
        }

        int dp[][] = new int[count][10];

        for (int num : nums) {
            for (int i = 0; num > 0; i++) {
                dp[i][num % 10]++;
                num /= 10;
            }
        }

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 10; j++) {
                ans += (long) dp[i][j] * (n - dp[i][j]);
            }
        }
        return ans / 2;
    }
}