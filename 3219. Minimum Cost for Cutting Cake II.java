import java.util.Arrays;

class Solution {

    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        long ans = 0;
        Arrays.sort(verticalCut);
        Arrays.sort(horizontalCut);
        for (int i = m - 2, j = n - 2; i >= 0 || j >= 0;) {
            long costi = i == -1 ? 0 : ((long) horizontalCut[i] * (n - j - 1));
            long costj = j == -1 ? 0 : ((long) verticalCut[j] * (m - i - 1));

            if ((i == -1 ? 0 : horizontalCut[i]) < (j == -1 ? 0 : verticalCut[j])) {
                ans += costj;
                j--;
            } else {
                ans += costi;
                i--;
            }
        }
        return ans;
    }
}