import java.util.HashMap;
import java.util.Map;

class Solution {
    private void add(int num, int[] bitCount, Map<Integer, Integer> mp){
        for(int i = 0;i<31;i++){
            int bit = (1 << i);
            if((num & bit) == 1){
                bitCount[i]++;
            }
        }
        mp.put(num, mp.getOrDefault(num, 0) + 1);
    }
    
    private boolean valid(int[] count, Map<Integer, Integer> mp){
        int value = 0;
        for(int i = 0;i < 31;i++){
            if(count[i] == 0) continue;
            value |= (1 << i);
        }
        return mp.containsKey(value);
        
    }
    private void remove(int num,int[] count, Map<Integer, Integer> mp){
        for(int i = 0;i<31;i++){
            int bit = (1 << i);
            if((num & bit) == 1){
                count[i]--;
            }
        }
        int value = mp.get(num);
        if(value == 1){
            mp.remove(num);
        }else{
            mp.put(num, value - 1);
        }
    }


    public long countGoodSubarrays(int[] nums) {
        int[] headLeft, headRight;
        headLeft = new int[32];
        headRight = new int[32];
        
        int n = nums.length;
        int ans = 0;
        Map<Integer, Integer> leftMp = new HashMap<>();
        Map<Integer, Integer> rightMp = new HashMap<>();
        for(int left = 0, right = 0, end = 0; end < n;end++){
            add(nums[end], headLeft, leftMp);
            add(nums[end], headRight, rightMp);

            while(valid(headLeft, leftMp) == false && left < end){
                remove(nums[left], headLeft, leftMp);
                left++;
            }

            while(valid(headRight, rightMp) && right < end){
                remove(nums[right], headRight, rightMp);
                right++;
            }
            System.out.println("left: " + left + " right: " + right + " end: " + end);
            ans += right - left;
        }
        return ans;
    }
}