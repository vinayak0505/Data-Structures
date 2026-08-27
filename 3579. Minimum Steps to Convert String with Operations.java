import java.util.Arrays;

class Solution {

    int dp2[][][];
    int dp[];

    private int helper2(String word1, String word2, int i, int j, int swap) {
        if (dp2[swap][i][j] != -1) {
            return dp2[swap][i][j];
        }
        int count = 0;
        int[][] counts = new int[26][26];

        if (swap == 0) {
            for (int k = i; k <= j; k++) {
                if (word1.charAt(k) != word2.charAt(k))
                    count++;
            }
            for (int k = i; k <= j; k++) {
                if (word2.charAt(k) == word1.charAt(k))
                    continue;

                int c1 = word1.charAt(k) - 'a';
                int c2 = word2.charAt(k) - 'a';

                if (counts[c2][c1] > 0) {
                    count--;
                    counts[c2][c1]--;
                } else {
                    counts[c1][c2]++;
                }
            }
        } else {
            for (int k = i; k <= j; k++) {
                if (word1.charAt(k) != word2.charAt(j - (k - i)))
                    count++;
            }
            for (int k = i; k <= j; k++) {
                int c1 = word1.charAt(k) - 'a';
                int c2 = word2.charAt(j - (k - i)) - 'a';
                if (c1 == c2) continue;

                if (counts[c2][c1] > 0) {
                    count--;
                    counts[c2][c1]--;
                } else {
                    counts[c1][c2]++;
                }
            }
        }
        return dp2[swap][i][j] = count;
    }

    private int helper(String word1, String word2, int i) {
        int n = word1.length();
        if (i == n)
            return 0;
        if (dp[i] != -1) {
            return dp[i];
        }
        int ans = Integer.MAX_VALUE;
        for (int j = i; j < n; j++) {
            int next = helper(word1, word2, j + 1);
            ans = Math.min(ans,
                    Math.min(helper2(word1, word2, i, j, 0),
                            1 + helper2(word1, word2, i, j, 1)) + next);
        }
        return dp[i] = ans;
    }

    public int minOperations(String word1, String word2) {
        int n = word1.length();
        dp = new int[n];
        Arrays.fill(dp, -1);
        dp2 = new int[2][n][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp2[i][j], -1);
            }
        }
        return helper(word1, word2, 0);
    }
}