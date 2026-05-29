import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    private boolean isValid(int size, int[] nums) {
        HashMap<Long, ArrayList<Integer>> mp = new HashMap<>();
        long hash = 0;
        long power = 1;
        for (int i = 0; i < size - 1; i++) {
            hash = hash * 10 + nums[i];
            power *= 10;
        }

        for (int i = size - 1; i < nums.length; i++) {
            hash = hash * 10 + nums[i];
            if (mp.containsKey(hash) == false) {
                mp.put(hash, new ArrayList<>());
            }
            mp.get(hash).add(i);
            hash -= nums[i - size + 1] * power;

        }

        for (ArrayList<Integer> i : mp.values()) {
            if (i.size() == 1)
                return true;
        }
        return false;
    }

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int low = 1, high = n;
        int ans = high;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isValid(mid, nums)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}