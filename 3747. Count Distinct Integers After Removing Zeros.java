class Solution {

    Long dp[][][];
    public long helper(String str, int i, boolean tight, boolean allzero) {
        if (str.length() == i) {
            if (allzero == true)
                return 0;
            return 1;
        }
        if(dp[i][tight ? 0 : 1][allzero ? 0 : 1] != null){
            return dp[i][tight ? 0 : 1][allzero ? 0 : 1];
        }
        int till = tight ? (str.charAt(i) - '0') : 9;
        int start = allzero ? 0 : 1;
        long ans = 0;
        for (int j = start; j <= till; j++) {
            ans += helper(str, i + 1, tight && (j == till), allzero && (j == 0));
        }
        return dp[i][tight ? 0 : 1][allzero ? 0 : 1] = ans;
    }

    public long countDistinct(long n) {
        String str = Long.toString(n);
        dp = new Long[str.length()][2][2];
        return helper(str, 0, true, true);
    }
}