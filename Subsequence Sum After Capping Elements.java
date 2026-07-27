import java.util.Arrays;

class Solution {

    private int lowerBound(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (k <= nums[mid]) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }
        return ans;
    }

    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;

        int[] presum = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            presum[i] = sum;
        }

        boolean[][] dp = new boolean[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                if(nums[0] <= k){
                    dp[i][nums[0]] = true;
                }
                continue;
            }
            for (int j = 0; j <= k; j++) {
                dp[i][j] |= dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] |= dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        boolean[] ans = new boolean[n];

        for (int i = 0; i < n; i++) {
            int target = i + 1;

            int lb = lowerBound(nums, target);

            for (int mul = lb + 1; mul <= n; mul++) {
                int remaining = (mul - lb - 1) * target;

                if (k < remaining)
                    break;
                if (k - remaining > k)
                    continue;
                if (dp[lb + 1][k - remaining]) {
                    ans[i] = true;
                    break;
                }
            }
        }
        return ans;

    }
}