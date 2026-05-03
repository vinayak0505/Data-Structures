class Solution {
    int MAX_SIZE = 51;
    private long[] buildCombination(int r){
        long prev = 1;
        long[] ans = new long[MAX_SIZE];
        ans[r] = 1;
        for(int i = r + 1;i<MAX_SIZE;i++){
            prev = (prev * i) / (i - r);
            ans[i] = prev;
        }
        return ans;
    }
    public long nthSmallest(long n, int k) {
        if(n == 0 || k == 0){
            return 0;
        }
        long[] combination = buildCombination(k);
        int i = 0;
        for(;i<MAX_SIZE;i++){
            if(combination[i] >= n) break;
        }
        return (1l << (i - 1)) + nthSmallest(n - combination[i - 1], k - 1);
    }
}