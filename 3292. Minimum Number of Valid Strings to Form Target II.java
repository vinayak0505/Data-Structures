import java.util.Arrays;

class Solution {

    public int[] getArray(String word, String pattern, char separator) {
        int w = word.length();
        int p = pattern.length();
        int size = w + p + 1;
        int[] dp = new int[size];
        String finalWord = pattern + separator + word;
        int till = 0;
        int index = -1;
        for (int i = 1; i < size; i++) {
            int j = 0;
            if (till >= i) {
                int diff = i - index;
                int maxValue = Math.min(dp[diff], till - i + 1);
                dp[i] = maxValue;
                j = maxValue;
            }
            for (; i + j < dp.length; j++) {
                if (finalWord.charAt(i + j) != finalWord.charAt(j))
                    break;
                if (i + j > till) {
                    till = i + j;
                    index = i;
                }
                dp[i]++;
            }
        }
        return Arrays.copyOfRange(dp, p + 1, finalWord.length());
    }

    public int[] getArray(String word, String pattern) {
        return getArray(word, pattern, '$');
    }

    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        int[] dp = new int[n];

        for (int i = 0; i < words.length; i++) {
            int[] newdp = getArray(target, words[i]);
            for (int j = 0; j < newdp.length; j++) {
                if (newdp[j] > dp[j]) {
                    dp[j] = newdp[j];
                }
            }
        }
        int ans = 0;
        int farthest = dp[0];
        int till = 0;
        for (int i = 0; i < n; i++) {
            farthest = Math.max(farthest, dp[i] + i);
            if (till == i) {
                ans++;
                if (farthest == i)
                    return -1;
                till = farthest;
            }
        }

        return ans;

    }
}