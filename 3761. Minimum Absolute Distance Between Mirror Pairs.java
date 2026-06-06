import java.util.HashMap;

class Solution {

    private int makeRevValue(int num){
        int value = 0;
        while(num > 0){
            value = (value * 10) + (num % 10);
            num /= 10;
        }
        return value;
    }
    public int minMirrorPairDistance(int[] nums) {
        // number, index
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;

        int ans = -1;

        for(int i = 0; i< n;i++){

            if(map.containsKey(nums[i])){
                int posAns = i - map.get(nums[i]);
                if(ans == -1){
                    ans = posAns;
                }else{
                    ans = Math.min(ans, posAns);
                }
            }
            int revValue = makeRevValue(nums[i]);
            while(revValue % 10 == 0){
                revValue = revValue / 10;
            }
            map.put(revValue, i);
        }

        return ans;
    }
}