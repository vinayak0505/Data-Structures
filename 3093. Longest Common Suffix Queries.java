class Solution {

    class Node {
        Node child[] = new Node[26];
        int index = -1;
        int size = Integer.MAX_VALUE;

        public void update(int newIndex, int newSize) {
            if (newSize < size) {
                size = newSize;
                index = newIndex;
            }
        }
    }

    class Trie {
        Node root = new Node();

        public void addWord(String word, int index) {
            Node cur = root;
            cur.update(index, word.length());
            for (int i = word.length() - 1; i >= 0; i--) {
                int ci = word.charAt(i) - 'a';
                if (cur.child[ci] == null) {
                    cur.child[ci] = new Node();
                }
                cur.child[ci].update(index, word.length());
                cur = cur.child[ci];
            }
        }

        public int findWord(String word) {
            Node cur = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                int ci = word.charAt(i) - 'a';
                if (cur.child[ci] == null) {
                    return cur.index;
                }
                cur = cur.child[ci];
            }

            return cur.index;
        }
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie trie = new Trie();
        for (int i = 0; i < wordsContainer.length; i++) {
            trie.addWord(wordsContainer[i], i);
        }
        int n = wordsQuery.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = trie.findWord(wordsQuery[i]);
        }
        return ans;
    }
}