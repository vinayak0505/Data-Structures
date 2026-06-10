import java.util.Arrays;

class Solution {
    public long[] countStableSubarrays(int[] nums, int[][] queries) {
        int n = nums.length;

        int[] preindex = new int[n];
        preindex[0] = 0;
        for(int i = 1; i<n;i++){
            if(nums[i] >= nums[i - 1]){
                preindex[i] = preindex[i - 1];
            }else{
                preindex[i] = i;
            }
        }

        int[] rightIndex = new int[n];
        rightIndex[n - 1] = n - 1;
        for(int i = n - 2; i >= 0; i--){
            if(nums[i] <= nums[i + 1]){
                rightIndex[i] = rightIndex[i + 1];
            }else{
                rightIndex[i] = i;
            }
        }

        long[] presum = new long[n];
        presum[0] = 1;
        for(int i = 1;i<n;i++){
            presum[i] = presum[i - 1] + (i + 1 - preindex[i]);
        }

        // print all arrays
        System.out.println("preindex " + Arrays.toString(preindex));
        System.out.println("rightIndex " + Arrays.toString(rightIndex));
        System.out.println("presum " + Arrays.toString(presum));

        int qn = queries.length;
        long[] ans = new long[qn];

        for(int i = 0; i< qn; i++){
            int l = queries[i][0], r = queries[i][1];

            System.out.println(presum[r] - (l == 0 ? 0 : presum[l - 1]));
            System.out.println((l - preindex[l]) * (Math.min(r, rightIndex[l]) - l + 1));
            ans[i] = presum[r] - (l == 0 ? 0 : presum[l - 1]) - (l - preindex[l]) * (Math.min(r, rightIndex[l]) - l + 1);
        }
        return ans;
    }
}