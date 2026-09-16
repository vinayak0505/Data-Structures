class Solution {

    private boolean possible(int[] points, long mid, long m) {
        long prev = 0;
        long nodeOn = -1;
        for (int i = 0; i < points.length; i++) {
            long times = Math.ceilDiv(mid, points[i]) - prev;
            if (times > 0) {
                m -= i - 1 - nodeOn;
                prev = times - 1;
                m -= times + prev;
                nodeOn = i;
            } else {
                prev = 0;
            }
            if (m < 0)
                return false;

        }
        return true;
    }

    public long maxScore(int[] points, int m) {
        long max = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            max = Math.min(points[i], max);
        }
        max *= m;
        long start = 0;
        long ans = 0;

        while (start <= max) {
            long mid = (start + max) / 2;
            if (possible(points, mid, m)) {
                ans = mid;
                start = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return ans;

    }
}