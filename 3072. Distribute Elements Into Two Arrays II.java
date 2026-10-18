import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {

    class SegmentTree {
        int n;
        int array[];

        public SegmentTree(int n) {
            this.n = n;
            array = new int[4 * n];
        }

        private void add(int node, int l, int r, int i) {
            if (l > i || i > r) {
                return;
            }

            if (l == r) {
                array[node]++;
                return;
            }

            int mid = (l + r) / 2;
            add(node * 2, l, mid, i);
            add(node * 2 + 1, mid + 1, r, i);
            array[node] = array[node * 2] + array[node * 2 + 1];
        }

        public void add(int i) {
            add(1, 0, n - 1, i);
        }

        private int getCount(int node, int l, int r, int greaterThen) {
            if (l > greaterThen) {
                return array[node];
            }

            if (r <= greaterThen) {
                return 0;
            }

            int mid = (l + r) / 2;
            int left = getCount(node * 2, l, mid, greaterThen);
            int right = getCount(node * 2 + 1, mid + 1, r, greaterThen);
            return left + right;
        }

        public int getCount(int greaterThen) {
            return getCount(1, 0, n - 1, greaterThen);
        }
    }

    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] sortedNums = new int[n];

        for (int i = 0; i < n; i++) {
            sortedNums[i] = nums[i];
        }

        Arrays.sort(sortedNums);
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
            hm.put(sortedNums[i], i);
        }

        SegmentTree stLeft = new SegmentTree(n);
        SegmentTree stRight = new SegmentTree(n);

        ArrayList<Integer> left = new ArrayList<Integer>();
        ArrayList<Integer> right = new ArrayList<Integer>();

        left.add(nums[0]);
        stLeft.add(hm.get(nums[0]));
        right.add(nums[1]);
        stRight.add(hm.get(nums[1]));

        for (int i = 2; i < n; i++) {
            int index = hm.get(nums[i]);
            if (stLeft.getCount(index) == stRight.getCount(index)) {
                if (left.size() > right.size()) {
                    right.add(nums[i]);
                    stRight.add(index);
                } else {
                    left.add(nums[i]);
                    stLeft.add(index);
                }
            } else if (stLeft.getCount(index) > stRight.getCount(index)) {
                stLeft.add(index);
                left.add(nums[i]);
            } else {
                stRight.add(index);
                right.add(nums[i]);
            }
        }

        int[] ans = new int[n];
        for (int i = 0; i < left.size(); i++) {
            ans[i] = left.get(i);
        }

        for (int i = 0; i < right.size(); i++) {
            ans[i + left.size()] = right.get(i);
        }
        return ans;

    }
}