class Solution {

    public long maxScore(long n, int[][] edges) {
        long ans = 0;

        for (long i = 1; i <= n - 2; i += 2) {
            ans += (i * (i + 2));
        }

        for (long i = 2; i <= n - 2; i += 2) {
            ans += (i * (i + 2));
        }

        ans += (n * (n - 1));

        if (edges.length == n) {
            ans += 2;
        }
        return ans;
    }
}