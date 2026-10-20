import java.util.HashMap;

class Solution {

    class Node {
        HashMap<Integer, Node> node = new HashMap<>();
        int endCount = 0;
    }

    class Trie {
        Node start = new Node();

        public void add(String word) {
            int n = word.length();
            Node cur = start;
            for (int i = 0; i < n; i++) {
                int key = (word.charAt(i) - 'a') * 26 + (word.charAt(n - 1 - i) - 'a');
                if (cur.node.get(key) == null) {
                    cur.node.put(key, new Node());
                }
                cur = cur.node.get(key);
            }
            cur.endCount++;
        }

        public int getCount(String word) {
            int ans = 0;
            int n = word.length();
            Node cur = start;
            for (int i = 0; i < n; i++) {
                int key = (word.charAt(i) - 'a') * 26 + (word.charAt(n - 1 - i) - 'a');
                if (cur.node.get(key) == null) {
                    return ans;
                }
                cur = cur.node.get(key);
                ans += cur.endCount;
            }
            return ans;
        }
    }

    public long countPrefixSuffixPairs(String[] words) {
        long ans = 0;
        Trie trie = new Trie();
        for (String word : words) {
            ans += trie.getCount(word);
            trie.add(word);
        }
        return ans;
    }
}