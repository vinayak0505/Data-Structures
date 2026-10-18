import java.util.Arrays;
import java.util.HashSet;

class Solution {
    private boolean isValid(int mid, int[] nums, int[] changeIndices) {
        HashSet<Integer> st = new HashSet<>();
        for (int i = 0; i <= mid; i++) {
            st.add(changeIndices[i]);
        }
        if(st.size() != nums.length){
            return false;
        }
        int[] newNums = new int[nums.length + 1];

        for (int i = 0; i <= mid; i++) {
            newNums[changeIndices[i]] = i;
        }

        int count = 0;
        for (int i = 0; i <= mid; i++) {
            if (newNums[changeIndices[i]] == i) {
                if (nums[changeIndices[i] - 1] > count) {
                    return false;
                }
                count -= nums[changeIndices[i] - 1];
            } else {
                count++;
            }
        }

        return true;
    }

    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int l = Arrays.stream(nums).reduce(0, Integer::sum) + nums.length - 1;
        int r = changeIndices.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            System.out.println(mid + " " + isValid(mid, nums, changeIndices));
            if (isValid(mid, nums, changeIndices)) {
                ans = mid + 1;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}