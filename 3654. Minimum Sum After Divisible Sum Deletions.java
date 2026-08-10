import java.util.Arrays;

class Solution {
    public long minArraySum(int[] nums, int k) {
        int maxNum = (int)1e6 + 1;
        int n= nums.length;

        long[] dp = new long[maxNum];

        Arrays.fill(dp, -1);
        dp[0] = 0;

        long sum = 0;
        for(int i = 0;i<n;i++){
            sum += nums[i];
            int remainder = (int)(sum % k);
            if(dp[remainder] == -1){
                dp[remainder] = sum;
            }else{
                long min = Math.min(sum, dp[remainder]);
                sum = min;
                dp[remainder] = min;
            }
        }
        return sum;
    }
}