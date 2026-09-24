class Solution {
    int mod = (int) 1e9 + 7;

    public int calculateCombination(int n, int r) {

        if (r < 0 || r > n) {
            return 0;
        }
        if (r == 0 || r == n) {
            return 1;
        }

        if (r > n - r) {
            r = n - r;
        }

        long result = 1;
        for (int i = 1; i <= r; i++) {
            result = (result * (n - r + i)) % mod;
            result = (result * (power(i, mod - 2))) % mod;
        }

        return (int) result;
    }

    private int power(int a, int b) {
        if (b == 1)
            return a;
        if (b == 0)
            return 1;
        long value = power((int) (((long) a * a) % mod), b / 2);
        if (b % 2 == 1) {
            value = (value * a) % mod;
        }
        return (int) value;
    }

    public int countGoodArrays(int n, int m, int k) {
        long ans = m;
        ans = (ans * power(m - 1, n - k - 1)) % mod;
        ans = (ans * calculateCombination(n - 1, k)) % mod;
        return (int) ans;
    }
}