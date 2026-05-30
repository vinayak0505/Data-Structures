import java.util.HashMap;

class Solution {

    private void update(int start, int end,int add ,int sqr, HashMap<Integer, Integer> freq[], int[] lazy, int[] nums2) {
        while (start <= end) {
            int sqrIndex = start/sqr;
            if (start % sqr == 0 && ((start / sqr) + 1) * sqr <= end) {
                lazy[start / sqr] += add;
                start += sqr;
            } else {
                int oldValue = nums2[start];
                freq[sqrIndex].put(oldValue, freq[sqrIndex].getOrDefault(oldValue, 0) - 1);
                if (freq[sqrIndex].get(oldValue) == 0)
                    freq[sqrIndex].remove(oldValue);
                nums2[start] += add;
                freq[sqrIndex].put(nums2[start], freq[sqrIndex].getOrDefault(nums2[start], 0) + 1);
                start++;
            }
        }
    }

    private int find(int target, int sqr, int[] nums2, int[] nums1, int[] lazy, HashMap<Integer, Integer> freq[]) {
        int count = 0;
        for (int num: nums1) {
            for (int i = 0; i < sqr; i++) {
                int needed = target - num - lazy[i];
                count += freq[i].getOrDefault(needed, 0);
            }
        }
        return count;
    }

    public int[] numberOfPairs(int[] nums1, int[] nums2, int[][] queries) {
        int queryOfType2Lenght = 0;
        for (int[] query : queries) {
            if (query[0] == 2)
                queryOfType2Lenght++;
        }

        int[] ans = new int[queryOfType2Lenght];

        int n = nums2.length;
        int sqr = (int) Math.sqrt(n) + 1;

        int[] lazy = new int[sqr];
        HashMap<Integer, Integer> freq[] = new HashMap[sqr + 1];

        for (int i = 0; i < freq.length; i++) {
            freq[i] = new HashMap<>();
            int start = i * sqr;
            int end = start + sqr;
            for (int j = start; j < end && j < n; j++) {
                int val = nums2[j];
                freq[i].put(val, freq[i].getOrDefault(val, 0) + 1);
            }
        }

        int ansCount = 0;
        for (int[] querie : queries) {
            if (querie[0] == 1) {
                update(querie[1], querie[2],querie[3], sqr, freq, lazy, nums2);
            } else {
                ans[ansCount] = find(querie[1], sqr, nums2, nums1, lazy, freq);
                ansCount++;
            }
        }

        return ans;
    }
}