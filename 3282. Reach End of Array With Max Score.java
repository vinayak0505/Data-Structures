import java.util.List;

class Solution {
    public long findMaximumScore(List<Integer> nums) {
        long ans = 0;
        int prev = 0;
        for (int num : nums) {
            ans += prev;
            prev = Math.max(prev, num);
        }
        return ans;
    }
}