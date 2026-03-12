import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isValid(Map<Integer, Integer> mp, int k, int m) {
        if (mp.size() != k)
            return false;
        for (Integer values : mp.values()) {
            if (values < m)
                return false;
        }
        return true;
    }

    public long countSubarrays(int[] nums, int k, int m) {
        Map<Integer, Integer> slow = new HashMap<>();
        Map<Integer, Integer> fast = new HashMap<>();
        long vcount = 0;

        for (int right = 0, slowp = 0, fastp = 0; right < nums.length; right++) {
            slow.put(nums[right], slow.getOrDefault(nums[right], 0) + 1);
            fast.put(nums[right], fast.getOrDefault(nums[right], 0) + 1);

            while ((fast.size() > k  || isValid(fast, k, m)) && fastp <= right) {
                int newValue = fast.get(nums[fastp]) - 1;
                if (newValue == 0) {
                    fast.remove(nums[fastp]);
                } else {
                    fast.put(nums[fastp], newValue);
                }
                fastp++;
            }

            while (slow.size() > k && slowp <= right) {
                int newValue = slow.get(nums[slowp]) - 1;
                if (newValue == 0) {
                    slow.remove(nums[slowp]);
                } else {
                    slow.put(nums[slowp], newValue);
                }
                slowp++;
            }
            if (isValid(slow, k, m)) {
                System.out.println(fastp - slowp);
                vcount += (long)(fastp - slowp);
            }

        }
        return vcount;
    }
}