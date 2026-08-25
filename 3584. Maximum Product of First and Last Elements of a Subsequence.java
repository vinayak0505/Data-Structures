class Solution {

    class SegmentedTreeSmallest {
        int tree[];
        int n;

        private void build(int node, int left, int right, int[] nums) {
            if (left == right) {
                tree[node] = nums[left];
                return;
            }

            int mid = (left + right) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);
            tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
        }

        public SegmentedTreeSmallest(int[] nums) {
            n = nums.length;
            tree = new int[n * 4];
            build(1, 0, n - 1, nums);
        }

        private int find(int node, int nodel, int noder, int left, int right) {
            if (left > noder || right < nodel) {
                return Integer.MAX_VALUE;
            }
            if (left <= nodel && noder <= right) {
                return tree[node];
            }

            int mid = (nodel + noder) / 2;
            int l = find(node * 2, nodel, mid, left, right);
            int r = find(node * 2 + 1, mid + 1, noder, left, right);
            return Math.min(l, r);
        }

        public int find(int left, int right) {
            return find(1, 0, n - 1, left, right);
        }
    }

    class SegmentedTreeLargest {
        int tree[];
        int n;

        private void build(int node, int left, int right, int[] nums) {
            if (left == right) {
                tree[node] = nums[left];
                return;
            }

            int mid = (left + right) / 2;
            build(node * 2, left, mid, nums);
            build(node * 2 + 1, mid + 1, right, nums);
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        public SegmentedTreeLargest(int[] nums) {
            n = nums.length;
            tree = new int[n * 4];
            build(1, 0, n - 1, nums);
        }

        private int find(int node, int nodel, int noder, int left, int right) {
            if (left > noder || right < nodel) {
                return Integer.MIN_VALUE;
            }
            if (left <= nodel && noder <= right) {
                return tree[node];
            }

            int mid = (nodel + noder) / 2;
            int l = find(node * 2, nodel, mid, left, right);
            int r = find(node * 2 + 1, mid + 1, noder, left, right);
            return Math.max(l, r);
        }

        public int find(int left, int right) {
            return find(1, 0, n - 1, left, right);
        }
    }

    public long maximumProduct(int[] nums, int m) {
        long ans = Long.MIN_VALUE;

        SegmentedTreeLargest stl = new SegmentedTreeLargest(nums);
        SegmentedTreeSmallest sts = new SegmentedTreeSmallest(nums);
        int n = nums.length;
        for (int i = 0; i <= n - m; i++) {
            long largest = (long) nums[i] * stl.find(i + m - 1, n - 1);
            long smallest = (long) nums[i] * sts.find(i + m - 1, n - 1);

            ans = Math.max(ans, Math.max(largest, smallest));
        }
        return ans;
    }
}