class Solution {
    int maxValue = (int) 1e5 + 2;
    int mod = (int) 1e9 + 7;

    public int sumOfGoodSubsequences(int[] nums) {
        long ans = 0;
        int n = nums.length;
        long prevSum[] = new long[maxValue];
        long count[] = new long[maxValue];

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            long newCount = (1 + count[num + 1]) % mod;
            if (num != 0) {
                newCount = (newCount + count[num - 1]) % mod;
            }
            count[num] = (count[num] + newCount) % mod;

            long tempans = (newCount * num) % mod;
            tempans = (tempans + prevSum[num + 1]) % mod;
            if (num != 0) {
                tempans = (tempans + prevSum[num - 1]) % mod;
            }
            ans = (ans + tempans) % mod;
            prevSum[num] = (prevSum[num] + tempans) % mod;
        }

        return (int) ans;
    }
}