import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Integer, Map<Integer, Integer>> dp;
    private int minRemovals(int[] nums, int target, int i, int xor) {
        if(dp.containsKey(i) == false){
            dp.put(i, new HashMap<>());
        }
        
        Map<Integer,Integer> xorDp = dp.get(i);
        if(xorDp.containsKey(xor)) return xorDp.get(xor);
        int n = nums.length;
        if(n == i){
            return xor == target ? 0 : -1;
        }
        int take = minRemovals(nums, target, i+1,xor ^ nums[i]);
        int notTake = minRemovals(nums, target, i+1,xor);
        if(notTake == -1) {
            xorDp.put(xor, take);
            return take;
        }
        if(take == -1) {
            xorDp.put(xor, notTake + 1);
            return notTake + 1;
        }
        xorDp.put(xor, Math.min(take, notTake + 1));
        return Math.min(take, notTake + 1);
    }

    public int minRemovals(int[] nums, int target) {
        dp = new HashMap<>();
        return minRemovals(nums, target, 0, 0);
    }
}