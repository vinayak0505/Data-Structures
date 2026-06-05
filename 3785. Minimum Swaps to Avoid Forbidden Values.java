import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public int minSwaps(int[] nums, int[] forbidden) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        int n = nums.length;

        for(int i : nums){
            mp.put(i, mp.getOrDefault(i, 0) + 1);
        }

        for (int i : forbidden) {
            mp.put(i, mp.getOrDefault(i, 0) + 1);
        }

        for(int i: mp.values()){
            if(i > n) return -1;
        }
        ArrayList<Integer> notAllowed = new ArrayList<>();
        for(int i = 0;i<n;i++){
            if(nums[i] == forbidden[i]) notAllowed.add(nums[i]);
        }
        if(notAllowed.size() == 0) return 0;
        int count = 0;
        int num = notAllowed.get(0);

        for(int i : notAllowed){
            if(num == i){
                count++;
            }else{
                if(count == 0){
                    num = i;
                    count = 1;
                }else{
                    count--;
                }
            }
        }

        // checki is num is more then half
        count = 0;
        for(int i: notAllowed){
            if(num == i) count++;
        }

        if(count * 2 > notAllowed.size()) return count;
        return ((notAllowed.size() + 1) / 2);
        // return 0;
    }
}