class Solution {

    private long getCount(int n, int[] order, int mid) {
        long totalAns = (long) n * (n + 1) / 2;
        boolean count[] = new boolean[n];
        for (int i = 0; i < mid; i++) {
            count[order[i]] = true;
        }

        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (count[i]) {
                int newn = i - 1 - prev;
                totalAns -= ((long)newn * (newn + 1)) / 2;
                prev = i;
            }
        }
        int newn = n - 1 - prev;
        totalAns -= ((long)newn * (newn + 1)) / 2;
        return totalAns;
    }

    public int minTime(String s, int[] order, long k) {
        int ans = -1;
        int n = s.length();
        int low = 0, high = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (getCount(n, order, mid) >= k) {
                ans = mid - 1;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}