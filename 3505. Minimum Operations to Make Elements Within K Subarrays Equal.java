import java.util.Arrays;
import java.util.TreeSet;

class Solution {

    private int compare(int[] a, int[] b) {
        if (a[0] == b[0]) {
            return Integer.compare(a[1], b[1]);
        }
        return Integer.compare(a[0], b[0]);
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        TreeSet<int[]> maxHeap = new TreeSet<int[]>((a, b) -> compare(b, a));
        TreeSet<int[]> minHeap = new TreeSet<int[]>((a, b) -> compare(a, b));
        long[] cost = new long[n];
        long sumGreaterHalf = 0, sumSmallerHalf = 0;

        for (int i = 0; i < n; i++) {
            if (maxHeap.isEmpty() || nums[i] <= maxHeap.first()[0]) {
                maxHeap.add(new int[] { nums[i], i });
                sumSmallerHalf += nums[i];
            } else {
                minHeap.add(new int[] { nums[i], i });
                sumGreaterHalf += nums[i];
            }

            if (i >= x) {
                if (maxHeap.contains(new int[] { nums[i - x], i - x })) {
                    maxHeap.remove(new int[] { nums[i - x], i - x });
                    sumSmallerHalf -= nums[i - x];
                } else {
                    minHeap.remove(new int[] { nums[i - x], i - x });
                    sumGreaterHalf -= nums[i - x];
                }
            }

            int[] num;

            while (maxHeap.size() > minHeap.size() + 1) {
                num = maxHeap.removeFirst();
                sumSmallerHalf -= num[0];
                minHeap.add(num);
                sumGreaterHalf += num[0];
            }

            while (maxHeap.size() < minHeap.size()) {
                num = minHeap.removeFirst();
                sumGreaterHalf -= num[0];
                maxHeap.add(num);
                sumSmallerHalf += num[0];
            }

            int median = maxHeap.first()[0];

            cost[i] = (median * maxHeap.size() - sumSmallerHalf)
                    + (sumGreaterHalf - median * minHeap.size());
        }

        // System.out.println(Arrays.toString(cost));

        long[][] dp = new long[k + 1][n];
        for (int i = 1; i <= k; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }

        for (int i = 0; i <= k; i++) {
            for (int j = x - 1; j < n; j++) {
                if (i == 0) {
                    continue;
                }
                long prev;
                if (j - x >= 0) {
                    prev = dp[i - 1][j - x];
                } else {
                    prev = (i == 1) ? 0 : Long.MAX_VALUE / 2;
                }
                dp[i][j] = Math.min(dp[i][j - 1], (long) cost[j] + prev);
            }
            // System.out.println(Arrays.toString(dp[i]));

        }
        return dp[k][n - 1];
    }
}