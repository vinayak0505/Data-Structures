import java.util.Arrays;

class Solution {
    public long maxProduct(int[] nums) {
        int maxNumber = Arrays.stream(nums).max().orElse(0);

        int maxBitSize = Integer.toBinaryString(maxNumber).length();

        maxNumber = (1 << maxBitSize) - 1;

        int dp[] = new int[maxNumber + 1];

        for(int num: nums){
            dp[num] = num;
        }

        for(int i = 0; i < maxBitSize;i++){
            for(int j = 0; j <= maxNumber; j++){
                if(((1 << i) & j) == 0) continue;
                int subset = ((1 << i) ^ j);
                dp[j] = Math.max(dp[j], dp[subset]);
            }
        }

        long ans = 0;
        for(int num: nums){
            int xor = maxNumber ^ num;
            ans = Math.max(ans, (long)dp[xor] * (long)num);
        }
        return ans;
    }
}