import java.util.HashMap;

class Solution {
    private HashMap<String, Integer> dp;
    private int sumOffset;

    public int maxProduct(int[] nums, int k, int limit) {
        sumOffset = 0;
        for (int num : nums) {
            sumOffset += num;
        }
        if (k > sumOffset || k < -sumOffset) {
            return -1;
        }

        dp = new HashMap<>();

        return helper(nums, k, limit, 0, null, 0, true);
    }

    private int helper(int[] nums, int k, int limit, int count, Integer multiply, int i, boolean even) {
        if (i == nums.length) {
            if (multiply != null && count == k && multiply <= limit) {
                return multiply;
            }
            return -1;
        }

        int prodIdx = getProdIndex(multiply, limit);
        int countIdx = count + sumOffset;

        String key = countIdx + " " + prodIdx + " " + i + " " + even;

        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int ans = helper(nums, k, limit, count, multiply, i + 1, even);

        long nextProd = (multiply == null) ? nums[i] : (long) multiply * nums[i];
        if (nextProd > limit) {
            nextProd = limit + 1;
        }

        int newCount = count + (even ? nums[i] : -nums[i]);
        int take = helper(nums, k, limit, newCount, (int) nextProd, i + 1, !even);

        ans = Math.max(ans, take);
        dp.put(key, ans);
        return ans;
    }

    private int getProdIndex(Integer multiply, int limit) {
        if (multiply == null)
            return limit + 2;
        if (multiply > limit)
            return limit + 1;
        return multiply;
    }
}