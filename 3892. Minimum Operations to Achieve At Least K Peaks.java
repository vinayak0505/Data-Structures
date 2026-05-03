import java.util.Arrays;

class Solution {
    long[][][] memo;

    long MAX_POS = Long.MAX_VALUE / 2;

    private long helper(int[] nums, int k, int i, int firstTaken){
        if(k == 0) return 0;

        int n = nums.length;
        if(n == i){
            return MAX_POS;
        }
        if(memo[firstTaken][k][i] != -1){
            return memo[firstTaken][k][i];
        }

        long take = MAX_POS;
        if(i != n - 1 || firstTaken == 0){
            if(i == 0){
                take = helper(nums, k - 1, i + 2, 1);
            }else{
                take = helper(nums, k - 1, i + 2, firstTaken);
            }
        }

        long nottake = helper(nums, k, i + 1, firstTaken);

        long neg = Math.max(nums[(i + 1) % n], nums[(i - 1 + n) % n]) + 1;
        long diff = neg > nums[i] ? neg - nums[i] : 0;

        if(take <= MAX_POS){
            return memo[firstTaken][k][i] = nottake;
        }else{
            return memo[firstTaken][k][i] = Math.min(take + diff, nottake);
        }
    }

    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        memo = new long[2][k + 1][n];
        for(long[][] arr : memo){
            for(long[] a : arr){
                Arrays.fill(a, -1);
            }
        }

        return (int)helper(nums, k, 0, 0);
    }
}