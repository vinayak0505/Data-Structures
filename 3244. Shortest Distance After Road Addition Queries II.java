class Solution {

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int parant[] = new int[n];
        for (int i = 0; i < parant.length - 1; i++) {
            parant[i] = i + 1;
        }

        int curAns = n - 1;
        int q = queries.length;
        int ans[] = new int[q];

        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            if (parant[l] >= r) {
                ans[i] = curAns;
                continue;
            }

            for (; l < r;) {
                int p = parant[l];
                parant[l] = r;
                l = p;
                curAns--;
            }
            curAns++;
            ans[i] = curAns;
        }

        return ans;
    }
}