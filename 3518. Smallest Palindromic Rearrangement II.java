class Solution {

    public int remainingPos(char[] count, int k) {
        long arrangements = 1;
        int used = 0;
        for (char c : count) {
            if (c > 0) {
                int remaining = used + c;
                arrangements = arrangements * binomial(remaining, c, k);
                if (arrangements > k) {
                    return Integer.MAX_VALUE;
                }
                used = remaining;
            }
        }
        return (int) arrangements;
    }

    private long binomial(int n, int r, int k) {
        long result = 1;
        for (int i = 1; i <= r; i++) {
            result = (result * (n - r + i)) / i;
            if(result >= k){
                return k;
            }
        }
        return result;
    }

    public String smallestPalindrome(String s, int k) {
        if (s.length() == 1) {
            if (k == 1) {
                return s;
            }
            return "";
        }
        char[] count = new char[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        char odd = '\n';
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                odd = (char) ('a' + i);
            }
            count[i] /= 2;
        }

        int n = s.length() / 2;

        if (k > remainingPos(count, k))
            return "";

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (count[j] == 0)
                    continue;
                count[j]--;
                int value = remainingPos(count, k);
                if (value >= k) {
                    ans.append((char) ('a' + j));
                    break;
                } else {
                    k -= value;
                    count[j]++;
                }
            }
        }

        String finalans = ans.toString();
        if (odd != '\n') {
            return finalans + odd + ans.reverse().toString();
        }
        return finalans + ans.reverse().toString();

    }
}