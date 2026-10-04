class Solution {
    int maxBit = 48;

    public char kthCharacter(long k, int[] operations) {
        int moveAheadBy = 0;
        long sizeOfString = 1;
        for (int i = 0; i < Math.min(operations.length, 48); i++) {
            sizeOfString <<= 1;
        }

        for (int i = Math.min(48, operations.length) - 1; i >= 0; i--) {
            int o = operations[i];

            sizeOfString >>= 1;

            if (sizeOfString < k) {
                k -= sizeOfString;
                if (o == 1) {
                    moveAheadBy++;
                }
            }
        }
        return (char) ('a' + (moveAheadBy % 26));

    }
}