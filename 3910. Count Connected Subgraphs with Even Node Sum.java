import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int ans;

    private boolean isValid(int[] nums,boolean[] taken, ArrayList<Integer> edges[]){
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if(taken[i]) sum += nums[i];
        }
        if((sum & 1) > 0) return false;
        boolean one = false;
        boolean[] vis = new boolean[n];
        for(int i = 0;i<n;i++){
            if(taken[i] == false || vis[i]) continue;
            if(one) return false;
            one = true;

            Queue<Integer> q = new LinkedList<>();

            q.add(i);
            vis[i] = true;

            while(q.size() > 0){
                for(int next: edges[q.peek()]){
                    if(taken[next] && vis[next] == false){
                        q.add(next);
                        vis[next] = true;
                    }
                }
                q.poll();
            }
        }
        return one;
    }

    private void print(int[] nums, boolean[] taken, boolean ans){
        System.out.println("Subset:");
        for (int i = 0; i < nums.length; i++) {
            if (taken[i]) {
                System.out.print("for index " + i + " " + nums[i] + " ");
            }
        }
        System.out.println("\nIs Valid: " + ans);
    }

    private int helper(int[] nums, int i, boolean[] taken, ArrayList<Integer> edges[]){
        int n = nums.length;
        if(i == n){
            boolean valid = isValid(nums, taken, edges);
            print(nums, taken, valid);
            return valid ? 1: 0;
            
        }
        int ans = helper(nums, i + 1, taken, edges);
        taken[i] = true;
        ans += helper(nums, i + 1, taken, edges);
        taken[i] = false;
        return ans;

    }
    public int evenSumSubgraphs(int[] nums, int[][] edges) {
        int n = nums.length;
        boolean[] taken = new boolean[n];
        ArrayList<Integer> graph[] = new ArrayList[n];
        for(int i = 0;i<n;i++){
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }
        return helper(nums, 0, taken, graph);
    }
}