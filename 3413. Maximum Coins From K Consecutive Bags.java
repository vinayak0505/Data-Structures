import java.util.Arrays;

class Solution {
    public long maximumCoins(int[][] coins, int k) {
        Arrays.sort(coins, (a, b) -> Integer.compare(a[0], b[0]));
        int n = coins.length;
        long ans = 0;
        long curWindowSum = 0;

        int cI = 0;
        int cC = coins[0][0] - 1;
        int pI = -1;
        int pC = cC - k;

        while (true) {
            int cD = 0;
            if (coins[cI][1] == cC) {
                cD = coins[cI + 1][0] - 1 - cC;
            } else if (coins[cI][0] - 1 <= cC) {
                cD = coins[cI][1] - cC;
            } else {
                cD = coins[cI][0] - cC - 1;
            }

            int pD = 0;
            if (pI == -1 || coins[pI][1] == pC) {
                pD = coins[pI + 1][0] - 1 - pC;
            } else if (coins[pI][0] - 1 <= pC) {
                pD = coins[pI][1] - pC;
            } else {
                pD = coins[pI][0] - pC - 1;
            }

            int moveBy = Math.min(pD, cD);

            if (coins[cI][1] == cC) {
                curWindowSum += 0;
                cI++;
            } else if (coins[cI][0] - 1 <= cC) {
                curWindowSum += (long)coins[cI][2] * moveBy;
            } else {
                curWindowSum += 0;
            }
            cC += moveBy;

            if (pI == -1 || coins[pI][1] == pC) {
                curWindowSum -= 0;
                pI++;
            } else if (coins[pI][0] - 1 <= pC) {
                curWindowSum -= (long)coins[pI][2] * moveBy;
            } else {
                curWindowSum -= 0;
            }
            pC += moveBy;

            ans = Math.max(ans, curWindowSum);
            if (cC == coins[n - 1][1]) {
                return ans;
            }

        }
    }
}