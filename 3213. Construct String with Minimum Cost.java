import java.util.Arrays;

class Solution {

    class Trie {
        int value = Integer.MAX_VALUE;
        int n = 26;

        Trie[] next = new Trie[n];

        void updateValue(int value) {
            this.value = Math.min(this.value, value);
        }

    }

    public void addWord(String word, int i, Trie head, int value) {
        if (word.length() == i) {
            head.updateValue(value);
            return;
        }

        if (head.next[word.charAt(i) - 'a'] == null) {
            head.next[word.charAt(i) - 'a'] = new Trie();
        }
        addWord(word, i + 1, head.next[word.charAt(i) - 'a'], value);
    }


    private void update(int startIndex, Trie head, String target, int[] dp, int prevValue) {
    for (int i = startIndex; i < target.length(); i++) {
        char ch = target.charAt(i);
        if (head.next[ch - 'a'] == null) {
            break;
        }
        head = head.next[ch - 'a'];
        if (head.value != Integer.MAX_VALUE) {
            dp[i + 1] = Math.min(dp[i + 1], prevValue + head.value);
        }
    }
}

    public int minimumCost(String target, String[] words, int[] costs) {
        Trie start = new Trie();
        for (int i = 0; i < words.length; i++) {
            addWord(words[i], 0, start, costs[i]);
        }

        int n = target.length();

        int dp[] = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE)
                continue;
            update(i, start, target, dp, dp[i]);
        }

        if (dp[n] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[n];
    }
}