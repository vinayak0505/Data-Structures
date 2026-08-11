import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    int mod = (int) 1e9 + 7;

    private long power(long base, long exp) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1)
                res = (res * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;

        int sqrt = (int) Math.sqrt(n);
        HashMap<Integer, ArrayList<int[]>> kToQueriesMap = new HashMap<>();
        for (int[] query : queries) {
            int k = query[2];
            if (k > sqrt) {
                apply(nums, query);
            } else {
                if (kToQueriesMap.containsKey(k) == false) {
                    kToQueriesMap.put(k, new ArrayList<>());
                }
                kToQueriesMap.get(k).add(query);
            }
        }

        long mul[] = new long[n + 1];
        long div[] = new long[n + 1];
        for (Map.Entry<Integer, ArrayList<int[]>> entry : kToQueriesMap.entrySet()) {

            Arrays.fill(mul, 1);
            Arrays.fill(div, 1);

            for (int[] query : entry.getValue()) {
                mul[query[0]] = (mul[query[0]] * query[3]) % mod;
                int next = ((query[1] - query[0]) / entry.getKey()) + 1;

                int stopIdx = query[0] + (next * entry.getKey());
                if (stopIdx < n + 1) {
                    long invQueryVal = power(query[3], mod - 2);
                    div[stopIdx] = (div[stopIdx] * invQueryVal) % mod;
                }
            }

            for (int i = 0; i < n; i++) {
                if (i < entry.getKey()) {
                    long totalMultiplier = (mul[i] * div[i]) % mod;
                    nums[i] = (int) ((nums[i] * totalMultiplier) % mod);
                } else {
                    mul[i] = (mul[i] * mul[i - entry.getKey()]) % mod;
                    div[i] = (div[i] * div[i - entry.getKey()]) % mod;
                    long totalMultiplier = (mul[i] * div[i]) % mod;
                    nums[i] = (int) ((nums[i] * totalMultiplier) % mod);
                }
            }
        }
        return Arrays.stream(nums).reduce(0, (a, b) -> a ^ b);
    }

    private void apply(int[] nums, int[] query) {
        for (int i = query[0]; i <= query[1]; i += query[2]) {
            nums[i] = (int) ((1l * nums[i] * query[3]) % mod);
        }
    }
}