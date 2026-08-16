import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    long ans;

    private void check(StringBuilder st, long n, int odd, int size) {
        String original;
        if (odd != -1) {
            original = st.toString() + odd;
        } else {
            original = st.toString();
        }

        original += new StringBuilder(st).reverse().toString();
        long originalLong = Long.valueOf(original);
        if (originalLong <= n)
            return;
        if (originalLong < ans) {
            ans = originalLong;
        }
    }

    private void populateAns(HashMap<Integer, Integer> list, StringBuilder st, int size, long n, int odd, int i) {
        if (i == size / 2) {
            check(st, n, odd, size);
            return;
        }
        for (int key : list.keySet()) {
            int value = list.get(key);
            if (value == 0)
                continue;

            if (st.length() > i) {
                st.setCharAt(i, (char) ('0' + key));
            } else {
                st.append(key);
            }
            list.put(key, value - 1);

            populateAns(list, st, size, n, odd, i + 1);

            list.put(key, value);
        }
    }

    private void pick(ArrayList<Integer> list, int curSize, int size, long n, int odd, int i) {
        if (curSize > size)
            return;
        if (i == 10) {
            if (curSize == size) {
                HashMap<Integer, Integer> numToCountMap = new HashMap<>();
                for (int value : list) {
                    numToCountMap.put(value, value / 2);
                }
                populateAns(numToCountMap, new StringBuilder(size), size, n, odd, 0);
            }
            return;
        }
        if (i % 2 == 0) {
            list.add(i);
            pick(list, curSize + i, size, n, odd, i + 1);
            list.removeLast();
        } else {
            if (odd == -1) {
                list.add(i);
                pick(list, curSize + i, size, n, i, i + 1);
                list.removeLast();
            }
        }
        pick(list, curSize, size, n, odd, i + 1);
    }

    private void helper(int size, long n) {
        pick(new ArrayList<>(), 0, size, n, -1, 1);
    }

    public long specialPalindrome(long n) {
        int size = String.valueOf(n).length();
        ans = 2666888888886662l;
        helper(size + 1, n);
        helper(size, n);
        return ans;
    }
}