import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        HashMap<Integer, Integer> mp1 = new HashMap<>();
        int maxValue = 0;
        for (int num : nums1) {
            if (num % k != 0)
                continue;
            maxValue = Math.max(maxValue, num / k);
            mp1.put(num / k, mp1.getOrDefault(num / k, 0) + 1);
        }
        HashMap<Integer, Integer> mp2 = new HashMap<>();
        for (int num : nums2) {
            mp2.put(num, mp2.getOrDefault(num, 0) + 1);
        }

        long ans = 0;
        for (Entry<Integer, Integer> entrySet : mp2.entrySet()) {
            for (int i = entrySet.getKey(); i <= maxValue; i += entrySet.getKey()) {
                ans += (long) entrySet.getValue() * mp1.getOrDefault(i, 0);
            }
        }

        return ans;
    }
}