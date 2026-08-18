import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public int minJumps(int[] nums) {
        int n = nums.length;
        boolean[] vis = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        int maxValue = 0;
        for (int i : nums) {
            maxValue = Math.max(maxValue, i);
        }

        boolean[] isPrime = fillPrimes(nums, maxValue + 1);

        HashMap<Integer, ArrayList<Integer>> mp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            mp.putIfAbsent(nums[i], new ArrayList<>());
            mp.get(nums[i]).add(i);
        }

        int ans = 0;
        queue.add(0);
        vis[0] = true;
        while (queue.size() > 0) {
            int count = queue.size();
            while (count > 0) {
                count--;
                int i = queue.poll();
                if (i == n - 1)
                    return ans;
                if (vis[i + 1] == false) {
                    queue.add(i + 1);
                    vis[i + 1] = true;
                }
                if (i > 0 && vis[i - 1] == false) {
                    queue.add(i - 1);
                    vis[i - 1] = true;
                }

                if (nums[i] < 0 || nums[i] >= isPrime.length || !isPrime[nums[i]])
                    continue;
                for (int j = nums[i]; j <= maxValue; j += nums[i]) {
                    if (mp.containsKey(j) == false)
                        continue;
                    for (int pos : mp.get(j)) {
                        if (vis[pos] == false) {
                            vis[pos] = true;
                            queue.add(pos);
                        }
                    }
                    mp.remove(j);
                }
                isPrime[nums[i]] = false;
            }
            ans++;
        }
        return -1;
    }

    private boolean[] fillPrimes(int[] nums, int LIMIT) {

        boolean[] isPrime = new boolean[LIMIT];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i < LIMIT; i++) {
            if (!isPrime[i])
                continue;
            for (int j = i + i; j < LIMIT; j += i) {
                isPrime[j] = false;
            }
        }

        return isPrime;
    }
}