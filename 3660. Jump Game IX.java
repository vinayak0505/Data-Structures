class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;

        int max_array[] = new int[n];
        int prev = 0;
        for (int i = 0; i < n; i++) {
            prev = Math.max(prev, nums[i]);
            max_array[i] = prev;
        }

        prev = Integer.MAX_VALUE;
        int min_array[] = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            prev = Math.min(prev, nums[i]);
            min_array[i] = prev;
        }

        nums[n - 1] = max_array[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (max_array[i] <= min_array[i + 1]) {
                nums[i] = max_array[i];
            } else {
                nums[i] = nums[i + 1];
            }
        }
        return nums;
    }
}