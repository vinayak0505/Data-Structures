import java.util.ArrayList;

class Solution {
    public long countAlternatingSubarrays(int[] nums) {
        long ans = 0;
        ArrayList<Integer> newnums = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            newnums.add(nums[i]);
        }
        newnums.add(newnums.getLast());
        System.out.println(newnums.toString());
        int count = 0;
        long mul = 0;
        for (int i = 0; i < nums.length; i++) {
            count++;
            mul += count;
            if (newnums.get(i) == newnums.get(i + 1)) {
                ans += mul;
                mul = 0;
                count = 0;
            }
        }
        return ans;
    }
}