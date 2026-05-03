class Solution {
    public int[] minCost(int[] nums, int[][] queries) {
        int n = nums.length;
        
        int[] presum = new int[n];
        presum[0] = 0;
        int prefix = 1;
        for(int i = 1;i < n -1;i++){
            presum[i] = prefix;
            prefix += (nums[i] - nums[i - 1] >nums[i + 1] - nums[i]) ? 1 : nums[i + 1] - nums[i];
        }
        presum[n - 1] = prefix;

        int[] leftpresum = new int[n];
        leftpresum[n - 1] = 0;
        prefix = 1;
        for(int i = n- 2;i > 0;i--){
            leftpresum[i] = prefix;
            prefix += (nums[i] - nums[i - 1] <= nums[i + 1] - nums[i]) ? 1 : nums[i] - nums[i - 1];
        }
        leftpresum[0] = prefix;

        // for(int i = 0;i<n;i++){
        //     System.out.println(leftpresum[i]);
        // }
        
        int size = queries.length;
        int ans[] = new int[size];
        for(int i = 0;i<size;i++){
            int l = queries[i][0];
            int r = queries[i][1];
            if(r >= l){
                ans[i] = presum[r] - presum[l];
            }else if(r < l){
                ans[i] = leftpresum[r] - leftpresum[l];
            }
                
        }
        return ans;
    }
}