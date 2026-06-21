import java.util.Arrays;
import java.util.HashMap;

class Solution {

    private int findCont(String s, char c) {
        int ans = 0;
        int tempans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                tempans++;
            } else {
                ans = Math.max(ans, tempans);
                tempans = 0;
            }
        }
        return Math.max(ans, tempans);
    }

    private int findCont(char[] c, char x, char y) {
        int n = c.length, max_len = 0;
        int[] first = new int[2 * n + 1]; 
        Arrays.fill(first, -2); 

        int clear_idx = -1, diff = n; 
        first[diff] = -1; 

        for (int i = 0; i < n; i++) {
            if (c[i] != x && c[i] != y) { 
                clear_idx = i; 
                diff = n; 
                first[diff] = clear_idx; 
            } else { 
                if (c[i] == x)
                    diff++;
                else
                    diff--;

                if (first[diff] < clear_idx) { 
                    first[diff] = i;
                } else { 
                    max_len = Math.max(max_len, i - first[diff]);
                }
            }
        }
        return max_len;
    }

    private int findCont(String s) {
        HashMap<Long, Integer> mp = new HashMap<>(100005);
        int a = 0;
        int b = 0;
        int c = 0;
        int ans = 0;
        mp.put(0l, -1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                a++;
            } else if (s.charAt(i) == 'b') {
                b++;
            } else {
                c++;
            }
            long key = ((long) (a - b) << (17)) + (a - c);
            if (mp.containsKey(key)) {
                ans = Math.max(ans, i - mp.get(key));

            } else {
                mp.put(key, i);
            }
        }
        return ans;

    }

    public int longestBalanced(String s) {
        int a = findCont(s, 'a');
        int b = findCont(s, 'b');
        int c = findCont(s, 'c');

        char[] cs = s.toCharArray();

        int ab = findCont(cs, 'a', 'b');
        int bc = findCont(cs, 'c', 'b');
        int ca = findCont(cs, 'a', 'c');

        int abc = findCont(s);

        return Arrays.stream(new int[] { a, b, c, ab, bc, ca, abc }).max().getAsInt();

    }
}