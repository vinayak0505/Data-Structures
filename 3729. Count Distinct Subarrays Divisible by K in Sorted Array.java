import java.util.HashMap;
import java.util.Map;

class Solution {

    public long numGoodSubarrays(int[] nums, int k) {
        long ans = 0;
        long presum = 0;
        long presumNonRepeating = 0;
        Map<Long, Integer> mp = new HashMap<>();
        mp.put(0l, 1);
        for(int i = 0, j = 0;i<nums.length;i++){
            while(j < i && nums[j] != nums[i]){
                presumNonRepeating += nums[j];
                presumNonRepeating %= k;
                mp.put(presumNonRepeating, mp.getOrDefault(presumNonRepeating, 0) + 1);
                j++;
            }
            presum += nums[i];
            presum %= k;

            ans += mp.getOrDefault(presum, 0);
            
        }
        return ans;
    }
}