import java.util.Arrays;
import java.math.BigInteger;

class Solution {
    public int maxTotalReward(int[] rewardValues) {
        rewardValues = Arrays.stream(rewardValues).distinct().sorted().toArray();
        int maxSize = 0;
        for (int reward : rewardValues) {
            maxSize = Math.max(maxSize, reward);
        }
        maxSize <<= 1;
        BigInteger f = BigInteger.ONE;
        for (int reward : rewardValues) {
            // Create a mask for bits less than 'reward'
            BigInteger mask = BigInteger.ONE.shiftLeft(reward).subtract(BigInteger.ONE);
            // Shift the valid reachable sums and combine them
            BigInteger shifted = f.and(mask).shiftLeft(reward);
            f = f.or(shifted);
        }
        return f.bitLength() - 1;
    }
}