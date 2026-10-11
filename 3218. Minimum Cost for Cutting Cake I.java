import java.util.Arrays;

class Solution {

    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        int ans = 0;
        Arrays.sort(verticalCut);
        Arrays.sort(horizontalCut);
        for (int i = m - 2, j = n - 2; i >= 0 || j >= 0;) {
            int costi = i == -1 ? 0 : (horizontalCut[i] * (n - j - 1));
            int costj = j == -1 ? 0 : (verticalCut[j] * (m - i - 1));

            System.out.println("cost i " + costi + " j " + costj);

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