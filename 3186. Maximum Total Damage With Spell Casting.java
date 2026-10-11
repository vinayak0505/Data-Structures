import java.util.TreeMap;

class Solution {
    public long maximumTotalDamage(int[] power) {
        TreeMap<Integer, Integer> spell = new TreeMap<>();

        for (int p : power) {
            spell.put(p, spell.getOrDefault(p, 0) + 1);
        }

        int n = spell.size();
        long[] dp = new long[n];
        int[] keySet = spell.keySet().stream()
                .mapToInt(Integer::intValue)
                .toArray();
        for (int i = 0; i < n; i++) {
            dp[i] = ((long)keySet[i] * spell.get(keySet[i]));
            for (int j = i - 1; j >= 0 && j >= i - 3; j--) {
                if (keySet[i] <= keySet[j] + 2)
                    continue;
                dp[i] = Math.max(dp[i], dp[j] + ((long) keySet[i] * spell.get(keySet[i])));
                break;
            }
            if (i != 0) {
                dp[i] = Math.max(dp[i], dp[i - 1]);
            }
        }
        return dp[n - 1];
    }
}