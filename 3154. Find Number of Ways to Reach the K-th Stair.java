class Solution {
    private int[] saved = new int[] {
            2,
            4,
            4,
            3,
            2,
            4,
            6,
            4,
            1,
    };

    public static long getPascalValue(int row, int col) {
        if (col < 1 || col > row) {
            return 0;
        }

        int n = row - 1;
        int r = col - 1;

        if (r > n - r) {
            r = n - r;
        }

        long result = 1;
        for (int i = 0; i < r; i++) {
            result = result * (n - i);
            result = result / (i + 1);
        }

        return result;
    }

    public int waysToReachStair(int k) {
        if (k <= 8) {
            return saved[k];
        }

        int count = 1;
        while ((1 << count) < k)
            count++;

        return (int) getPascalValue(count + 2, (1 << count) - k + 1);
    }
}