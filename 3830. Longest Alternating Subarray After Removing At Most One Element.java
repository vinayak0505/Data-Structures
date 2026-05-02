class Solution {
    public int longestAlternating(int[] nums) {
        int n = nums.length;
        int left[][] = new int[n][2];
        int right[][] = new int[n][2];

        // Initialize left and right arrays with 1
        for (int i = 0; i < n; i++) {
            left[i][0] = 1;
            left[i][1] = 1;
            right[i][0] = 1;
            right[i][1] = 1;
        }

        int ans = 1;
        for(int i = 1;i<n;i++){
            if(nums[i] > nums[i - 1]){
                // if curr bigger taking smaller form inside 1
                left[i][1] = left[i - 1][0] + 1;
                ans = Math.max(left[i][1], ans);
            }else if(nums[i] < nums[i - 1]){
                left[i][0] = left[i - 1][1] + 1;
                ans = Math.max(left[i][0], ans);
            }
        }

        for(int i = n - 2;i>= 0;i--){
            if(nums[i] > nums[i + 1]){
                right[i][1] = right[i + 1][0] + 1;
                ans = Math.max(right[i][1], ans);
            }else if(nums[i] < nums[i + 1]){
                right[i][0] = right[i + 1][1] + 1;
                ans = Math.max(right[i][0], ans);
            }
        }
        for(int i = 2;i<n;i++){
            if(nums[i] > nums[i - 2]){
                ans = Math.max(right[i][1] + left[i - 2][0], ans);
            }else if(nums[i] < nums[i - 2]){
                ans = Math.max(right[i][0] + left[i - 2][1], ans);
            }
        }

        return ans;
    }
}