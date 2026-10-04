class Solution {
    int size = 26;

    private boolean valid(int[] word1Count, int[] word2Count) {
        for (int i = 0; i < 26; i++) {
            if (word1Count[i] < word2Count[i])
                return false;
        }
        return true;
    }

    public long validSubstringCount(String word1, String word2) {
        int[] word2Count = new int[size];

        for (char c : word2.toCharArray()) {
            word2Count[c - 'a']++;
        }
        long ans = 0;
        int[] word1Count = new int[size];
        for (int i = 0, j = -1; i < word1.length(); i++) {
            word1Count[word1.charAt(i) - 'a']++;
            while (valid(word1Count, word2Count)) {
                ans = ans + (word1.length() - i);
                j++;
                word1Count[word1.charAt(j) - 'a']--;
            }
        }
        return ans;
    }
}