import java.util.*;

class Solution {
    public int minSplitMerge(int[] nums1, int[] nums2) {
        List<Integer> start = toList(nums1);
        List<Integer> target = toList(nums2);

        if (start.equals(target)) return 0;

        Queue<List<Integer>> queue = new ArrayDeque<>();
        Set<List<Integer>> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        int operations = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            operations++;

            for (int s = 0; s < size; s++) {
                List<Integer> current = queue.poll();

                // Generate all valid split-and-merge neighbors
                for (List<Integer> nextState : getNextStates(current)) {
                    if (nextState.equals(target)) {
                        return operations;
                    }
                    if (visited.add(nextState)) {
                        queue.offer(nextState);
                    }
                }
            }
        }

        return -1; // If target is impossible to reach (e.g., multiset mismatch)
    }

    private List<List<Integer>> getNextStates(List<Integer> state) {
        List<List<Integer>> neighbors = new ArrayList<>();
        int n = state.size();

        // Choose subarray [i..j)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // Skip full array or single element moves that don't change state
                if (i == 0 && j == n) continue;

                List<Integer> sub = state.subList(i, j);
                
                // Form the remaining list without sub
                List<Integer> remaining = new ArrayList<>();
                remaining.addAll(state.subList(0, i));
                remaining.addAll(state.subList(j, n));

                // Re-insert 'sub' at every possible position k in 'remaining'
                for (int k = 0; k <= remaining.size(); k++) {
                    // Skip inserting back into its original relative position
                    if (k == i) continue;

                    List<Integer> next = new ArrayList<>(remaining);
                    next.addAll(k, sub);
                    neighbors.add(next);
                }
            }
        }
        return neighbors;
    }

    private List<Integer> toList(int[] arr) {
        List<Integer> list = new ArrayList<>(arr.length);
        for (int val : arr) list.add(val);
        return list;
    }
}