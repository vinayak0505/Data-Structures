class Solution {
    private static final int MOD = (int) 1e9 + 7;

    class SegmentTree {
        int[] nums;
        int n;
        // contains all, without first, without last, without both
        long[][] array;

        private long[] combineArray(long[] leftArray, long[] rightArray) {
            return new long[] {
                    Math.max(leftArray[2] + rightArray[0], leftArray[0] + rightArray[1]) % MOD,
                    Math.max(leftArray[3] + rightArray[0], leftArray[1] + rightArray[1]) % MOD,
                    Math.max(leftArray[2] + rightArray[2], leftArray[0] + rightArray[3]) % MOD,
                    Math.max(leftArray[1] + rightArray[3], leftArray[3] + rightArray[2]) % MOD,
            };
        }

        private void build(int node, int left, int right) {
            if (left == right) {
                array[node] = new long[] { Math.max(0, nums[left]), 0, 0, 0 };
                return;
            }
            int mid = (left + right) / 2;
            build(node * 2, left, mid);
            build(node * 2 + 1, mid + 1, right);

            array[node] = combineArray(array[node * 2], array[node * 2 + 1]);
        }

        public SegmentTree(int[] nums) {
            this.nums = nums;
            n = nums.length;

            array = new long[4 * n][4];
            build(1, 0, n - 1);
        }

        private void update(int node, int left, int right, int index) {
            if (left > index || right < index)
                return;
            if (left == right) {
                array[node] = new long[] { Math.max(0, nums[left]), 0, 0, 0 };
                return;
            }
            int mid = (left + right) / 2;
            update(node * 2, left, mid, index);
            update(node * 2 + 1, mid + 1, right, index);

            array[node] = combineArray(array[node * 2], array[node * 2 + 1]);
        }

        public void update(int index, int value) {
            nums[index] = value;
            update(1, 0, n - 1, index);
        }

        private long[] getValue(int node, int left, int right, int start, int end) {
            if (start <= left && right <= end) {
                return array[node];
            }
            if (right < start || end < left) {
                return new long[] { 0, 0, 0, 0 };
            }
            if (left == right) {
                return array[node];
            }

            int mid = (left + right) / 2;
            long leftArray[] = getValue(node * 2, left, mid, start, end);
            long rightArray[] = getValue(node * 2 + 1, mid + 1, right, start, end);
            return combineArray(leftArray, rightArray);
        }

        public int getValue(int start, int end) {
            return (int)getValue(1, 0, n - 1, start, end)[0];
        }
    }

    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        SegmentTree st = new SegmentTree(nums);
        long ans = 0;

        for (int[] query : queries) {
            st.update(query[0], query[1]);
            ans = (ans + st.getValue(0, nums.length - 1)) % MOD;
        }
        return (int) ans;
    }
}