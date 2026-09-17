import java.util.Arrays;

class Solution {
    long dp[][];
    long maskToLcm[];
    int maxMask;

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        if (a == 0 || b == 0)
            return 0;
        return (a / gcd(a, b)) * b;
    }

    private void buildLcm(int[] target) {
        maskToLcm = new long[maxMask + 1];
        for (int mask = 1; mask <= maxMask; mask++) {
            long currentLcm = 1;
            for (int i = 0; i < target.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentLcm = lcm(currentLcm, target[i]);
                }
            }
            maskToLcm[mask] = currentLcm;
        }
    }

    private long helper(int[] nums, int i, int mask) {
        if (mask == maxMask) {
            return 0;
        }

        int n = nums.length;
        if (i == n)
            return Long.MAX_VALUE;

        if (dp[i][mask] != -1L)
            return dp[i][mask];

        long ans = helper(nums, i + 1, mask);
        for (int newMask = 1; newMask <= maxMask; newMask++) {
            if ((newMask & mask) != 0)
                continue;
            long lcm = maskToLcm[newMask];
            long diff = Math.ceilDiv((long) nums[i], lcm) * lcm - nums[i];
            long value = helper(nums, i + 1, mask | newMask);
            if (value == Long.MAX_VALUE)
                continue;
            ans = Math.min(ans, diff + value);
        }
        return dp[i][mask] = ans;
    }

    public int minimumIncrements(int[] nums, int[] target) {
        maxMask = (1 << target.length) - 1;
        int n = nums.length;
        dp = new long[n][maxMask + 1];
        buildLcm(target);
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1L);
        }
        return (int)helper(nums, 0, 0);
    }
}