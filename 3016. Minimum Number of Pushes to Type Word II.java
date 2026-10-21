import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int size = 26;
        Integer[] count = new Integer[size];
        Arrays.fill(count, 0);

        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }

        Arrays.sort(count, (a, b) -> Integer.compare(b, a));

        int ans = 0;
        for (int i = 0; i < size; i++) {
            ans += count[i] * ((i / 8) + 1);
        }
        return ans;
    }
}