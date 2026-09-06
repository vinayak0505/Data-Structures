import java.util.Objects;
import java.util.TreeSet;

class Solution {
    class Node {
        long num;
        int index;

        public Node(long num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {
            if (getClass() == obj.getClass()) {
                Node node = (Node) obj;
                return node.num == num && node.index == index;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, index);
        }
    }

    public int minimumPairRemoval(int[] temp) {
        int n = temp.length;
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = temp[i];
        }
        int badPairCount = 0;
        TreeSet<Node> queue = new TreeSet<>((a, b) -> {
            if (a.num != b.num) {
                return Long.compare(a.num, b.num);
            }
            return Integer.compare(a.index, b.index);
        });

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                badPairCount++;
            }
            queue.add(new Node(nums[i] + nums[i + 1], i));
        }

        int[] next = new int[n];
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            next[i] = i + 1;
            prev[i] = i - 1;
        }

        int count = 0;

        while (badPairCount != 0) {
            count++;
            Node node = queue.removeFirst();
            int first = node.index;
            int second = next[first];

            int first_left = prev[first];
            int second_right = next[second];

            if (nums[first] > nums[second]) {
                badPairCount--;
            }

            long sum = node.num;

            // left size
            if (first_left >= 0 && nums[first_left] > nums[first] && nums[first_left] <= sum) {
                badPairCount--;
            } else if (first_left >= 0 && nums[first_left] <= nums[first] && nums[first_left] > sum) {
                badPairCount++;
            }

            if (first_left >= 0) {
                queue.remove(new Node(nums[first_left] + nums[first], first_left));
                queue.add(new Node(nums[first_left] + nums[first] + nums[second], first_left));
            }

            // right size
            if (second_right < n && nums[second] > nums[second_right] && sum <= nums[second_right]) {
                badPairCount--;
            } else if (second_right < n && nums[second] <= nums[second_right] && sum > nums[second_right]) {
                badPairCount++;
            }

            if (second_right < n) {
                queue.remove(new Node(nums[second] + nums[second_right], second));
                queue.add(new Node(nums[second_right] + nums[first] + nums[second], first));
            }

            nums[first] = nums[first] + nums[second];
            next[first] = next[second];
            if (second_right < n) {
                prev[second_right] = first;
            }

        }

        return count;
    }
}