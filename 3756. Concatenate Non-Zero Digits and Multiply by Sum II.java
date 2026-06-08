class Solution {
    private int MOD = (int)1e9 + 7;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        long preSum[] = new long[n + 1];
        long preNum[] = new long[n + 1];
        long prePower[] = new long[n + 1];
        int nonZeroCount[] = new int[n + 1];
        prePower[0] = 1;

        for(int i = 0; i < n; i++){
            preSum[i + 1] = (preSum[i] + (s.charAt(i) - '0')) % MOD;
            if(s.charAt(i) != '0'){
                preNum[i + 1] = ((preNum[i] * 10) + (s.charAt(i) - '0')) % MOD;
                nonZeroCount[i + 1] = nonZeroCount[i] + 1;
            }else{
                preNum[i + 1] = preNum[i];
                nonZeroCount[i + 1] = nonZeroCount[i];
            }
            prePower[i + 1] = (prePower[i] * 10) % MOD;
        }

        int ans[] = new int[queries.length];

        for(int i = 0; i < queries.length; i++){
            int l = queries[i][0], r = queries[i][1];
            long sum = preSum[r + 1] - preSum[l];

            long rightNum = preNum[r + 1];
            int countInRange = nonZeroCount[r + 1] - nonZeroCount[l];
            long multiplier = prePower[countInRange];
            long leftNum = (preNum[l] * multiplier) % MOD;
            long num = ((rightNum - leftNum) + MOD) % MOD;

            ans[i] = (int)((sum * num) % MOD);
        }

        return ans;
    }
}