import java.util.Collections;
import java.util.TreeMap;

class Solution {

    class XorTrie {
        public int count = 0;
        public XorTrie left = null;
        public XorTrie right = null;
    }

    XorTrie head;

    private void addNumber(int num) {
        XorTrie newhead = head;
        for (int i = 31; i >= 0; i--) {
            if ((num & (1 << i)) == 0) {
                if (newhead.left == null) {
                    newhead.left = new XorTrie();
                }
                newhead = newhead.left;
            } else {
                if (newhead.right == null) {
                    newhead.right = new XorTrie();
                }
                newhead = newhead.right;
            }
            newhead.count++;
        }
    }

    private void removeNumber(int num) {
        XorTrie newhead = head;
        for (int i = 31; i >= 0; i--) {
            if ((num & (1 << i)) == 0) {
                if (newhead.left.count == 1) {
                    newhead.left = null;
                    return;
                }
                newhead = newhead.left;
            } else {
                if (newhead.right.count == 1) {
                    newhead.right = null;
                    return;
                }
                newhead = newhead.right;
            }
            newhead.count--;
        }
    }

    private int getMaxDiff(XorTrie node, int num, int i) {
        if (node == null || i < 0)
            return 0;
        int bit = (num >> i) & 1;
        if (bit == 0) {
            // query bit is 0, want opposite (1) → go right
            if (node.right != null)
                return (1 << i) + getMaxDiff(node.right, num, i - 1);
            return getMaxDiff(node.left, num, i - 1);
        } else {
            // query bit is 1, want opposite (0) → go left
            if (node.left != null)
                return (1 << i) + getMaxDiff(node.left, num, i - 1);
            return getMaxDiff(node.right, num, i - 1);
        }
    }

    public int maxXor(int[] nums, int k) {
        head = new XorTrie();
        int n = nums.length;
        int ans = 0;
        int pre = 0;
        int[] presum = new int[n + 1];
        presum[0] = 0;
        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] ^ nums[i];
        }

        TreeMap<Integer, Integer> min = new TreeMap<>();
        TreeMap<Integer, Integer> max = new TreeMap<>(Collections.reverseOrder());
        for (int end = 0, start = 0; end < n; end++) {
            max.put(nums[end], max.getOrDefault(nums[end], 0) + 1);
            min.put(nums[end], min.getOrDefault(nums[end], 0) + 1);
            addNumber(presum[end]);

            while (!max.isEmpty() && !min.isEmpty() && (max.firstKey() - min.firstKey() > k)) {
                int value = max.get(nums[start]) - 1;
                removeNumber(presum[start]);
                if (value == 0) {
                    max.remove(nums[start]);
                    min.remove(nums[start]);
                } else {
                    max.put(nums[start], value);
                    min.put(nums[start], value);
                }
                start++;
            }

            ans = Math.max(ans, getMaxDiff(head, presum[end + 1], 31));
            System.out.println("start " + start + " end " + end + " presum " + presum[end + 1] + " ans "
                    + getMaxDiff(head, presum[end], 31));
        }
        return ans;
    }
}