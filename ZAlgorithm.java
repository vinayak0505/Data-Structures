import java.util.Arrays;

public class ZAlgorithm {

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
}
