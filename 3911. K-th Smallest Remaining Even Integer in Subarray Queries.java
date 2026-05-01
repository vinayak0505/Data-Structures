class Solution {

    private int find(int ans, int[] nums, int l, int r) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == ans) {
                return mid;
            }
            if (ans > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    private int isValid(int ans, int k, int l, int r, int[] count, int[] nums) {
        r = find(ans, nums, l, r);
        int diff;
        if(r == -1){
            diff = 0;
        }else{
            diff = count[r] - (l == 0 ? 0 : count[l - 1]);
        }
        int ans_2 = (diff + k) * 2;
        if (ans > ans_2) {
            return -1;
        } else if (ans < ans_2) {
            return 1;
        }
        return 0;
    }

    private int solve(int l, int r, int k, int[] count, int[] nums) {
        int low = (2 * k);
        int high = low + ((r - l + 1) * 2);
        int ans = high;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (mid % 2 != 0)
                mid++; // enforce even
            
            int valid = isValid(mid, k, l, r, count, nums);
            
            if (valid == 1) {
                low = mid + 2;
            } else {
                ans = mid;
                high = mid - 2;
            }
        }
        
        return ans;
    }

    public int[] kthRemainingInteger(int[] nums, int[][] queries) {
        
        int n = nums.length;
        int count[] = new int[n];
        int prev = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0)
                prev++;
            count[i] = prev;
        }
        

        int query_length = queries.length;
        int ans[] = new int[query_length];
        for (int i = 0; i < query_length; i++) {
            ans[i] = solve(queries[i][0], queries[i][1], queries[i][2], count, nums);
        }
        return ans;
    }
}