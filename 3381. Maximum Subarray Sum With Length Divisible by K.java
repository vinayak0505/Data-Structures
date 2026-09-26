class Solution {
    int limit = (int) Math.sqrt(2 * Math.pow(10, 5));

    private long maxSumStartFromWithDiff(int[] nums, int start, int k) {
        long ans = Long.MIN_VALUE;
        long tempans = 0;
        for (int i = start; i < nums.length; i++) {
            tempans += nums[i];
            if ((start - i - 1) % k == 0) {
                ans = Math.max(tempans, ans);
                if (tempans < 0)
                    tempans = 0;
            }

        }
        return ans;
    }

    private long ansForLessK(int[] nums, int k) {
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            ans = Math.max(ans, maxSumStartFromWithDiff(nums, i, k));
        }
        return ans;
    }

    private long maxSumWithWindow(int[] nums, int size) {
        long tempans = 0;
        for (int i = 0; i < size; i++) {
            tempans += nums[i];
        }
        long ans = tempans;

        for (int i = size; i < nums.length; i++) {
            tempans += nums[i];
            tempans -= nums[i - size];
            ans = Math.max(ans, tempans);
        }
        return ans;
    }

    private long ansForMoreK(int[] nums, int k) {
        long ans = Long.MIN_VALUE;
        for (int i = k; i <= nums.length; i += k) {
            ans = Math.max(ans, maxSumWithWindow(nums, i));
        }
        return ans;
    }

    public long maxSubarraySum(int[] nums, int k) {
        if (k <= limit) {
            return ansForLessK(nums, k);
        }
        return ansForMoreK(nums, k);
    }
}