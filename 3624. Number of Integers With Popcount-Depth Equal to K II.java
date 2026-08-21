class Solution {
    private static final int MAX_DEPTH = 6;
    private int[][] tree;
    private int[] numsDepth;
    private int n;

    private static class PopCount {
        private final int[] dp = new int[65];

        public PopCount() {
            dp[1] = 0;
            for (int i = 2; i < 65; i++) {
                dp[i] = dp[Long.bitCount(i)] + 1;
            }
        }

        public int getDepth(long val) {
            if (val <= 1) return 0;
            return 1 + dp[Long.bitCount(val)];
        }
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node][numsDepth[start]] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        
        for (int d = 0; d < MAX_DEPTH; d++) {
            tree[node][d] = tree[2 * node][d] + tree[2 * node + 1][d];
        }
    }

    private void update(int node, int start, int end, int idx, int oldDepth, int newDepth) {
        if (start == end) {
            tree[node][oldDepth] = 0;
            tree[node][newDepth] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, oldDepth, newDepth);
        } else {
            update(2 * node + 1, mid + 1, end, idx, oldDepth, newDepth);
        }

        for (int d = 0; d < MAX_DEPTH; d++) {
            tree[node][d] = tree[2 * node][d] + tree[2 * node + 1][d];
        }
    }

    private int query(int node, int start, int end, int l, int r, int targetDepth) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return targetDepth < MAX_DEPTH ? tree[node][targetDepth] : 0;
        }
        int mid = start + (end - start) / 2;
        int leftCount = query(2 * node, start, mid, l, r, targetDepth);
        int rightCount = query(2 * node + 1, mid + 1, end, l, r, targetDepth);
        return leftCount + rightCount;
    }

    public int[] popcountDepth(long[] nums, long[][] queries) {
        n = nums.length;
        PopCount pc = new PopCount();
        
        numsDepth = new int[n];
        for (int i = 0; i < n; i++) {
            numsDepth[i] = pc.getDepth(nums[i]);
        }

        tree = new int[4 * n][MAX_DEPTH];
        build(1, 0, n - 1);

        int queryCount = 0;
        for (long[] q : queries) {
            if (q[0] == 1) queryCount++;
        }

        int[] result = new int[queryCount];
        int resIdx = 0;

        for (long[] q : queries) {
            int type = (int) q[0];
            if (type == 1) {
                int l = (int) q[1];
                int r = (int) q[2];
                int k = (int) q[3];
                result[resIdx++] = query(1, 0, n - 1, l, r, k);
            } else {
                int idx = (int) q[1];
                long val = q[2];
                int oldDepth = numsDepth[idx];
                int newDepth = pc.getDepth(val);
                numsDepth[idx] = newDepth;
                update(1, 0, n - 1, idx, oldDepth, newDepth);
            }
        }

        return result;
    }
}