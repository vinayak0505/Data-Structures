import java.util.ArrayList;
import java.util.List;

class Solution {
    class SegmentTree {
        int n;
        int array[];
        int[] peak;
        int[] nums;

        private void build(int node, int l, int r) {
            if (l == r) {
                array[node] = peak[l];
                return;
            }

            int mid = (l + r) / 2;
            build(node * 2, l, mid);
            build(node * 2 + 1, mid + 1, r);
            array[node] = array[node * 2] + array[node * 2 + 1];
        }

        private void makePeak() {
            peak = new int[n];
            for (int i = 1; i < n - 1; i++) {
                peak[i] = (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) ? 1 : 0;
            }
        }

        public SegmentTree(int[] nums) {
            n = nums.length;
            this.nums = nums;
            makePeak();
            array = new int[4 * n];
            build(1, 0, n - 1);
        }

        public void update(int i, int num) {
            if (i == 0) {
                nums[i] = num;
                peak[i + 1] = (nums[i + 1] > nums[i] && nums[i + 1] > nums[i + 2]) ? 1 : 0;
                update(1, 0, n - 1, i + 1);
            } else if (i == n - 1) {
                nums[i] = num;
                peak[i - 1] = (nums[i - 1] > nums[i - 2] && nums[i - 1] > nums[i]) ? 1 : 0;
                update(1, 0, n - 1, i - 1);
            } else {
                nums[i] = num;
                peak[i] = (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) ? 1 : 0;
                if (i + 2 != n) {
                    peak[i + 1] = (nums[i + 1] > nums[i] && nums[i + 1] > nums[i + 2]) ? 1 : 0;
                }
                if (i - 2 != -1) {
                    peak[i - 1] = (nums[i - 1] > nums[i - 2] && nums[i - 1] > nums[i]) ? 1 : 0;
                }

                update(1, 0, n - 1, i + 1);
                update(1, 0, n - 1, i);
                update(1, 0, n - 1, i - 1);
            }
        }

        private void update(int node, int l, int r, int index) {
            if (index > r || index < l) {
                return;
            }
            if (l == r) {
                array[node] = peak[l];
                return;
            }
            int mid = (l + r) / 2;
            update(node * 2, l, mid, index);
            update(node * 2 + 1, mid + 1, r, index);
            array[node] = array[node * 2] + array[node * 2 + 1];
        }

        public int get(int l, int r) {
            int value = get(1, 0, n - 1, l, r);
            if (peak[l] == 1) {
                value--;
            }
            if (l != r && peak[r] == 1) {
                value--;
            }
            return value;
        }

        private int get(int node, int l, int r, int left, int right) {
            if (left <= l && r <= right) {
                return array[node];
            }
            if (left > r || right < l) {
                return 0;
            }
            int mid = (l + r) / 2;
            return get(node * 2, l, mid, left, right) + get(node * 2 + 1, mid + 1, r, left, right);
        }
    }

    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        SegmentTree st = new SegmentTree(nums);

        ArrayList<Integer> ans = new ArrayList<>();

        for (int[] query : queries) {
            if (query[0] == 1) {
                ans.add(st.get(query[1], query[2]));
            } else {
                st.update(query[1], query[2]);
            }
        }
        return ans;
    }
}