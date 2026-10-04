import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    int maxNum = 100;

    private int helper(int num, int mask, int maxMask, ArrayList<Integer>[] numPostion, int dp[][]) {
        if (num == 0 || maxMask == mask)
            return 0;

        if (dp[num][mask] != -1) {
            return dp[num][mask];
        }
        int ans = helper(num - 1, mask, maxMask, numPostion, dp);
        for (int i : numPostion[num]) {
            if (((1 << i) & mask) != 0)
                continue;
            ans = Math.max(ans, helper(num - 1, mask | (1 << i), maxMask, numPostion, dp) + num);
        }
        return dp[num][mask] = ans;
    }

    public int maxScore(List<List<Integer>> grid) {
        ArrayList<Integer>[] numPostion = new ArrayList[maxNum + 1];
        for (int i = 0; i < numPostion.length; i++) {
            numPostion[i] = new ArrayList<>();
        }
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                numPostion[grid.get(i).get(j)].add(i);
            }
        }

        int dp[][] = new int[maxNum + 1][(int) (1 << grid.size()) - 1];
        for (int i = 0; i <= maxNum; i++) {
            Arrays.fill(dp[i], -1);
        }

        return helper(maxNum, 0, (1 << grid.size()) - 1, numPostion, dp);

    }
}