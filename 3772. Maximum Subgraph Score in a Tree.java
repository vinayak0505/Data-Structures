import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    int ans[];
    int parent[];
    Integer parentans[];

    private int helper(int node, int par, ArrayList<Integer> graph[], int[] good){
        ans[node] = good[node];
        parent[node] = par;

        for(int next: graph[node]){
            if(next == par) continue;
            int value = helper(next, node, graph, good);
            if(value > 0){
                ans[node] += value;
            }
        }
        return ans[node];
    }

    private int fillParent(int node, ArrayList<Integer> graph[], int[] good){
        if(parent[node] == -1) {
            parentans[node] = ans[node];
        }
        if(parentans[node] != null) return parentans[node];
        
        int parentvalue = fillParent(parent[node], graph, good);
        if(parentvalue > ans[node]){
            if(ans[node] >= 0){
                parentans[node] = parentvalue;
            }else{
                parentans[node] = parentvalue - 1;
            }
        }else{
            parentans[node] = ans[node];
        }
        return parentans[node];
    }

    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        ArrayList<Integer> graph[] = new ArrayList[n];

        for(int i = 0;i<n;i++){
            if(good[i] == 0) good[i] = -1;
        }

        for(int i = 0; i< n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        ans = new int[n];
        parent = new int[n];
        parentans = new Integer[n];
        helper(0, -1, graph, good);

        for (int i = 0; i < graph.length; i++) {
            fillParent(i, graph, good);
        }

        return Arrays.stream(parentans).mapToInt(Integer::intValue)
                               .toArray();
    }
}