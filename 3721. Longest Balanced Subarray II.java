import java.util.Arrays;

class SegmentTree {

    class Node {
        public int max;
        public int min;
        public int lazy;

        public Node() {
            max = 0;
            min = 0;
            lazy = 0;
        }
    }

    Node[] array;
    int n;

    public SegmentTree(int size) {
        n = size;
        array = new Node[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            array[i] = new Node();
        }
    }

    private void push(int node, int start, int end){
        if(array[node].lazy == 0) return;
        array[node].max += array[node].lazy;
        array[node].min += array[node].lazy;
        if(start != end){
            array[node * 2].lazy += array[node].lazy;
            array[node * 2 + 1].lazy += array[node].lazy;
        }
        array[node].lazy = 0;
    }

    private void update(int node, int value, int start, int end, int queryStart, int queryEnd){

        push(node, start, end);

        if(queryEnd < start || end < queryStart || end < start) return;

        if(queryStart <= start && end <= queryEnd){
            array[node].lazy += value;
            push(node, start, end);
            return;
        }

        int mid = start + (end - start) / 2;
        update(2 * node, value, start, mid, queryStart, queryEnd);
        update(2 * node + 1, value, mid + 1, end, queryStart, queryEnd);

        array[node].max = Math.max(array[2 * node].max, array[2 * node + 1].max);
        array[node].min = Math.min(array[2 * node].min, array[2 * node + 1].min);
    }

    public void update(int value, int start, int end) {
        update(1, value, 0, n - 1, start, end);
    }

    private boolean hasZero(int node, int start, int end){
        if(array[node].min <= 0 && 0 <= array[node].max) return true;
        return false;
    }

    private int query(int nodeIndex, int left, int right, int leftQuery, int rightQuery){
        push(nodeIndex, left, right);
        if(right < leftQuery || rightQuery < left || !hasZero(nodeIndex, left, right)) return -1;

        if(left == right){
            if(hasZero(nodeIndex, left, right)){
                return left;
            }
            return -1;
        }

        int mid = left + (right - left) / 2;
        int ans = query(2 * nodeIndex, left, mid, leftQuery, rightQuery);
        if(ans != -1) return ans;
        return query(2 * nodeIndex + 1, mid + 1, right, leftQuery, rightQuery);

    }

    public int query(int end) {
        int ans = query(1, 0, n - 1, 0, end);
        if(ans == -1) return end + 1;
        return ans;
    }

}

class Solution {
    int maxValue = (int) 1e5 + 1;

    public int longestBalanced(int[] nums) {
        int place[] = new int[maxValue];
        Arrays.fill(place, -1);
        SegmentTree st = new SegmentTree(nums.length);

        int ans = 0;
        for (int r = 0; r < nums.length; r++) {
            boolean odd = (nums[r] % 2) == 1;
            int prev = place[nums[r]];
            st.update(odd ? 1 : -1, prev + 1, r);
            place[nums[r]] = r;

            int l = st.query(r);
            ans = Math.max(ans, r - l + 1);

        }
        return ans;
    }
}