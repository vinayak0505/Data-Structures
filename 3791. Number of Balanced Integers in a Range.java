import java.util.Arrays;

class Solution {
    long dp[][][];
    private long countBalanced(char[] num, int i, int sum, boolean tight){
        
        if(i == num.length){
            return sum == 0 ? 1 : 0;
        }
        if(dp[i][sum + 100][tight ? 1 : 0] != -1){
            return dp[i][sum + 100][tight ? 1 : 0];
        }
        int limit = tight ? num[i] - '0' : 9;
        long res = 0;
        for(int dig = 0; dig <= limit; dig++){
            res += countBalanced(num, i + 1, sum + (i % 2 == 0 ? dig : -dig), (tight && (dig == limit)));
        }
        return dp[i][sum + 100][tight ? 1 : 0] = res;
    }

    public long countBalanced(long low, long high) {
        dp = new long[16][200][2];
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 200; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        low = countBalanced(Long.toString(low - 1).toCharArray(), 0, 0, true);

        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 200; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        return countBalanced(Long.toString(high).toCharArray(), 0, 0, true) - low;
    }
}