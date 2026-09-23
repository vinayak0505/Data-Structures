import java.util.HashMap;

class Solution {

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);

    }

    private String reduceFraction(int a, int b) {
        int g = gcd(a, b);
        a /= g;
        b /= g;
        return a + "_" + b;
    }

    public long numberOfSubsequences(int[] nums) {
        long ans = 0;
        int n = nums.length;
        HashMap<String, Long> dp[] = new HashMap[n];
        for (int j = n - 3; j >= 0; j--) {
            if (dp[j + 1] != null) {
                dp[j] = new HashMap<>(dp[j + 1]);
            } else {
                dp[j] = new HashMap<>();
            }

            for (int i = j + 2; i < n; i++) {
                String key = reduceFraction(nums[i], nums[j]);
                dp[j].put(key, dp[j].getOrDefault(key, 0L) + 1);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j < n - 2; j++) {
                String key = reduceFraction(nums[i], nums[j]);
                if (dp[j + 2] == null) {
                    continue;
                }
                ans += dp[j + 2].getOrDefault(key, 0l);
            }
        }
        return ans;
    }
}