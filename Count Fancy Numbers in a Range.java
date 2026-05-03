import java.util.HashSet;
import java.util.Set;

class Solution {
    private boolean isDecreasing(int num) {
        int prev = 10;
        while (num > 0) {
            int val = num % 10;
            num /= 10;
            if (val >= prev)
                return false;
            prev = val;
        }
        return true;
    }

    private boolean isIncreasing(int num) {
        int prev = -1;
        while (num > 0) {
            int val = num % 10;
            num /= 10;
            if (val <= prev)
                return false;
            prev = val;
        }
        return true;
    }

    boolean isSumFancy[];

    private void fillSumFancy() {
        int n = 145;
        isSumFancy = new boolean[n];
        for (int i = 0; i < n; i++) {
            isSumFancy[i] = (isIncreasing(i) || isDecreasing(i));
        }
    }

    Set<Long> isGoodNumber;

    private boolean checkSumFancy(long num) {
        int value = 0;
        while (num > 0) {
            value += num % 10;
            num /= 10;
        }
        return isSumFancy[value];
    }

    private void fillGreaterGood(long num, int prev) {
        if (checkSumFancy(num) == false) {
            isGoodNumber.add(num);
        }
        for (int i = prev + 1; i < 10; i++) {
            long newnum = (num * 10) + i;
            fillGreaterGood(newnum, i);
        }
    }

    private void fillSmallerGood(long num, int prev) {
        if (checkSumFancy(num) == false) {
            isGoodNumber.add(num);
        }
        for (int i = prev - 1; i >= 0; i--) {
            long newnum = (num * 10) + i;
            fillSmallerGood(newnum, i);
        }
    }

    private void fillGoodNumber() {
        isGoodNumber = new HashSet<>();
        fillGreaterGood(0, 0);
        fillSmallerGood(0, 10);
    }

    Long dp[][][];

    long fillDp(int index, int currentSum, boolean isTight, String s) {
        if (index == s.length()) {
            return isSumFancy[currentSum] ? 1l : 0l;
        }
        int tightNumber = isTight ? 1 : 0;
        if (dp[index][currentSum][tightNumber] != null) {
            return dp[index][currentSum][tightNumber];
        }
        long increase = 0;
        int till = (isTight ? s.charAt(index) - '0' : 9);
        for (int i = 0; i <= till; i++) {
            boolean nextTight = isTight && (i == till);
            increase += fillDp(index + 1, currentSum + i, nextTight, s);
        }
        return dp[index][currentSum][tightNumber] = increase;
    }

    private long countValueFor(long num) {
        String numS = String.valueOf(num);
        dp = new Long[17][155][2];
        long value = fillDp(0, 0, true, numS);
        for (long val : isGoodNumber) {
            if (val <= num)
                value++;
        }
        return value;
    }

    public long countFancy(long l, long r) {
        fillSumFancy();
        fillGoodNumber();
        return countValueFor(r) - countValueFor(l - 1);
    }
}