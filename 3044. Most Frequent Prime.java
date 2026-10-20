import java.util.Arrays;
import java.util.HashMap;

class Solution {

    int maxSize = 6;
    private boolean prime[];
    int[][] directions = new int[][] {
            { 0, 1 },
            { 0, -1 },
            { 1, 1 },
            { 1, 0 },
            { 1, -1 },
            { -1, 1 },
            { -1, 0 },
            { -1, -1 }
    };

    private void makePrimes() {
        int maxNum = 1;
        for (int i = 0; i < maxSize; i++) {
            maxNum *= 10;
        }

        prime = new boolean[maxNum];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;

        for (int i = 2; i < maxNum; i++) {
            if (prime[i] == false)
                continue;
            for (int j = i + i; j < maxNum; j += i) {
                prime[j] = false;
            }
        }
    }

    public int mostFrequentPrime(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        maxSize = Math.max(n, m);
        makePrimes();

        int ans = -1;
        int count = 0;
        HashMap<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                for (int[] direction : directions) {
                    int x = i, y = j;
                    int num = mat[x][y];
                    x += direction[0];
                    y += direction[1];
                    while (x != n && x != -1 && y != m && y != -1) {
                        num *= 10;
                        num += mat[x][y];
                        if (prime[num]) {
                            int thisCount = mp.getOrDefault(num, 0) + 1;
                            if (thisCount > count) {
                                count = thisCount;
                                ans = num;
                            } else if (thisCount == count) {
                                ans = Math.max(num, ans);
                            }
                            mp.put(num, thisCount);
                        }
                        x += direction[0];
                        y += direction[1];
                    }

                }
            }
        }
        return ans;
    }
}