import java.util.Arrays;

class Solution {
    public long minArraySum(int[] nums) {
        Arrays.sort(nums);

        int maxn = (int)1e5 + 1;
        boolean isPresent[] = new boolean[maxn];
        int minNums[] = new int[maxn];

        for(int i = 0;i<maxn;i++){
            minNums[i] = i;
        }

        int n = nums.length;

        for(int i = 0;i<n;i++){
            isPresent[nums[i]] = true;
        }

        for(int i = 0;i<maxn;i++){
            if(isPresent[i] == false || minNums[i] != i) continue;
            for(int j = i + i;j<maxn;j += i){
                if(minNums[j] == j){
                    minNums[j] = i;
                }
            }
        }
        long ans = 0;
        for(int j = 0;j<n;j++){
            int i = nums[j];
            if(isPresent[i]) {
                ans += (long) minNums[i];
            }
        }
        return ans;
    }
}