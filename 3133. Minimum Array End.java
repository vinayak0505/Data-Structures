import java.util.BitSet;

class Solution {
    public long minEnd(int n, int x) {
        long ans = x;
        long[] array = new long[] { (long) x };
        BitSet bs = BitSet.valueOf(array);

        int clearBit = 0;
        n--;
        while (n > 0) {
            boolean set = (int) (n & 1) == 1;
            n >>= 1;
            clearBit = bs.nextClearBit(clearBit);
            if (set) {
                ans |= (1l << clearBit);
            }
            clearBit++;

        }
        return ans;
    }
}