import java.util.Arrays;

class Solution {
    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);

        long ans = 0;
        int n = nums.length;
        if (nums[n / 2] > k) {
            ans += Math.abs(nums[n / 2] - k);
            for (int i = 0; i < n / 2; i++) {
                if (nums[i] > k) {
                    ans += nums[i] - k;
                }
            }
        } else if (nums[n / 2] < k) {
            ans += Math.abs(nums[n / 2] - k);
            for (int i = n / 2 + 1; i < nums.length; i++) {
                if (nums[i] < k) {
                    ans += k - nums[i];
                }
            }
        }
        return ans;
    }
}