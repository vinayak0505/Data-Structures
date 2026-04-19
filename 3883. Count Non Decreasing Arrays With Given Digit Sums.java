import java.util.ArrayList;

class Solution {
    private int mod = (int) 1e9 + 7;
    private ArrayList<Integer>[] numbers;

    private void fillNumbers() {
        numbers = new ArrayList[51];
        for (int i = 0; i < 51; i++) {
            numbers[i] = new ArrayList<>();
        }
        for (int i = 0; i <= 5000; i++) {
            int num = i;
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            numbers[sum].add(i);
        }
    }

    private int getSameOrGreaterIndex(ArrayList<Integer> list, int target) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int helper(int i, int[] digitSum, int j, Integer[][] dp) {
        int n = digitSum.length;
        if (i == n) {
            return 1;
        }
        int m = numbers[digitSum[i]].size();

        if(j == m || j == -1) {
            return 0;
        }
        
        if(dp[i][j] != null) {
            return dp[i][j];
        }
        int ans = helper(i, digitSum, j + 1, dp);
        int nextJ = (i + 1 < n) ? getSameOrGreaterIndex(numbers[ digitSum[i + 1]], numbers[digitSum[i]].get(j)) : -1;
        ans = (ans + helper(i + 1, digitSum, nextJ, dp)) % mod;
        return dp[i][j] = ans % mod;
    }

    public int countArrays(int[] digitSum) {
        fillNumbers();
        int n = digitSum.length;
        Integer[][] dp = new Integer[n][5001];
        return (int) helper(0, digitSum, 0, dp);
    }
}