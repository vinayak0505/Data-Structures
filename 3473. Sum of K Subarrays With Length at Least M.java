class Solution {
    private int prefixsum[];
    private Integer dp[][][];
    int Neg = (int) -1e9;

    private int helper(int[] nums, int index, int remaining, int canExtend, int m){
        if(nums.length == index) return remaining == 0 ? 0 : Neg;
        if(dp[index][remaining][canExtend] != null) return dp[index][remaining][canExtend];

        int best = Neg;

        if(canExtend == 1){
            best = Math.max(best, nums[index] + helper(nums, index + 1, remaining, 1, m));
            best = Math.max(best, helper(nums, index, remaining, 0, m));
        }else{
            if(remaining == 0) return 0;
            best = Math.max(best, helper(nums, index + 1, remaining, 0, m));
            if(index + m <= nums.length){
                int curSum = prefixsum[index + m] - prefixsum[index];
                best = Math.max(best, curSum + helper(nums, index + m, remaining - 1, 1, m));
            }
        }

        return dp[index][remaining][canExtend] = best;
    }

    public int maxSum(int[] nums, int k, int m) {
        int n = nums.length;
        prefixsum = new int[n + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixsum[i + 1] = prefixsum[i] + nums[i];
        }
        dp = new Integer[n][k + 1][2];
        return helper(nums, 0, k, 0, m);
    }
}