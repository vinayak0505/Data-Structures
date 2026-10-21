import java.util.Arrays;

class Solution {

    private int[] zIndex(String word) {
        int n = word.length();
        int[] ans = new int[n];

        int index = -1;
        int till = 0;

        for (int i = 1; i < n; i++) {
            int j = 0;
            if(till >= i){
                int diff = i - index;
                int maxValue = Math.min(ans[diff], till - i + 1);
                ans[i] = maxValue;
                j = maxValue;
            }

            for (; i + j < n; j++) {
                if(word.charAt(i + j) != word.charAt(j)){
                    break;
                }
                if(i + j > till){
                    till = i + j;
                    index = i;
                }
                ans[i]++;
            }
        }

        return ans;
    }

    public int minimumTimeToInitialState(String word, int k) {
        int[] kmp = zIndex(word);
        // System.out.println(Arrays.toString(kmp));
        for (int i = k; i < kmp.length; i += k) {
            if(i + kmp[i] == word.length()) return i / k;
        }
        return Math.ceilDiv(word.length(), k);
    }
}