import java.util.HashMap;
import java.util.TreeSet;

class Solution {

    class Node {
        int value;
        int count = 0;

        public Node(int value) {
            this.value = value;
        }
    }

    private int compare(Node a, Node b) {
        if (a.count == b.count) {
            return Integer.compare(a.value, b.value);
        }
        return Integer.compare(a.count, b.count);
    }

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int size = n - k + 1;
        long ans[] = new long[size];
        HashMap<Integer, Node> mp = new HashMap<>();
        TreeSet<Node> topX = new TreeSet<Node>(this::compare);
        long topXsum = 0;
        TreeSet<Node> remaining = new TreeSet<Node>((a, b) -> compare(b, a));

        for (int i = 0; i < k - 1; i++) {
            if (mp.containsKey(nums[i]) == false) {
                mp.put(nums[i], new Node(nums[i]));
            }
            topXsum = add(topX, topXsum, remaining, mp.get(nums[i]), x);
        }
        for (int i = k - 1; i < n; i++) {
            if (mp.containsKey(nums[i]) == false) {
                mp.put(nums[i], new Node(nums[i]));
            }
            topXsum = add(topX, topXsum, remaining, mp.get(nums[i]), x);

            ans[i - k + 1] = topXsum;
            topXsum = minus(topX, topXsum, remaining, mp.get(nums[i - k + 1]), x);

        }

        return ans;
    }

    private long minus(TreeSet<Solution.Node> topX, long topXsum, TreeSet<Solution.Node> remaining, Solution.Node node,
            int x) {
        if (topX.contains(node)) {
            topXsum -= (long) node.count * node.value;
            topX.remove(node);
        } else if (remaining.contains(node)) {
            remaining.remove(node);
        }

        node.count--;
        if (node.count != 0) {
            remaining.add(node);
        }

        while (remaining.size() > 0 && topX.size() < x) {
            topXsum += (long) remaining.getFirst().count * remaining.getFirst().value;
            topX.add(remaining.pollFirst());
        }
        return topXsum;
    }

    private long add(TreeSet<Solution.Node> topX, long topXsum, TreeSet<Solution.Node> remaining, Solution.Node node,
            int x) {
        if (topX.contains(node)) {
            topXsum -= (long) node.count * node.value;
            topX.remove(node);
        } else if (remaining.contains(node)) {
            remaining.remove(node);
        }

        node.count++;
        remaining.add(node);

        while (remaining.size() > 0 && topX.size() < x) {
            topXsum += (long) remaining.getFirst().count * remaining.getFirst().value;
            topX.add(remaining.pollFirst());
        }
        if (remaining.size() > 0) {
            while (compare(remaining.getFirst(), topX.getFirst()) > 0) {
                Node bestRemaining = remaining.pollFirst();
                Node worstTopX = topX.pollFirst();

                topXsum += (long) bestRemaining.count * bestRemaining.value;
                topXsum -= (long) worstTopX.count * worstTopX.value;

                topX.add(bestRemaining);
                remaining.add(worstTopX);
            }
        }
        return topXsum;
    }
}