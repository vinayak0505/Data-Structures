import java.util.ArrayList;

class Solution {

    private boolean isPossible(ArrayList<Integer> nums, int diff, int minNum, int maxNum) {
        int x = minNum + diff;
        int y = maxNum - diff;

        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) == -1 || nums.get(i + 1) == -1)
                continue;
            if (Math.abs(nums.get(i) - nums.get(i + 1)) > diff)
                return false;
        }

        if (nums.getFirst() == -1) {
            if (Math.abs(nums.get(1) - x) > diff && Math.abs(nums.get(1) - y) > diff) {
                return false;
            }
        }

        if (nums.getLast() == -1) {
            if (Math.abs(nums.get(nums.size() - 2) - x) > diff && Math.abs(nums.get(nums.size() - 2) - y) > diff) {
                return false;
            }
        }

        for (int i = 1; i < nums.size() - 1; i++) {
            if (nums.get(i) != -1 || nums.get(i - 1) == -1 || nums.get(i + 1) == -1)
                continue;

            if (Math.abs(nums.get(i - 1) - x) <= diff && Math.abs(nums.get(i + 1) - x) <= diff)
                continue;

            if (Math.abs(nums.get(i - 1) - y) <= diff && Math.abs(nums.get(i + 1) - y) <= diff)
                continue;

            return false;
        }

        for (int i = 1; i < nums.size() - 2; i++) {
            if (nums.get(i) != -1 || nums.get(i + 1) != -1)
                continue;

            if (Math.abs(nums.get(i - 1) - x) <= diff && Math.abs(nums.get(i + 2) - x) <= diff)
                continue;

            if (Math.abs(nums.get(i - 1) - y) <= diff && Math.abs(nums.get(i + 2) - y) <= diff)
                continue;

            if (y - x <= diff && Math.max(nums.get(i - 1), nums.get(i + 2)) - y <= diff
                    && x - Math.min(nums.get(i - 1), nums.get(i + 2)) <= diff)
                continue;

            return false;
        }

        return true;
    }

    public int minDifference(int[] nums) {
        ArrayList<Integer> newNums = new ArrayList<>();
        int minum1Count = 0;
        int minNumber = Integer.MAX_VALUE;
        int maxNumber = Integer.MIN_VALUE;
        int minDiff = 0;
        int prev = -1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == -1) {
                minum1Count++;
            } else {
                minum1Count = 0;
                if ((i > 0 && nums[i - 1] == -1) || (i < nums.length - 1 && nums[i + 1] == -1)) {
                    minNumber = Math.min(minNumber, num);
                    maxNumber = Math.max(maxNumber, num);
                }

            }
            if (minum1Count > 2) {
                prev = num;
                continue;
            }
            newNums.add(num);
            if (prev != -1 && num != -1) {
                minDiff = Math.max(minDiff, Math.abs(num - prev));
            }
            prev = num;
        }
        if(minNumber == Integer.MAX_VALUE){
            int ans = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                ans = Math.max(ans, Math.abs(nums[i] - nums[i + 1]));
            }
            return ans;
        }

        if (newNums.size() <= 1) {
            return 0;
        }
        if (newNums.get(0) == -1 && newNums.get(1) == -1) {
            newNums.removeFirst();
        }
        if (newNums.size() <= 1) {
            return 0;
        }
        if (newNums.get(newNums.size() - 1) == -1 && newNums.get(newNums.size() - 2) == -1) {
            newNums.removeLast();
        }
        if (newNums.size() <= 1) {
            return 0;
        }

        int maxDiff = Math.max(minDiff,  maxNumber - minNumber);
        System.out.println(maxNumber + " " + minNumber + " " + minDiff);
        System.out.println(newNums.toString());
        int ans = maxDiff;
        while (minDiff <= maxDiff) {
            int mid = (maxDiff + minDiff) / 2;
            if (isPossible(newNums, mid, minNumber, maxNumber)) {
                ans = mid;
                maxDiff = mid - 1;
            } else {
                minDiff = mid + 1;
            }
        }

        return ans;

    }
}