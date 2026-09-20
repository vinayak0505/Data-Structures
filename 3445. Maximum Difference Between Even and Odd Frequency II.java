import java.util.Arrays;

class Solution {

    private int getCurState(int a, int b) {
        if (a % 2 == 0) {
            return (b % 2 == 0) ? 2 : 3;
        }
        return (b % 2 == 0) ? 0 : 1;
    }

    private int getExpectedState(int num) {
        switch (num) {
            case 0:
                return 2; // odd_even -> even_even
            case 1:
                return 3; // odd_odd -> even_odd
            case 2:
                return 0; // even_even -> odd_even
            default:
                return 1; // even_odd -> odd_odd
        }
    }

    private int helper(String s, int k, int a, int b) {
        int n = s.length();
        int aCountDp[] = new int[n];
        int bCountDp[] = new int[n];

        int aCount = 0;
        int bCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) - '0' == a) {
                aCount++;
            } else if (s.charAt(i) - '0' == b) {
                bCount++;
            }
            aCountDp[i] = aCount;
            bCountDp[i] = bCount;
        }

        // int odd_even = -1;
        // int odd_odd = -1;
        // int even_even = -1;
        // int even_odd = -1;
        Integer minValueOfEachStateA[] = new Integer[4];
        Integer minValueOfEachStateB[] = new Integer[4];
        Integer minValueOfEachStateA2[] = new Integer[4];
        Integer minValueOfEachStateB2[] = new Integer[4];
        // minValueOfEachStateA[2] = minValueOfEachStateB[2] = 0;

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int curState = getCurState(aCountDp[i], bCountDp[i]);
            int expState = getExpectedState(curState);

            if (i - k >= 0) {
                int index = i - k;
                int prevState = getCurState(aCountDp[index], bCountDp[index]);
                int prevValue = aCountDp[index] - bCountDp[index];
                if (minValueOfEachStateA[prevState] == null) {
                    minValueOfEachStateA[prevState] = aCountDp[index];
                    minValueOfEachStateB[prevState] = bCountDp[index];
                }else if(prevValue < (minValueOfEachStateA[prevState] - minValueOfEachStateB[prevState])){
                    minValueOfEachStateA2[prevState] = minValueOfEachStateA[prevState];
                    minValueOfEachStateB2[prevState] = minValueOfEachStateB[prevState];
                    minValueOfEachStateA[prevState] = aCountDp[index];
                    minValueOfEachStateB[prevState] = bCountDp[index];
                }else if(minValueOfEachStateA2[prevState] == null || prevValue < (minValueOfEachStateA2[prevState] - minValueOfEachStateB2[prevState])){
                    minValueOfEachStateA2[prevState] = aCountDp[index];
                    minValueOfEachStateB2[prevState] = bCountDp[index];
                }
            } else if (i - k == -1) {
                minValueOfEachStateA[2] = 0;
                minValueOfEachStateB[2] = 0;
            }
            // if prev index is -1 and prevstate even even then we can take 0 0
            if (i - k == -1) {
                if (expState == 2 && bCountDp[i] != 0) {
                    ans = Math.max(ans, aCountDp[i] - bCountDp[i]);
                }
                // check if prev any state exist or not
            } else if (minValueOfEachStateA[expState] != null && bCountDp[i] - minValueOfEachStateB[expState] > 0) {
                ans = Math.max(ans,
                        aCountDp[i] - minValueOfEachStateA[expState] - (bCountDp[i] - minValueOfEachStateB[expState]));
            }else if(minValueOfEachStateA2[expState] != null && bCountDp[i] - minValueOfEachStateB2[expState] > 0){
                ans = Math.max(ans,
                        aCountDp[i] - minValueOfEachStateA2[expState] - (bCountDp[i] - minValueOfEachStateB2[expState]));
            }

        }
        return ans;

    }

    public int maxDifference(String s, int k) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == j)
                    continue;
                ans = Math.max(ans, helper(s, k, i, j));
            }
        }
        return ans;
    }
}