class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long minValue = Integer.MAX_VALUE;
        for (int coin : coins) {
            minValue = Math.min(minValue, coin);
        }

        long maxValue = minValue * k;

        long ans = 0;
        while (minValue <= maxValue) {
            long mid = (minValue + maxValue) / 2;
            if (isValid(mid, coins, k)) {
                maxValue = mid - 1;
                ans = mid;
            } else {
                minValue = mid + 1;
            }
        }
        return ans;
    }

    public static long getGCD(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    boolean isValid(long mid, int[] coins, int k) {
        long count = 0;
        int mask = (1 << coins.length) - 1;
        for (int i = 1; i <= mask; i++) {
            int bitSize = 0;
            long lcm = 1;
            for (int j = 0; j < 16; j++) {
                if(((1 << j) & i) != 0){
                    bitSize++;
                    lcm = (lcm * coins[j]) / getGCD(lcm, coins[j]);
                }
            }
            if(bitSize % 2 == 0){
                count -= (mid / lcm);
            }else{
                count += (mid / lcm);
            }


        }
        return count >= k;
    }
}