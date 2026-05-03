class Solution {
    int mod = (int)1e9 + 7;
    public int sumOfNumbers(int l, int r, int k) {
        long sum = 0;
        for (int i = l; i <= r; i++) sum += i;
        long diff = r - l + 1;
        long times = power(diff, k - 1);
        long singleDigitContribution = (times * sum) % mod;
        long mul = power(10, k);
        long inverse = power(9,  mod - 2);
        return (int)((singleDigitContribution * ((mul - 1 + mod) % mod * inverse % mod)) % mod);
    }

    private long power(long a, long b){
        long res = 1;
        while(b > 0){
            if((b & 1) == 1) res = (res * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return res;
    }
}