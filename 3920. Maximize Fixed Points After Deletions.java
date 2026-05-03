import java.util.ArrayList;
import java.util.Collections;

class Solution {

    class Pair {
        int val;
        int diff;
        
        Pair(int val, int diff) {
            this.val = val;
            this.diff = diff;
        }
    }

    private int upperBound(ArrayList<Integer> list, int value) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = l + (r - l) / 2; // Prevents overflow
            if (list.get(mid) <= value) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private int helper(ArrayList<Integer> diff) {
        int n = diff.size();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int value = diff.get(i);
            int pos = upperBound(array, value);
            if (array.size() == pos)
                array.add(value);
            else
                array.set(pos, value);
        }
        return array.size();
    }

    public int maxFixedPoints(int[] nums) {
        int n = nums.length;
        ArrayList<Pair> array = new ArrayList<>(n);
        for (int i = 0; i < nums.length; i++) {
            if (i >= nums[i]) {
                array.add(new Pair(nums[i], i - nums[i]));
            }
        }

        Collections.sort(array, (a, b) -> {
            if(a.val == b.val){
                return b.diff - a.diff;
            }
            return a.val - b.val;
        });
        ArrayList<Integer> validDiffs = new ArrayList<>(n);
        for (Pair p : array) {
            validDiffs.add(p.diff);
        }
        return helper(validDiffs);
    }
}