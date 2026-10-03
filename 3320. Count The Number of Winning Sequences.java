class Solution {

    // index. count. prev
    Integer[][][] memo;
    int mod = (int) 1e9 + 7;

    private int valid(String s, int i, int prev, int count) {
        int n = s.length();
        if (i == n) {
            return count > 0 ? 1 : 0;
        }
        int ans = 0;
        if (prev != ' ' && memo[i][count + n][prev] != null) {
            return memo[i][count + n][prev];
        }

        switch (s.charAt(i)) {
            case 'F':
                if (prev != 0) {
                    ans = (ans + valid(s, i + 1, 0, count - 1)) % mod;
                }
                if (prev != 1) {
                    ans = (ans + valid(s, i + 1, 1, count)) % mod;
                }
                if (prev != 2) {
                    ans = (ans + valid(s, i + 1, 2, count + 1)) % mod;
                }
                break;
            case 'W':
                if (prev != 0) {
                    ans = (ans + valid(s, i + 1, 0, count + 1)) % mod;
                }
                if (prev != 1) {
                    ans = (ans + valid(s, i + 1, 1, count - 1)) % mod;
                }
                if (prev != 2) {
                    ans = (ans + valid(s, i + 1, 2, count)) % mod;
                }
                break;
            case 'E':
                if (prev != 0) {
                    ans = (ans + valid(s, i + 1, 0, count)) % mod;
                }
                if (prev != 1) {
                    ans = (ans + valid(s, i + 1, 1, count + 1)) % mod;
                }
                if (prev != 2) {
                    ans = (ans + valid(s, i + 1, 2, count - 1)) % mod;
                }
                break;

            default:
                break;
        }
        if (prev != ' ') {
            return memo[i][count + n][prev] = ans;
        }
        return ans;

    }

    public int countWinningSequences(String s) {
        memo = new Integer[s.length()][s.length() * 2 + 1][3];
        return valid(s, 0, ' ', 0);
    }
}