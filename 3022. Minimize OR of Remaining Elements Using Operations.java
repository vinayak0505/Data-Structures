class Solution {
    public int minOrAfterOperations(int[] nums, int k) {
        int maxBit = 30;
        int taken = 0;
        int notTaken = 0;
        int allAnds = Integer.MAX_VALUE;
        for (int num : nums) {
            allAnds &= num;
        }

        for (int i = maxBit; i >= 0; i--) {
            if ((allAnds & (1 << i)) != 0) {
                notTaken |= (1 << i);
                continue;
            }
            int testMask = taken | (1 << i);
            int kcopy = k;
            int running = Integer.MAX_VALUE;
            for (int num : nums) {
                running &= num;
                if ((running & testMask) != 0) {
                    kcopy--;
                } else {
                    running = Integer.MAX_VALUE;
                }
            }
            if (kcopy < 0) {
                notTaken |= (1 << i);
            } else {
                taken |= (1 << i);
            }

        }
        return notTaken;
    }
}