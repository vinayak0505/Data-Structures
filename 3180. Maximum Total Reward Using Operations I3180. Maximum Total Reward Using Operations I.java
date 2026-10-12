import java.util.Arrays;

class Solution {
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int value = 0;
        for (int reward : rewardValues) {
            value += reward;
        }

        boolean[] dp = new boolean[value + 1];
        dp[0] = true;
        dp[rewardValues[0]] = true;

        Arrays.sort(rewardValues);
        int ans = rewardValues[0];

        for (int i = 1; i < rewardValues.length; i++) {
            dp[rewardValues[i]] = true;
            ans = Math.max(ans, rewardValues[i]);
            for (int j = 0; j <= value && j < rewardValues[i]; j++) {
                if (dp[j] == false)
                    continue;
                int newvalue = j + rewardValues[i];
                ans = Math.max(ans, newvalue);
                dp[newvalue] = true;
            }
        }

        return ans;
    }
}