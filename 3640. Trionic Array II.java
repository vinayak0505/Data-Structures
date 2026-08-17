import java.util.Arrays;

class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;

        long[] increasingSumTillHere = new long[n];
        long[] increasingSumFromHere = new long[n];
        int[] decreasingIndex = new int[n];
        long[] prefixSum = new long[n];

        Arrays.fill(increasingSumTillHere, Long.MIN_VALUE);
        Arrays.fill(increasingSumFromHere, Long.MIN_VALUE);

        for (int i = 1; i < n; i++) {
            if(nums[i] <= nums[i - 1]) continue;
            increasingSumTillHere[i] = Math.max(increasingSumTillHere[i - 1], nums[i - 1]) + nums[i];
        }

        for (int i = n - 2; i >= 0; i--) {
            if(nums[i] >= nums[i + 1]) continue;
            increasingSumFromHere[i] = Math.max(increasingSumFromHere[i + 1], nums[i + 1]) + nums[i];
        }

        decreasingIndex[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if(nums[i] <= nums[i + 1]){
                decreasingIndex[i] = i;
            }else {
                decreasingIndex[i] = decreasingIndex[i + 1];
            }

        }

        long sum = 0;
        for (int i = 0; i < prefixSum.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }

        long ans = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if(increasingSumTillHere[i] == Long.MIN_VALUE) continue;
            long posAns = increasingSumTillHere[i];
            int nextIndex = decreasingIndex[i];
            if(nextIndex == i) continue;
            if(increasingSumFromHere[nextIndex] == Long.MIN_VALUE) continue;
            posAns += increasingSumFromHere[nextIndex];
            posAns += prefixSum[nextIndex - 1] - prefixSum[i];
            ans = Math.max(ans, posAns);
        }

        return ans;
    }
}