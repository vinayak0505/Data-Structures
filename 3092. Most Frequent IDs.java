import java.util.TreeSet;

class Solution {

    class Node {
        int value;
        long count;

        public Node(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    private Integer compare(Node a, Node b) {
        if (a.count == b.count) {
            return Integer.compare(a.value, b.value);
        }
        return Long.compare(a.count, b.count);
    }

    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        Node[] array = new Node[(int) 1e5 + 1];
        int n = nums.length;
        TreeSet<Node> set = new TreeSet<>((a, b) -> compare(a, b));
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            if (array[nums[i]] == null) {
                array[nums[i]] = new Node(nums[i], 0);
            } else {
                set.remove(array[nums[i]]);
            }
            array[nums[i]].count += freq[i];
            set.add(array[nums[i]]);
            ans[i] = set.last().count;
        }
        return ans;
    }
}