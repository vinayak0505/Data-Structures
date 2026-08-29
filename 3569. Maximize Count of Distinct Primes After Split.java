import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

class Solution {

    private boolean isPrime[];

    private void createPrimeArray(int max) {
        isPrime = new boolean[max + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i <= max; i++) {
            if (isPrime[i] == false)
                continue;
            for (int j = i + i; j <= max; j += i) {
                isPrime[j] = false;
            }
        }

    }

    class SegmentTree {
        // prime number -> index[]
        HashMap<Integer, TreeSet<Integer>> primeNumberToIndexesMap;
        private int[] primeSumCountTree;
        private int[] lazyUpdateTree;
        int n;
        int[] nums;

        public SegmentTree(int nums[]) {
            build(nums);
        }

        private void build(int[] nums) {
            n = nums.length;
            primeNumberToIndexesMap = new HashMap<>();
            primeSumCountTree = new int[n * 4];
            lazyUpdateTree = new int[n * 4];
            this.nums = nums;
        }

        public void add(int num, int i) {
            nums[i] = num;
            if (isPrime[num] == false)
                return;

            primeNumberToIndexesMap.putIfAbsent(num, new TreeSet<Integer>());
            TreeSet<Integer> st = primeNumberToIndexesMap.get(num);
            if (st.size() == 0) {
                st.add(i);
                update(0, n - 2, 1); // checked
            } else if (st.getFirst() > i) {
                update(i, st.getFirst() - 1, 1);
                st.add(i);
            } else if (st.getLast() < i) {
                update(st.getLast(), i - 1, 1); // checked
                st.add(i);
            } else {
                st.add(i); // checked
            }
        }

        public void remove(int i) {
            if (isPrime[nums[i]] == false)
                return;
            TreeSet<Integer> st = primeNumberToIndexesMap.get(nums[i]);
            if (st.size() == 1) {
                st.remove(i);
                update(0, n - 2, -1);
            } else if (st.getFirst() == i) {
                st.removeFirst();
                update(i, st.getFirst() - 1, -1);
            } else if (st.getLast() == i) {
                st.removeLast();
                update(st.getLast(), i - 1, -1);
            } else {
                st.remove(i);
            }

        }

        private void update(int node, int left, int right, int from, int to, int diff) {
            
            int nextLeft = node * 2;
            int nextRight = node * 2 + 1;

            if (to < left || right < from) {
            } else if (from <= left && right <= to) {
                lazyUpdateTree[node] += diff;
            } else {
                int mid = (left + right) / 2;
                update(nextLeft, left, mid, from, to, diff);
                update(nextRight, mid + 1, right, from, to, diff);
                primeSumCountTree[node] = Math.max(primeSumCountTree[nextLeft], primeSumCountTree[nextRight]);
            }

            primeSumCountTree[node] += lazyUpdateTree[node];
            if(left != right){
                lazyUpdateTree[nextLeft] += lazyUpdateTree[node];
                lazyUpdateTree[nextRight] += lazyUpdateTree[node];
            }
            lazyUpdateTree[node] = 0;

        }

        private void update(int from, int to, int diff) {
            update(1, 0, n - 1, from, to, diff);
        }

        public int getMaxSplit() {
            return primeSumCountTree[1] + lazyUpdateTree[1];
        }
    }

    public int[] maximumCount(int[] nums, int[][] queries) {
        int max = (int) 1e5;
        int n = nums.length;

        createPrimeArray(max);
        SegmentTree st = new SegmentTree(nums);

        for (int i = 0; i < n; i++) {
            st.add(nums[i], i);
        }

        int ans[] = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int num = queries[i][1];
            int idx = queries[i][0];

            st.remove(idx);
            st.add(num, idx);

            ans[i] = st.getMaxSplit();
        }

        return ans;

    }
}