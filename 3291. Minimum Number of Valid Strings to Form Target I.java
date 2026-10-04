import java.util.Arrays;

class Solution {

    class Trie {
        class Node {
            Node[] array;

            Node() {
                array = new Node[26];
            }
        }

        Node start = new Node();

        private void add(String word) {
            Node node = start;
            for (char c : word.toCharArray()) {
                if (node.array[c - 'a'] == null) {
                    node.array[c - 'a'] = new Node();
                }
                node = node.array[c - 'a'];
            }
        }

        public Trie(String[] words) {
            for (String word : words) {
                add(word);
            }
        }

        public int matchUpto(String target, int i) {
            Node node = start;
            for (; i < target.length(); i++) {
                if (node.array[target.charAt(i) - 'a'] == null)
                    return i;
                node = node.array[target.charAt(i) - 'a'];
            }
            return i;
        }
    }

    public int minValidStrings(String[] words, String target) {
        Trie trie = new Trie(words);
        int n = target.length();
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            int prev = i == 0 ? 0 : ans[i - 1];
            if (prev == Integer.MAX_VALUE)
                return -1;
            int till = trie.matchUpto(target, i);
            for (int j = 0; j < till; j++) {
                ans[j] = Math.min(ans[j], prev + 1);
            }
        }

        if (ans[n - 1] == Integer.MAX_VALUE)
            return -1;

        return ans[n - 1];
    }
}