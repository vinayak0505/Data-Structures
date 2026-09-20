class Solution {

    private int helper(int[] nums, int a, int b) {
        int prev_temp = 0;
        int temp = 0;
        int start = -1, end = -1;

        int temp_start = -1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == a){
                temp--;
            }else if(nums[i] == b){
                temp++;
            }

            if(temp > prev_temp){
                start = temp_start;
                end = i;
                prev_temp = temp;
            }

            if(temp < 0){
                temp = 0;
                temp_start = i + 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (start <= i && i <= end) {
                if (nums[i] == b) {
                    ans++;
                }
            } else if (nums[i] == a) {
                ans++;
            }
        }
        return ans;

    }

    public int maxFrequency(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i <= 50; i++) {
            ans = Math.max(ans, helper(nums, k, i));
        }
        return ans;
    }
}