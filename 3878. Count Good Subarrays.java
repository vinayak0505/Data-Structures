import java.util.HashMap;
import java.util.Map;

class Solution {

    private void fill(boolean bit[], int nextGreater[], int prevGreater[]){
        int n = bit.length;
        int prev = -1;
        for(int i = 0;i<n;i++){
            if(bit[i]){
                prevGreater[i] = -1;
                prev = i;
            }else{
                prevGreater[i] = prev;
            }
        }

        int next = n;
        for(int i = n - 1; i >= 0;i--){
            if(bit[i]){
                nextGreater[i] = n;
                next = i;
            }else{
                nextGreater[i] = next;
            }
        }
    }
    public long countGoodSubarrays(int[] nums) {
        int n = nums.length;
        int m = 32;
        boolean bit[][] = new boolean[m][n];
        for(int i = 0;i<n;i++){
            int num = nums[i];
            for(int j = 0;j<m;j++){
                if(((1 << j) & num) != 0){
                    bit[j][i] = true;
                }
            }
        }

        int nextGreater[][] = new int[m][n];
        int prevGreater[][] = new int[m][n];

        for(int j = 0; j< m;j++){
            fill(bit[j], nextGreater[j], prevGreater[j]);
        }

        long ans = 0;

        Map<Integer, Integer> mp = new HashMap<>();

        for(int i = 0;i<n;i++){
            int prev = mp.getOrDefault(nums[i], -1);
            int next = n;
            for(int j = 0;j<m;j++){
                prev = Math.max(prev, prevGreater[j][i]);
                next = Math.min(next, nextGreater[j][i]);
            }
            ans += ((long)(i - prev) * (long)(next - i));
            mp.put(nums[i], i);
        }
        return ans;

    }
}