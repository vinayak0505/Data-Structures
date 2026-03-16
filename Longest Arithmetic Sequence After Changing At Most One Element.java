class Solution {
    public int longestArithmetic(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            left[i] = 2;
            right[i] = 2;
        }

        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 3; i >= 0; i--) {
            if (nums[i] - nums[i + 1] == nums[i + 1] - nums[i + 2]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ans = Math.max(ans, right[i]);
            } else if (i == n - 1) {
                ans = Math.max(ans, left[i]);
            } else {
                ans = Math.max(ans, Math.max(right[i], left[i]) + 1);
            }
        }
        for (int i = 1; i < n - 1; i++) {
            if (((nums[i + 1] - nums[i - 1]) & 1) != 1) {
                int diff = ((nums[i + 1] - nums[i - 1]) >> 1);
                int leftSum = 1;
                int rightSum = 1;
                if (i > 1 && diff == nums[i - 1] - nums[i - 2]) {
                    leftSum = left[i - 1];
                }
                if (i < n - 2 && diff == nums[i + 2] - nums[i + 1]){
                    rightSum = right[i + 1];
                }
                ans = Math.max(ans, leftSum + rightSum + 1);

            }
        }
        return ans;
    }
}