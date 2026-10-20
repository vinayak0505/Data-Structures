import java.util.ArrayList;

class Solution {

    private ArrayList<Integer> indexOfMathcing(int[] str, int[] pattern) {
        int p = pattern.length;
        int n = str.length;

        int[] pi = new int[p];

        for (int i = 1, j = 0; i < p; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                j++;
            }
            pi[i] = j;
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && str[i] != pattern[j]) {
                j = pi[j - 1];
            }

            if (str[i] == pattern[j]) {
                j++;
            }

            if (j == p) {
                ans.add(i - p + 1);
                j = pi[j - 1];
            }
        }
        return ans;
    }

    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int newnums[] = new int[nums.length - 1];

        for (int i = 0; i < newnums.length; i++) {
            newnums[i] = Integer.compare(nums[i + 1], nums[i]);
        }

        ArrayList<Integer> indexes = indexOfMathcing(newnums, pattern);

        return indexes.size();
    }
}