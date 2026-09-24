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
        HashMap<String, Long> dp = new HashMap<>();
        for (int r = 4; r < n; r++) {
            int q = r - 2;
            for (int p = 0; p <= q - 2; p++) {
                String g = reduceFraction(nums[p], nums[q]);
                dp.put(g, dp.getOrDefault(g, 0l) + 1);
            }

            for (int s = r + 2; s < n; s++) {
                String g = reduceFraction(nums[s], nums[r]);
                ans += dp.getOrDefault(g, 0l);
            }

        }
        return ans;
    }
}