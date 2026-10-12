class Solution {

    private int getValue(int[] count) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (count[i] == 0)
                continue;
            ans |= (1 << i);
        }
        return ans;
    }

    private void add(int[] count, int value) {
        for (int i = 0; i < 32; i++) {
            if (((1 << i) & value) == 0)
                continue;
            count[i]++;
        }
    }

    private void remove(int[] count, int value) {
        for (int i = 0; i < 32; i++) {
            if (((1 << i) & value) == 0)
                continue;
            count[i]--;
        }
    }

    public int minimumDifference(int[] nums, int k) {
        int[] count = new int[32];

        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0, j = 0; i < n; i++) {
            add(count, nums[i]);
            ans = Math.min(ans, Math.abs(k - getValue(count)));
            while (j < i && k < getValue(count)) {
                remove(count, nums[j]);
                ans = Math.min(ans, Math.abs(k - getValue(count)));
                j++;
            }
        }
        return ans;
    }
}