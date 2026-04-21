import java.util.ArrayList;

class Solution {

    private int getSmaller(ArrayList<Integer> nums, int find){
        int low = 0;
        int high = nums.size() - 1;
        int ans = nums.size(); // Initialize to nums.size() to handle edge cases
        while(low <= high){
            int mid = (low + high) / 2;
            if(nums.get(mid) >= find){
                ans = mid; 
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
    }
    private int solve(ArrayList<Integer> array){
        if(array.size() == 0) return 0;
        ArrayList<Integer> value = new ArrayList<>();
        value.add(array.get(0));
        for(int i = 1;i<array.size();i++){
            int smaller = getSmaller(value, array.get(i));
            if(smaller == value.size()){
                value.add(array.get(i));
            }else{
                value.set(smaller, array.get(i));
            }
        }
        return value.size();
    }
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int m = 32;
        ArrayList<Integer> array;
        int ans = 0;
        for(int j = 0;j<m;j++){
            array = new ArrayList<>();
            for(int i = 0;i<n;i++){
                if((nums[i] & (1 << j)) != 0){
                    array.add(nums[i]);
                }
            }
            ans = Math.max(ans, solve(array));
        }
        return ans;
    }
}