import java.util.Arrays;

class Solution {

    class SegmentTree {
        int[] tree;
        int n;

        private void build(int node, int left, int right, int[] array) {
            if (left == right) {
                tree[node] = array[left];
                return;
            }

            int mid = (left + right) / 2;

            build(node * 2, left, mid, array);
            build(node * 2 + 1, mid + 1, right, array);

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        public SegmentTree(int[] array) {
            n = array.length;
            tree = new int[4 * n];
            build(1, 0, n - 1, array);
        }

        private boolean update(int node, int left, int right, int value) {
            if (tree[node] < value) {
                return false;
            }

            if (left == right) {
                tree[node] = -1;
                return true;
            }

            int mid = (left + right) / 2;
            if (update(node * 2, left, mid, value) == false) {
                update(node * 2 + 1, mid + 1, right, value);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
            return true;
        }

        public boolean findAndUpdate(int value) {
            return update(1, 0, n - 1, value);
        }
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        SegmentTree st = new SegmentTree(baskets);
        int noOfFruitsDidNotFit = 0;
        for (int fruit : fruits) {
            if (st.findAndUpdate(fruit) == false) {
                noOfFruitsDidNotFit++;
            }
        }
        return noOfFruitsDidNotFit;
    }
}