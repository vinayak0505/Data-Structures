import java.util.HashMap;

class Solution {
    public long minArraySum(int[] nums, int k) {
        long ans = 0;
        long sum = 0;
        int n = nums.length;

        for(int i = 0;i< n;i++){
            sum += nums[i];
        }

        ans = sum;
        HashMap<Integer, Long> remainderToSumMap = new HashMap<>();
        int remainder = 0;
        long curSum = 0;
        remainderToSumMap.put(0, 0l);

        for(int i = 0;i<n;i++){
            remainder = (remainder + nums[i]) % k;
            curSum += nums[i];

            if(remainderToSumMap.containsKey(remainder)){
                ans = Math.min(ans, sum - (curSum - remainderToSumMap.get(remainder)));
            }else{
                remainderToSumMap.put(remainder, curSum);
            }
        }
        return ans;

    }
}