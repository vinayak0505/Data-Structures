import java.util.PriorityQueue;

class Solution {

    class SegmentTree {
        int[] arrayMax;
        int[] arrayMin;
        int[] nums;
        int n;

        private void build(int node, int left, int right) {
            if (left == right) {
                arrayMax[node] = nums[left];
                arrayMin[node] = nums[left];
                return;
            }
            int m = (left + right) / 2;
            build(node * 2, left, m);
            build(node * 2 + 1, m + 1, right);
            arrayMax[node] = Math.max(arrayMax[node * 2], arrayMax[node * 2 + 1]);
            arrayMin[node] = Math.min(arrayMin[node * 2], arrayMin[node * 2 + 1]);
        }

        public SegmentTree(int[] nums) {
            n = nums.length;
            this.nums = nums;
            arrayMax = new int[4 * n];
            arrayMin = new int[4 * n];
            build(1, 0, n - 1);
        }

        private int findMax(int node, int l, int r, int start, int end) {
            if (end < l || r < start) {
                return -1;
            }
            if (l <= start && end <= r) {
                return arrayMax[node];
            }
            if (start == end) {
                return arrayMax[node];
            }

            int m = (start + end) / 2;
            int left = findMax(node * 2, l, r, start, m);
            int right = findMax(node * 2 + 1, l, r, m + 1, end);
            if (left == -1) {
                return right;
            }
            if (right == -1) {
                return left;
            }
            return Math.max(left, right);
        }

        public int findMax(int l, int r) {
            return findMax(1, l, r, 0, n - 1);
        }

        private int findMin(int node, int l, int r, int start, int end) {

            if (end < l || r < start) {
                return -1;
            }
            if (l <= start && end <= r) {
                return arrayMin[node];
            }
            if (start == end) {
                return nums[start];
            }

            int m = (start + end) / 2;
            int left = findMin(node * 2, l, r, start, m);
            int right = findMin(node * 2 + 1, l, r, m + 1, end);
            if (left == -1) {
                return right;
            }
            if (right == -1) {
                return left;
            }
            return Math.min(left, right);
        }

        public int findMin(int l, int r) {
            return findMin(1, l, r, 0, n - 1);
        }
    }

    class Pair implements Comparable<Pair> {
        int value;
        int l;
        int r;

        Pair(int value, int l, int r) {
            this.value = value;
            this.l = l;
            this.r = r;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(other.value, this.value);
        }
    }

    public long maxTotalValue(int[] nums, int k) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        SegmentTree tree = new SegmentTree(nums);
        for (int l = nums.length - 1; l >= 0; l--) {
            min = Math.min(min, nums[l]);
            max = Math.max(max, nums[l]);
            maxHeap.offer(new Pair(max - min, l, nums.length - 1));
        }
        long ans = 0;
        while (maxHeap.size() > 0 && k > 0) {
            Pair poll = maxHeap.poll();
            k--;
            ans += poll.value;
            if (poll.r != 0) {
                maxHeap.offer(new Pair(tree.findMax(poll.l, poll.r - 1) - tree.findMin(poll.l, poll.r - 1), poll.l,
                        poll.r - 1));
            }
        }
        return ans;
    }
}