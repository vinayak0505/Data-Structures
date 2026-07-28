import java.util.Arrays;
import java.util.Stack;

class Solution {

    public long bowlSubarrays(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        int next[] = new int[n], prev[] = new int[n];
        Arrays.fill(next, n);
        Arrays.fill(prev, -1);

        for (int i = 0; i < n; i++) {
            while (st.size() > 0 && nums[st.peek()] < nums[i]) {
                st.pop();
            }
            if (st.size() > 0) {
                prev[i] = st.peek();
            }
            st.add(i);
        }

        st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (st.size() > 0 && nums[st.peek()] <= nums[i]) {
                next[st.pop()] = i;
            }
            st.add(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (i - prev[i] > 1 && prev[i] != -1) {
                ans++;
            }
            if (next[i] - i > 1 && next[i] != n) {
                ans++;
            }
        }
        return ans;

    }
}