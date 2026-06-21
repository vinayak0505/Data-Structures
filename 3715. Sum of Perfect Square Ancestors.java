import java.util.ArrayList;

class Solution {
    int max = (int) 1e5 + 1;

    private int[] updateNums(int[] nums) {
        int array[] = new int[max];
        int ans[] = new int[nums.length];

        for (int i = 2; i < max; i++) {
            if (array[i] == 0) {
                for (int j = i; j < max; j += i) {
                    array[j] = i;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int new_value = 1;
            while (value > 1 && array[value] != 0) {
                if (array[value] == array[value / array[value]]) {
                    value /= array[value] * array[value];
                } else {
                    new_value *= array[value];
                    value /= array[value];
                }
            }
            ans[i] = new_value;
        }

        return ans;
    }

    public long sumOfAncestors(int n, int[][] edges, int[] nums) {
        int[] updatedNums = updateNums(nums);

        ArrayList<Integer> tree[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        int dp[] = new int[max];

        return getSum(0, -1, tree, updatedNums, dp);

    }

    private long getSum(int node, int parent, ArrayList<Integer> tree[], int[] updatedNums,
            int dp[]) {
        long ans = 0;

        ans += dp[updatedNums[node]];
        dp[updatedNums[node]]++;
        for (int child : tree[node]) {
            if (child == parent)
                continue;
            ans += getSum(child, node, tree, updatedNums, dp);
        }
        dp[updatedNums[node]]--;
        return ans;
    }
}