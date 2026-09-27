class Solution {
    int maxLength = 0;

    private boolean isValid(int lastAdded, int[] count) {
        for (int k = 0; k < maxLength; k++) {
            if (k + lastAdded < maxLength && count[k] > 0 && count[k + lastAdded] > 0 && (k != lastAdded || count[k] > 1))
                return false;
            if (lastAdded - k > -1 && count[k] > 0 && count[lastAdded - k] > 0 && (k != lastAdded - k ||  count[k] > 1))
                return false;
        }
        return true;
    }

    public int maxSubarray(int[] nums) {
        int n = nums.length;
        for (int num: nums) {
            maxLength = Math.max(maxLength, num + 1);
        }
        if (n < 3)
            return n;

        int ans = 2;

        int[] count = new int[maxLength];
        count[nums[0]]++;
        count[nums[1]]++;

        for (int i = 2, j = -1; i < nums.length; i++) {
            count[nums[i]]++;
            while (j < (i - 2) && isValid(nums[i], count) == false) {
                j++;
                count[nums[j]]--;
            }
            if (j < (i - 2)) {
                ans = Math.max(ans, i - j);
            }
        }
        return ans;
    }
}