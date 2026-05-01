import java.util.Deque;
import java.util.LinkedList;

class Solution {

    public long countSubarrays(int[] nums, long k) {
        long ans = 0;
        int left = 0;
        Deque<Integer> minStack = new LinkedList<>(), maxStack = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            while(minStack.isEmpty() == false && nums[minStack.peekLast()] >= nums[i]) 
                minStack.pollLast();
            minStack.add(i);

            while(maxStack.isEmpty() == false && nums[maxStack.peekLast()] <= nums[i])
                maxStack.pollLast();
            maxStack.add(i);


            long min = nums[minStack.peekFirst()];
            long max = nums[maxStack.peekFirst()];
            while ((max - min) * (long)(i - left + 1) > k) {
                if(minStack.peekFirst() == left) minStack.pollFirst();
                if(maxStack.peekFirst() == left) maxStack.pollFirst();
                left++;
                min = nums[minStack.peekFirst()];
                max = nums[maxStack.peekFirst()];
            }
            ans += (long)(i - left + 1);
        }
        return ans;
    }
}