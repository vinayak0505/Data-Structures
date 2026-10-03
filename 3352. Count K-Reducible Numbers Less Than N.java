class Solution {

    boolean dp[];
    int mod = (int) 1e9 + 7;

    private int reduce(int n) {
        int count = 0;
        for (int i = 0; i < 11; i++) {
            if (((1 << i) & n) != 0)
                count++;
        }
        return count;
    }

    private void udpateDp(int n, int k) {
        dp = new boolean[n];

        for (int i = 1; i < n; i++) {
            int tempk = k;
            int num = i;
            while (tempk > 0 && num != 1) {
                tempk--;
                num = reduce(num);
            }
            if (num == 1) {
                dp[i] = true;
            }
        }
    }

    Integer newdp[][][];

    private int helper(int i, int ceil, String s, int count) {
        int n = s.length();
        if (i == n) {
            if (dp[count] && ceil == 0)
                return 1;
            return 0;
        }
        if (newdp[i][count][ceil] != null) {
            return newdp[i][count][ceil];
        }
        int ans = 0;

        if (ceil == 1) {
            if (s.charAt(i) == '1') {
                ans += helper(i + 1, 1, s, count + 1);
                ans %= mod;
                ans += helper(i + 1, 0, s, count);
                ans %= mod;
            } else {
                ans += helper(i + 1, 1, s, count);
                ans %= mod;
            }
        } else {
            ans += helper(i + 1, 0, s, count + 1);
            ans %= mod;
            ans += helper(i + 1, 0, s, count);
            ans %= mod;
        }

        return newdp[i][count][ceil] = ans;
    }

    public int countKReducibleNumbers(String s, int k) {
        int size = s.length() + 1;
        udpateDp(size, k - 1);
        newdp = new Integer[size][size][2];
        return helper(0, 1, s, 0);

    }
}