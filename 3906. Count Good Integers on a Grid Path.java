import java.util.HashSet;
import java.util.Set;

class Solution {
    private long solve(int i, Set<Integer> index, int[] max, boolean isMax, int prev, Long[][][] dp) {
        if (i == 16) {
            return 1;
        }
        if (dp[i][isMax ? 1 : 0][prev] != null) {
            return dp[i][isMax ? 1 : 0][prev];
        }
        int end = isMax ? max[i] : 9;
        long ans = 0;
        for (int j = index.contains(i) ? prev : 0; j <= end; j++) {
            ans += solve(i + 1, index, max, isMax && (j == end), index.contains(i) ? j : prev, dp);
        }
        dp[i][isMax ? 1 : 0][prev] = ans;
        return ans;
    }

    public long countGoodIntegersOnPath(long l, long r, String directions) {
        Set<Integer> index = new HashSet<>();
        index.add(0);
        int index_count = 0;
        for (char dir : directions.toCharArray()) {
            if (dir == 'D') {
                index_count += 4;
            } else {
                index_count++;
            }
            index.add(index_count);
        }

        int max[] = new int[16];
        for (int i = 15; i >= 0 && r > 0; i--) {
            max[i] = (int) (r % 10);
            r /= 10;
        }

        Long[][][] dp = new Long[16][2][10];
        long right = solve(0, index, max, true, 0, dp);

        max = new int[16];
        l--;
        for (int i = 15; i >= 0 && l > 0; i--) {
            max[i] = (int) (l % 10);
            l /= 10;
        }
        dp = new Long[16][2][10];
        long left = solve(0, index, max, true, 0, dp);
        return right - left;
    }
}