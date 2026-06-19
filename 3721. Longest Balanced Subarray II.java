import java.util.Arrays;
import java.util.HashMap;

class Solution {

    private int[] firstOcc;
    
    private void buildFirstOcc(int[] nums){
        int max = (int) 1e5 + 1;
        firstOcc = new int[max];
        Arrays.fill(firstOcc, -1);
    }
    
    public int longestBalanced(int[] nums) {
        buildFirstOcc(nums);
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int ans = 0;
        map.put(0, 0);
        for(int i = 0; i< nums.length;i++){
            if(firstOcc[nums[i]] == -1){
                firstOcc[nums[i]] = i;
                if(nums[i] % 2 == 0){
                    sum--;
                }else{
                    sum++;
                }
            }
            System.out.println(sum + " " + i);
            if(map.containsKey(sum)){
                ans = Math.max(ans, i - map.get(sum) + 1);
            }else{
                map.put(sum, i);
            }
        }
        return ans;
    }
}