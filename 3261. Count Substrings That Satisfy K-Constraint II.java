class Solution {
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int n = s.length();
        int count1 = 0;
        int count2 = 0;

        int validUpto[] = new int[n];
        for (int i = n - 1, j = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                count1++;
            } else {
                count2++;
            }
            while (count2 > k && count1 > k) {
                if (s.charAt(j) == '1') {
                    count1--;
                } else {
                    count2--;
                }
                j--;
            }
            validUpto[i] = j;
        }
        long validEndingICount[] = new long[n];

        count1 = 0;
        count2 = 0;
        long count = 0;
        for (int i = 0, j = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                count1++;
            } else {
                count2++;
            }
            while (count2 > k && count1 > k) {
                if (s.charAt(j) == '1') {
                    count1--;
                } else {
                    count2--;
                }
                j++;
            }
            count += i - j + 1;
            validEndingICount[i] = count;
        }


        int q = queries.length;
        long ans[] = new long[q];
        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            if (validUpto[l] > r) {
                ans[i] = ((long) (r - l + 1) * (r - l + 2)) / 2;
                continue;
            }

            ans[i] = ((long)(validUpto[l] - l + 1) * (validUpto[l] - l + 1 + 1)) / 2;
            ans[i] += validEndingICount[r] - validEndingICount[validUpto[l]];

        }
        return ans;

    }
}