import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class Solution {
    public int minCost(int[] nums1, int[] nums2) {
        Map<Integer, Integer> mp = new HashMap<>();
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();

        for(int i = 0;i<nums1.length;i++){
            mp.put(nums1[i], mp.getOrDefault(nums1[i], 0) + 1);
            m1.put(nums1[i], m1.getOrDefault(nums1[i], 0) + 1);
        }
        for(int i = 0;i<nums2.length;i++){
            mp.put(nums2[i], mp.getOrDefault(nums2[i], 0) + 1);
            m2.put(nums2[i], m2.getOrDefault(nums2[i], 0) + 1);
        }

        int cost = 0;
        for(Entry<Integer, Integer> pair: mp.entrySet()){
            if((pair.getValue() & 1) == 1) return -1;
            cost += Math.abs((pair.getValue() >> 1) - m1.getOrDefault(pair.getKey(), 0));

        }
        return cost >> 1;
    }
}