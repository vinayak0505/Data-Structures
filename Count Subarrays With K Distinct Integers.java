import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isValid(Map<Integer, Integer> mp, int k, int more) {
        if (mp.size() != k)
            return false;
        if(more != k) return false;
        return true;
    }

    public long countSubarrays(int[] nums, int k, int m) {
        Map<Integer, Integer> slow = new HashMap<>();
        Map<Integer, Integer> fast = new HashMap<>();
        long vcount = 0;
        int fastMore = 0, slowMore = 0;

        for (int right = 0, slowp = 0, fastp = 0; right < nums.length; right++) {
            int slowValue = slow.getOrDefault(nums[right], 0) + 1;
            if(slowValue == m){
                slowMore++;
            }
            slow.put(nums[right], slowValue);
            int fastValue = fast.getOrDefault(nums[right], 0) + 1;
            if(fastValue == m){
                fastMore++;
            }
            fast.put(nums[right], fastValue);

            while ((fast.size() > k || isValid(fast, k, fastMore)) && fastp <= right) {
                int newValue = fast.get(nums[fastp]) - 1;
                if(newValue == m - 1){
                    fastMore--;
                }
                if (newValue == 0) {
                    fast.remove(nums[fastp]);
                } else {
                    fast.put(nums[fastp], newValue);
                }
                fastp++;
            }

            while (slow.size() > k && slowp <= right) {
                int newValue = slow.get(nums[slowp]) - 1;
                if(newValue == m - 1){
                    slowMore--;
                }
                if (newValue == 0) {
                    slow.remove(nums[slowp]);
                } else {
                    slow.put(nums[slowp], newValue);
                }
                slowp++;
            }
            if (isValid(slow, k, slowMore)) {
                System.out.println(fastp - slowp);
                vcount += (long)(fastp - slowp);
            }

        }
        return vcount;
    }
}