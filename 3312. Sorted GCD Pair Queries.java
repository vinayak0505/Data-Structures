class Solution {

    public static long nC2(long n) {
        if (n < 2)
            return 0;
        return n * (n - 1) / 2;
    }

    public int[] gcdValues(int[] nums, long[] queries) {

        int maxValue = 0;
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }

        long[] difCount = new long[maxValue + 1];

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            for (int j = 1; j * j <= num; j++) {
                if(num % j == 0){
                    difCount[j]++;
                    if(num / j != j){
                        difCount[num / j]++;
                    }
                }
            }
        }

        for (int i = 0; i < difCount.length; i++) {
            difCount[i] = nC2(difCount[i]);
        }

        for (int i = maxValue; i > 0; i--) {
            for (int j = i + i; j <= maxValue; j += i) {
                difCount[i] -= difCount[j];
            }
        }

        int[] valueCount = new int[maxValue + 1];
        for (int i = 1; i < difCount.length; i++) {
            if (difCount[i] != 0) {
                valueCount[i] = i;
            } else {
                valueCount[i] = valueCount[i - 1];
            }
            difCount[i] += difCount[i - 1];
        }


        int q = queries.length;
        int ans[] = new int[q];
        for (int i = 0; i < ans.length; i++) {
            long query = queries[i];
            int start = 1, end = maxValue;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (query < difCount[mid]) {
                    ans[i] = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return ans;
    }
}