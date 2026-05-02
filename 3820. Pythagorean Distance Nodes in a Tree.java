import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private int[] findDistanceFrom(int x, ArrayList<Integer> graph[]){
        int n = graph.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean vis[] = new boolean[n];
        int dis = 1;
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        
        queue.add(x);
        vis[x] = true;
        ans[x] = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0){
                int node = queue.poll();
                for(int next: graph[node]){
                    if(vis[next]) continue;

                    queue.add(next);
                    vis[next] = true;
                    ans[next] = dis;
                }
            }

            dis++;
        }
        return ans;
    }

    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        ArrayList<Integer> graph[] = new ArrayList[n + 1];
        for(int i = 0;i<n + 1;i++) {
            graph[i] = new ArrayList<>();
        }
        for(int edge[]: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int xDis[] = findDistanceFrom(x, graph);
        int yDis[] = findDistanceFrom(y, graph);
        int zDis[] = findDistanceFrom(z, graph);

        int ans = 0;

        for(int i = 0;i<n;i++){
            if(xDis[i] == -1 || yDis[i] == -1 || zDis[i] == -1) continue;
            if(xDis[i] * xDis[i] == yDis[i] * yDis[i] + zDis[i] * zDis[i]) ans++;
            else if(yDis[i] * yDis[i] == xDis[i] * xDis[i] + zDis[i] * zDis[i]) ans++;
            else if(zDis[i] * zDis[i] == xDis[i] * xDis[i] + yDis[i] * yDis[i]) ans++;
        }
        return ans;
    }
}