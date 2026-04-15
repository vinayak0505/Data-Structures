class Solution {
    public long minIncrease(int[] nums) {
        int n = nums.length;

        if ((n & 1) == 1) {
            long ans = 0;
            for (int i = 1; i < n; i += 2) {
                if (nums[i] < Math.max(nums[i - 1], nums[i + 1]) + 1)
                    ans += (long)(Math.max(nums[i - 1], nums[i + 1]) + 1) - nums[i];
            }
            return ans;
        }

        long start[] = new long[n];
        long end[] = new long[n];
        for (int i = 1; i < n - 1; i++) {
            start[i] = start[i - 1];
            if ((i & 1) == 1 && nums[i] < Math.max(nums[i - 1], nums[i + 1]) + 1) {
                start[i] += (long)(Math.max(nums[i - 1], nums[i + 1]) + 1) - nums[i];
            }
        }

        for (int i = n - 2; i > 0; i--) {
            end[i] = end[i + 1];
            if (((n - 1 - i) & 1) == 1 && nums[i] < Math.max(nums[i - 1], nums[i + 1]) + 1) {
                end[i] += (long)(Math.max(nums[i - 1], nums[i + 1]) + 1) - nums[i];
            }
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n - 1; i += 2) {
            long posans = start[i] + end[i + 1];
            ans = Math.min(ans, posans);
        }
        return ans;
    }
}