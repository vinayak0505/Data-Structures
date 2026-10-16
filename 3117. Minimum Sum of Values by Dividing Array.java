import java.util.Arrays;
import java.util.HashMap;

class Solution {

    private HashMap<Integer, Integer> dp[][];

    private int helper(int i, int j, int prevMask, int[] nums, int[] andValues) {
        int n = nums.length;
        int m = andValues.length;

        if (i == n && j == m) {
            return 0;
        }

        if (i == n || j == m) {
            return -1;
        }

        if (dp[i][j].containsKey(prevMask)) {
            return dp[i][j].get(prevMask);
        }

        int newMask = prevMask & nums[i];
        int ans = helper(i + 1, j, newMask, nums, andValues);
        if (newMask == andValues[j]) {
            int pickedValue = helper(i + 1, j + 1, Integer.MAX_VALUE, nums, andValues);
            if (pickedValue == -1 && ans == -1) {
            } else if (ans == -1) {
                ans = pickedValue + nums[i];
            } else if (pickedValue != -1) {
                ans = Math.min(pickedValue + nums[i], ans);

            }
        }
        dp[i][j].put(prevMask,ans);
        return ans;
    }

    public int minimumValueSum(int[] nums, int[] andValues) {
        int n = nums.length;
        int m = andValues.length;
        dp = new HashMap[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = new HashMap<>();
            }
        }
        return helper(0, 0, Integer.MAX_VALUE, nums, andValues);
    }
}