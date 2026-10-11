class Solution {
    public long minimumOperations(int[] nums, int[] target) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            nums[i] -= target[i];
        }

        int[] positiveArray = new int[n];
        int[] negativeArray = new int[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                positiveArray[i] = nums[i];
            } else {
                negativeArray[i] = -nums[i];
            }
        }

        long ans = 0;
        int prev = 0;
        for (int i = 0; i < n; i++) {
            if (prev < positiveArray[i]) {
                ans += positiveArray[i] - prev;
            }
            prev = positiveArray[i];
        }
        System.out.println(ans);
        prev = 0;
        for (int i = 0; i < n; i++) {
            if (prev < negativeArray[i]) {
                ans += negativeArray[i] - prev;
            }
            prev = negativeArray[i];
        }
        return ans;
    }
}