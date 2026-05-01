class Solution {
    private long NOT_POS = Long.MIN_VALUE;
    Long dp[][][];
    private long maxScore(int[] nums1, int[] nums2, int k, int i, int j){
        int n = nums1.length;
        int m = nums2.length;
        if(i == n || j == m || k == 0){
            if(k == 0) return 0;
            return NOT_POS;
        }
        if(dp[i][j][k] != null){
            return dp[i][j][k];
        }
        long moveI = maxScore(nums1, nums2, k, i + 1, j);
        long moveJ = maxScore(nums1, nums2, k, i, j + 1);
        long ans = Math.max(moveI, moveJ);
        long pick = maxScore(nums1, nums2, k - 1, i +1, j + 1);
        if(pick != NOT_POS){
            pick += (long)nums1[i] * (long)nums2[j];
        }
        return dp[i][j][k] = Math.max(ans, pick);
    }
    public long maxScore(int[] nums1, int[] nums2, int k) {
        dp = new Long[101][101][101];
        return maxScore(nums1, nums2, k, 0, 0);
    }
}