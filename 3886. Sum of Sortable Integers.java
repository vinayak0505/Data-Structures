class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return 1;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1];
            if (nums[i - 1] > nums[i]) {
                preSum[i]++;
            }
        }
        int sol = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (isValid(nums, i, preSum)) {
                    sol += i;
                }
                if (i != n / i && isValid(nums, n / i, preSum)) {
                    sol += n / i;
                }
            }
        }
        return sol;
    }

    private boolean isValid(int[] nums, int k, int[] preSum) {
        int prevMax = 0;
        for (int i = 0; i < nums.length; i += k) {
            int min = nums[i];
            for (int j = i + 1; j < (i + k); j++) {
                min = Math.min(min, nums[j]);
            }
            int val = (nums[i + k - 1] > nums[i]) ? 1 : 0;
            if (((preSum[i + k - 1] - preSum[i] + val) > 1) || ((i != 0) && (prevMax > min))) {
                return false;
            }
            for (int j = i; j < (i + k); j++) {
                prevMax = Math.max(prevMax, nums[j]);
            }
        }
        return true;
    }
}