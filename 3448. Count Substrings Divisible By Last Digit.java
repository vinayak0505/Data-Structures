class Solution {
    public long countSubstrings(String s) {
        int n = s.length();

        // index, div, remaining
        long[][][] dp = new long[n][10][10];

        for (int i = 0; i < n; i++) {
            int cr = s.charAt(i) - '0';

            for (int div = 1; div < 10; div++) {
                dp[i][div][cr % div] = 1;
                if (i == 0)
                    continue;
                for (int rem = 0; rem < 10; rem++) {
                    long prevCount = dp[i - 1][div][rem];
                    dp[i][div][((rem * 10) + cr) % div] += prevCount;
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += dp[i][s.charAt(i) - '0'][0];
        }
        return ans;
    }
}