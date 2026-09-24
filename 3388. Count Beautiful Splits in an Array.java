class Solution {

    private boolean valid(int i, int j, int dp[][]) {
        int n = dp.length;
        int a = i + 1;
        int b = j - i;
        int c = n - a - b;
        if(b >= a){
            if(dp[i][i + a] >= a) return true;
        }
        if(c >= b){
            if(dp[j][j + b] >= b) return true;
        }

        return false;
    }

    public int beautifulSplits(int[] nums) {
        int n = nums.length;
        int dp[][] = new int[n][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (nums[i] != nums[j])
                    continue;
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = dp[i - 1][j - 1] + 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (valid(i, j, dp))
                    ans++;
            }
        }
        return ans;
    }
}