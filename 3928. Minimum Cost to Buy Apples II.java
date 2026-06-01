import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    class Node{
        public int a;
        public int cost;
        public Node(int a, int cost){
            this.a = a;
            this.cost = cost;
        }
    }
    
    private void fillMinDis(int node, int n, ArrayList<Node> graph[],Integer dis[], int maxCost){
        boolean visited[] = new boolean[n];
        Queue<Node> q = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        q.add(new Node(node, 0));
        while(!q.isEmpty()){
            Node curr = q.poll();
            if(visited[curr.a]) continue;
            visited[curr.a] = true;
            dis[curr.a] = curr.cost;
            for(Node next : graph[curr.a]){
                if(visited[next.a]) continue;
                if(curr.cost + next.cost <= maxCost && (curr.cost + next.cost >= 0)){
                    q.add(new Node(next.a, curr.cost + next.cost));
                }
            }
        }
    }

    private void fillDis(Integer dis[], Integer distax[], int node, int n, int[][] roads, int price){
        ArrayList<Node> graph[] = new ArrayList[n];
        for(int i = 0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0;i<roads.length;i++){
            graph[roads[i][0]].add(new Node(roads[i][1], roads[i][2]));
            graph[roads[i][1]].add(new Node(roads[i][0], roads[i][2]));
        }
        fillMinDis(node, n, graph, dis, price);

        graph = new ArrayList[n];
        for(int i = 0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0;i<roads.length;i++){
            graph[roads[i][0]].add(new Node(roads[i][1], roads[i][3] * roads[i][2]));
            graph[roads[i][1]].add(new Node(roads[i][0], roads[i][3] * roads[i][2]));
        }
        fillMinDis(node, n, graph, distax, price);
    }

    private int findans(int i, int n, int[] prices, int[][] roads){
        Integer dis[] = new Integer[n];
        Integer distax[] = new Integer[n];

        fillDis(dis, distax, i, n, roads, prices[i]);
        
        int ans = prices[i];
        for(int j = 0; j < n; j++){
            if(dis[j] == null || distax[j] == null) continue;
            ans = Math.min(ans, dis[j] + distax[j] + prices[j]);
        }
        return ans;
    }

    public int[] minCost(int n, int[] prices, int[][] roads) {
        int ans[] = new int[n];

        for(int i = 0;i<n;i++){
            ans[i] = findans(i, n, prices, roads);
        }
        
        return ans;
    }
}