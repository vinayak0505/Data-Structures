import java.util.ArrayList;
import java.util.List;

class Solution {

    private void removeBit(List<Integer> nums, int bit) {
        int mask = Integer.MAX_VALUE ^ (1 << bit);
        for (int i = 0; i < nums.size(); i++) {
            nums.set(i, nums.get(i) & mask);
        }
    }

    public int maximumAND(int[] _nums, int k, int m) {
        List<Integer> nums = new ArrayList<>(_nums.length);
        for (int num : _nums) {
            nums.add(num);
        }
        int ans = 0;
        for (int bit = 31; bit >= 0; bit--) {
            nums.sort((a, b) -> b - a);
            long amountNeeded = 0;
            int numNeeded = (1 << bit);
            for (int i = 0; i < m; i++) {
                if ((nums.get(i) & (1 << bit)) == 0) {
                    amountNeeded += (numNeeded - nums.get(i));
                    if(amountNeeded > k) break;
                }
            }
            if (amountNeeded <= k) {
                int firstIndexWhereBitIsNotSet = m;
                for (int i = m; i < nums.size(); i++) {
                    if ((nums.get(i) & (1 << bit)) == 0) {
                        firstIndexWhereBitIsNotSet = i;
                        break;
                    }
                }
                
                for (int i = 0; i < m; i++) {
                    if (nums.get(i) < numNeeded)
                        nums.set(i, 0);
                }
                removeBit(nums, bit);

                
                if (nums.size() > firstIndexWhereBitIsNotSet) {
                    nums = new ArrayList<>(nums.subList(0, firstIndexWhereBitIsNotSet));
                }
                k -= amountNeeded;
                ans |= numNeeded;
            } else {
                removeBit(nums, bit);
            }

        }

        return ans;
    }
}