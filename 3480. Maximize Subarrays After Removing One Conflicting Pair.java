import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    public long maxSubarrays(int n, int[][] conflictingPairs) {
        long canBeRemovedCount[] = new long[n];

        ArrayList<Integer> pairs[] = new ArrayList[n + 1];
        for (int i = 0; i < pairs.length; i++) {
            pairs[i] = new ArrayList<>();
        }

        for (int i = 0; i < conflictingPairs.length; i++) {
            int a = Math.min(conflictingPairs[i][0], conflictingPairs[i][1]);
            int b = Math.max(conflictingPairs[i][0], conflictingPairs[i][1]);

            pairs[b].add(a);
        }

        long alwaysValid = 0;
        int maxConflict = 0;
        int secondMaxConflict = 0;

        for (int end = 1; end <= n; end++) {
            for (int u: pairs[end]) {
                if(u >= maxConflict){
                    secondMaxConflict = maxConflict;
                    maxConflict = u;
                }else if(u > secondMaxConflict){
                    secondMaxConflict = u;
                }
            }

            alwaysValid += end - maxConflict;
            canBeRemovedCount[maxConflict] += maxConflict - secondMaxConflict;
        }

        return alwaysValid + Arrays.stream(canBeRemovedCount).max().orElse(0);
    }
}