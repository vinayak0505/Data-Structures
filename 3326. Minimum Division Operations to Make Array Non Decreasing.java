class Solution {

    private int MAX_NUM = 0;
    private Integer dp[];

    private void buildDp() {
        dp = new Integer[MAX_NUM];

        for (int i = 2; i < MAX_NUM; i++) {
            if (dp[i] != null)
                continue;
            for (int j = i + i; j < MAX_NUM; j += i) {
                if(dp[j] != null) continue;
                dp[j] = i;
            }
        }

    }

    private int getSmaller(int smallerOrEqual, int value) {
        if (value <= smallerOrEqual) {
            return value;
        }
        System.out.println("smaller for " + smallerOrEqual + " value " + value + " array " + dp[value]);

        if (dp[value] == null || dp[value] > smallerOrEqual)
            return -1;

        return dp[value];
    }

    public int minOperations(int[] nums) {
        for (int i : nums) {
            MAX_NUM = Math.max(i + 1, MAX_NUM);
        }
        buildDp();
        int prev = Integer.MAX_VALUE;
        int count = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            prev = getSmaller(prev, nums[i]);
            if (prev == -1) {
                return -1;
            }
            if (prev != nums[i]) {
                count++;
            }
        }
        return count;
    }
}