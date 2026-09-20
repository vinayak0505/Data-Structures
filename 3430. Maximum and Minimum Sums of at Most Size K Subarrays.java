import java.util.Arrays;
import java.util.Stack;

class Solution {
    public long minMaxSubarraySum(int[] nums, int k) {

        long ans = 0;
        int n = nums.length;

        Stack<Integer> st = new Stack<>();

        int[] prevGreater = new int[n];
        Arrays.fill(prevGreater, -1);
        int[] nextGreater = new int[n];
        Arrays.fill(nextGreater, n);
        for (int i = 0; i < n; i++) {
            while (st.isEmpty() == false && nums[st.peek()] < nums[i]) {
                nextGreater[st.pop()] = i;
            }
            if (st.size() > 0) {
                prevGreater[i] = st.peek();
            }
            st.add(i);
        }

        for (int i = 0; i < n; i++) {
            long leftLimit = Math.min(i - prevGreater[i], k);
            long rightLimit = Math.min(nextGreater[i] - i, k);
            if (nextGreater[i] - prevGreater[i] <= k + 1) {
                ans += (rightLimit * leftLimit) * nums[i];
            } else {
                long maxLeftForAllValues = Math.min(leftLimit, Math.max(0, k + 1 - rightLimit));
                ans += maxLeftForAllValues * rightLimit * nums[i];

                long firstTerm = (k + 1) - (maxLeftForAllValues + 1);
                long lastTerm = (k + 1) - leftLimit;
                ans += ((firstTerm * (firstTerm + 1)) / 2) * nums[i];
                ans -= ((lastTerm * (lastTerm - 1)) / 2) * nums[i];

            }
        }

        st.clear();
        int[] prevSmaller = new int[n];
        Arrays.fill(prevSmaller, -1);
        int[] nextSmaller = new int[n];
        Arrays.fill(nextSmaller, n);

        for (int i = 0; i < n; i++) {
            while (st.isEmpty() == false && nums[st.peek()] > nums[i]) {
                nextSmaller[st.pop()] = i;
            }
            if (st.size() > 0) {
                prevSmaller[i] = st.peek();
            }
            st.add(i);
        }

        for (int i = 0; i < n; i++) {
            long leftLimit = Math.min(i - prevSmaller[i], k);
            long rightLimit = Math.min(nextSmaller[i] - i, k);
            if (nextSmaller[i] - prevSmaller[i] <= k + 1) {
                ans += (rightLimit * leftLimit) * nums[i];
            } else {
                long maxLeftForAllValues = Math.min(leftLimit, Math.max(0, k + 1 - rightLimit));
                ans += maxLeftForAllValues * rightLimit * nums[i];

                long firstTerm = (k + 1) - (maxLeftForAllValues + 1);
                long lastTerm = (k + 1) - leftLimit;
                ans += ((firstTerm * (firstTerm + 1)) / 2) * nums[i];
                ans -= ((lastTerm * (lastTerm - 1)) / 2) * nums[i];

            }
        }

        return ans;

    }
}