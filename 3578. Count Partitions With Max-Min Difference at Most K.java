class Solution {
    int M = (int) 1e9 + 7;
    int n;
    int dp[];

    class SegmentTreeMin {
        int n;
        int array[];

        private void build(int node, int left, int right, int[] nums) {
            if (left == right) {
                array[node] = nums[left];
                return;
            }

            int mid = (left + right) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);
            array[node] = Math.min(array[node * 2], array[node * 2 + 1]);
        }

        public SegmentTreeMin(int[] nums) {
            n = nums.length;
            array = new int[4 * n];

            build(1, 0, n - 1, nums);
        }

        private int getMin(int node, int l, int r, int left, int right) {
            if (left <= l && r <= right) {
                return array[node];
            }

            if (r < left || right < l) {
                return Integer.MAX_VALUE;
            }

            int mid = (l + r) / 2;

            return Math.min(
                    getMin(node * 2, l, mid, left, right),
                    getMin(node * 2 + 1, mid + 1, r, left, right));
        }

        public int getMin(int left, int right) {
            return getMin(1, 0, n - 1, left, right);
        }
    }

    class SegmentTreeMax {
        int n;
        int array[];

        private void build(int node, int left, int right, int[] nums) {
            if (left == right) {
                array[node] = nums[left];
                return;
            }

            int mid = (left + right) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);
            array[node] = Math.max(array[node * 2], array[node * 2 + 1]);
        }

        public SegmentTreeMax(int[] nums) {
            n = nums.length;
            array = new int[4 * n];

            build(1, 0, n - 1, nums);
        }

        private int getMax(int node, int l, int r, int left, int right) {
            if (left <= l && r <= right) {
                return array[node];
            }

            if (r < left || right < l) {
                return Integer.MIN_VALUE;
            }

            int mid = (l + r) / 2;

            return Math.max(
                    getMax(node * 2, l, mid, left, right),
                    getMax(node * 2 + 1, mid + 1, r, left, right));
        }

        public int getMax(int left, int right) {
            return getMax(1, 0, n - 1, left, right);
        }
    }

    public int countPartitions(int[] nums, int k) {
        n = nums.length;
        dp = new int[n + 1];
        long[] dpSum = new long[n + 2];
        dpSum[n] = dp[n] = 1;

        SegmentTreeMin stmin = new SegmentTreeMin(nums);
        SegmentTreeMax stmax = new SegmentTreeMax(nums);
        int max_j = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int l = i, r = max_j;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (stmax.getMax(i, mid) - stmin.getMin(i, mid) > k) {
                    r = mid - 1;
                } else {
                    max_j = mid;
                    l = mid + 1;
                }
            }

            long ans = dpSum[i + 1] - dpSum[max_j + 2];
            dp[i] = (int) ((ans % M + M) % M);

            dpSum[i] = (dpSum[i + 1] + dp[i]) % M;

        }
        return dp[0];
    }
}