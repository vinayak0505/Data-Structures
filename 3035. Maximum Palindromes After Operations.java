import java.util.ArrayList;

class Solution {
    public int maxPalindromesAfterOperations(String[] words) {
        int count[] = new int[26];
        ArrayList<Integer> sizes = new ArrayList<>(words.length);

        for (String word : words) {
            sizes.add(word.length());
            for (char c : word.toCharArray()) {
                count[c - 'a']++;
            }
        }

        sizes.sort((a, b) -> Integer.compare(a, b));
        for (int i = 0; i < sizes.size(); i++) {
            if (isValid(sizes.get(i), count) == false) {
                return i;
            }
        }
        return sizes.size();

    }

    private boolean isValid(int size, int[] count) {
        int remove = size / 2;

        for (int i = 0; i < count.length && remove != 0; i++) {
            if (count[i] >= 2) {
                int value = Math.min(count[i] / 2, remove);
                remove -= value;
                count[i] -= 2 * value;
            }
        }

        if (remove != 0)
            return false;

        if (size % 2 == 1) {
            for (int i = 0; i < count.length; i++) {
                if (count[i] % 2 == 1) {
                    count[i]--;
                    return true;
                }
            }
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    count[i]--;
                    return true;
                }
            }
        }
        return true;
    }
}