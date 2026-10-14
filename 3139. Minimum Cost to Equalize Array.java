import java.util.Arrays;

class Solution {

    int mod = (int) 1e9 + 7;

    public int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {
        if (nums.length == 1) {
            return 0;
        }
        int maxValue = Arrays.stream(nums).max().getAsInt();
        long totalDiffForMax = 0;
        for (int num : nums) {
            totalDiffForMax += maxValue - num;
        }
        if (cost1 * 2 <= cost2) {
            return (int) ((totalDiffForMax * cost1) % mod);
        }

        int minValue = Arrays.stream(nums).min().getAsInt();
        long ans = Long.MAX_VALUE;
        for (int i = maxValue; i < maxValue * 2; i++) {
            ans = Math.min(ans, costToMake(nums, i, cost1, cost2, maxValue, minValue, totalDiffForMax));
        }
        return (int) (ans % mod);
    }

    private long costToMake(int[] nums, int target, long cost1, long cost2, int maxValue, int minValue,
            long totalDiffForMax) {
        long diffFromMinValueToTarget = target - minValue;
        long totalAdditionsNeededForTarget = totalDiffForMax + (long)nums.length * (target - maxValue);
        long remaingToTarget = totalAdditionsNeededForTarget - diffFromMinValueToTarget;
        if (diffFromMinValueToTarget > remaingToTarget) {
            return (remaingToTarget * cost2 + (diffFromMinValueToTarget - remaingToTarget) * cost1);
        }
        long ans = (totalAdditionsNeededForTarget / 2l) * cost2;
        if (totalAdditionsNeededForTarget % 2 == 1) {
            ans += cost1;
        }
        return ans;
    }
}