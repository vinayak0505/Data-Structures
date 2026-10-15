import java.util.HashMap;

class Solution {

    private boolean valid(int median, int[] nums, long count) {
        long ans = 0;
        int n = nums.length;
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            while (freq.size() > median && i >= j) {
                int value = freq.get(nums[j]);
                value--;
                if (value == 0) {
                    freq.remove(nums[j]);
                } else {
                    freq.put(nums[j], value);
                }
                j++;
            }
            ans += (i - j + 1);
        }
        return ans >= count;
    }

    public int medianOfUniquenessArray(int[] nums) {
        int low = 1;
        int n = nums.length;
        int high = n;
        long total = (long) n * (n + 1) / 2;

        int ans = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (valid(mid, nums, (total + 1) / 2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}