import java.util.ArrayList;

class Solution {
	private int getMinDiff(int[] nums, int plus, int minNum, ArrayList<Integer> minq, int maxNum) {
		boolean min = true;
		for (int i = 0; i < minq.size(); i++) {
			if (Math.abs(nums[minq.get(i)] & 1) == ((minq.get(i) + plus) & 1)) {
				min = false;
			}
		}

		int maxNum2 = Integer.MIN_VALUE;
		int minNum2 = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (Math.abs(nums[i] & 1) != ((i + plus) & 1)) {
				if (nums[i] == maxNum) {
					maxNum2 = Math.max(maxNum2, nums[i] - 1);
					minNum2 = Math.min(minNum2, nums[i] - 1);
				} else if (nums[i] == minNum) {
					maxNum2 = Math.max(maxNum2, nums[i] + 1);
					minNum2 = Math.min(minNum2, nums[i] + 1);
				} else if (min == false) {
					maxNum2 = Math.max(maxNum2, nums[i] - 1);
					minNum2 = Math.min(minNum2, nums[i] - 1);
				} else {
					maxNum2 = Math.max(maxNum2, nums[i] + 1);
					minNum2 = Math.min(minNum2, nums[i] + 1);
				}
			} else {
				minNum2 = Math.min(minNum2, nums[i]);
				maxNum2 = Math.max(maxNum2, nums[i]);
			}

		}

		return maxNum2 - minNum2;
	}

	public int[] makeParityAlternating(int[] nums) {
		int n = nums.length;
		int ans = 0;
		int minNum = Integer.MAX_VALUE;
		int maxNum = Integer.MIN_VALUE;
		ArrayList<Integer> minq = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			int num = nums[i];
			if (Math.abs(num & 1) != (i & 1))
				ans++;
			if (num < minNum) {
				minNum = num;
				minq = new ArrayList<>(n);
			}
			if (num == minNum) {
				minq.add(i);
			}

			if (num > maxNum) {
				maxNum = num;
			}
		}
		int ans2 = 0;
		// System.out.println("ans " + ans + " length " + nums.length);
		if (ans * 2 == nums.length) {
			int a = getMinDiff(nums, 0, minNum, minq, maxNum);
			int a2 = getMinDiff(nums, 1, minNum, minq, maxNum);
			// System.out.println("a " + a + " a2 " + a2);
			ans2 = Math.min(a, a2);
		} else if (ans * 2 > nums.length) {
			ans2 = getMinDiff(nums, 1, minNum, minq, maxNum);
		} else {
			ans2 = getMinDiff(nums, 0, minNum, minq, maxNum);
		}
		return new int[] { Math.min(ans, nums.length - ans), ans2 };

	}
}