import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    private long add;
    private int findForGroup(int groupId, ArrayList<Integer> graph[], int totalCount, 
        int node, int parent, int group[]){

        int ans = group[node] == groupId ? 1 : 0;

        for(int next: graph[node]){
            if(next == parent) continue;
            ans += findForGroup(groupId, graph, totalCount, next, node, group);
        }
        add += (long)ans * (totalCount - ans);
        return ans;
    }

    private HashMap<Integer, Integer> fillGroupCount(int group[]){
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int groupId: group){
            map.put(groupId, map.getOrDefault(groupId, 0) + 1);
        }
        return map;
    }

    public long interactionCosts(int n, int[][] edges, int[] group) {
        long ans = 0;

        ArrayList<Integer> graph[] = new ArrayList[n];
        for(int i = 0;i< n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        HashMap<Integer, Integer> map = fillGroupCount(group);

        for(int groupId: map.keySet()){
            add = 0;
            findForGroup(groupId, graph, map.get(groupId), 0, -1, group);
            ans += add;
        }
        return ans;
    }
}