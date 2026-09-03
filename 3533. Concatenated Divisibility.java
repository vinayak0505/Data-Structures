import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    boolean dp[][];

    private ArrayList<Integer> helper(int[] nums, int taken, int k, int i, int remainder) {
        if (i == nums.length) {
            if (remainder == 0) {
                return new ArrayList<>();
            }
            return null;
        }
        if (dp[taken][remainder]) {
            return null;
        }
        for (int j = 0; j < nums.length; j++) {
            if (((1 << j) & taken) != 0)
                continue;
            int newremainder = remainder * (int)Math.pow(10, Integer.toString(nums[j]).length());
            newremainder += nums[j];
            newremainder %= k;
            ArrayList<Integer> ans = helper(nums, (1 << j) | taken, k, i + 1, newremainder);
            if (ans != null) {
                ans.addLast(nums[j]);
                return ans;
            }
            dp[(1 << j) | taken][newremainder] = true;
        }
        return null;
    }

    public int[] concatenatedDivisibility(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        dp = new boolean[1 << n][k];
        ArrayList<Integer> ans = helper(nums, 0, k, 0, 0);
        if (ans == null) {
            return new int[] {};
        }

        int[] actualAns = new int[n];
        for (int i = 0; i < actualAns.length; i++) {
            actualAns[actualAns.length - i - 1] = ans.get(i);
        }
        return actualAns;
    }
}