class Solution {
    private long mod = (long)1e9 + 7;
    long lrSum = -1;
    int diff = 0;
    private void getLRSum(int l, int r){
        diff = (r - l + 1);

        long ans = 0;
        while(l <= r){
            ans += l++;
        }
        lrSum = ans % mod;
    }

    private long solve(int l, int r, int k){
        if(k == 1) return lrSum;
        long child = solve(l,r, k - 1);
        long ans = (child * diff) % mod;
        long sum = lrSum;
        while(l-- > 0){
            sum = (sum * 10) % mod;
        }
        return (sum + ans) % mod;
    }

    public int sumOfNumbers(int l, int r, int k) {
        getLRSum(l, r);
        return (int)(solve(l, r, k) % mod);
    }
}