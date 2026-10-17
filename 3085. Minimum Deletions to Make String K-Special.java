import java.util.Arrays;

class Solution {
    int m = 26;

    private int getMin(int[] freq, int k, int index, int cur) {
        int bottom = Math.max(0, cur - k);
        int top = cur;

        int ans = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] >= bottom && freq[i] <= top)
                continue;
            if (freq[i] > top) {
                ans += freq[i] - top;
            } else {
                ans += freq[i];
            }
        }

        return ans;
    }

    public int minimumDeletions(String word, int k) {
        int[] freq = new int[m];
        int maxValue = 0;

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;

            maxValue = Math.max(freq[c - 'a'], maxValue);
        }

        int ans = Integer.MAX_VALUE;

        System.out.println(Arrays.toString(freq));

        for (int i = 0; i <= maxValue; i++) {
            System.out.println(i + " " + getMin(freq, k, 0, i));
            ans = Math.min(ans, getMin(freq, k, 0, i));
        }
        return ans;
    }
}