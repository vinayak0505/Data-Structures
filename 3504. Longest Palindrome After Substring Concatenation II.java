class Solution {

    public int longestPalindrome(String s, String t) {

        int ans = 0;

        int n = s.length();
        int[] sdp = new int[n];

        for (int i = 0; i < n; i++) {
            int count = -1;

            for (int j = i, k = i; j >= 0 && k < n; j--, k++) {
                if (s.charAt(j) != s.charAt(k))
                    break;
                count += 2;
                ans = Math.max(ans, count);
                sdp[j] = count;
            }

            count = 0;
            for (int j = i, k = i + 1; j >= 0 && k < n; j--, k++) {
                if (s.charAt(j) != s.charAt(k))
                    break;
                count += 2;
                ans = Math.max(ans, count);
                sdp[j] = count;
            }
        }

        int m = t.length();
        t = new StringBuilder(t).reverse().toString();
        int[] tdp = new int[m];

        for (int i = 0; i < m; i++) {
            int count = -1;

            for (int j = i, k = i; j >= 0 && k < m; j--, k++) {
                if (t.charAt(j) != t.charAt(k))
                    break;
                count += 2;
                ans = Math.max(ans, count);
                tdp[j] = count;
            }

            count = 0;
            for (int j = i, k = i + 1; j >= 0 && k < m; j--, k++) {
                if (t.charAt(j) != t.charAt(k))
                    break;
                count += 2;
                ans = Math.max(ans, count);
                tdp[j] = count;
            }
        }

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = (s.charAt(i) == t.charAt(j) ? 1 : 0);
                } else {
                    if (s.charAt(i) == t.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                }
                ans = Math.max(ans,
                        Math.max(
                                i == n - 1 ? 0 : sdp[i + 1],
                                j == m - 1 ? 0 : tdp[j + 1])
                                + (2 * dp[i][j]));
            }
        }

        return Math.max(ans, 1);

    }
}