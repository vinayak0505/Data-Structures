import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    class Node{
        int node;
        int weight;
        int heavyCount;
        public Node(int node, int weight){
            this.node = node;
            this.weight = weight;
        }

        public Node(int node, int weight, int heavyCount){
            this.node = node;
            this.weight = weight;
            this.heavyCount = heavyCount;
        }
    }

    private boolean isValid(ArrayList<Node> graph[], int start, int end, int k, int threshold){
        int n = graph.length;
        int minHeavy[] = new int[n];
        Arrays.fill(minHeavy, Integer.MAX_VALUE);
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> b.heavyCount - a.heavyCount);
        q.add(new Node(start, 0, 0));
        minHeavy[start] = 0;
        while(q.size() > 0){
            Node par = q.poll();
            if(par.node == end) return true;
            if (par.heavyCount > minHeavy[par.node]) {
                continue;
            }
            for(Node next: graph[par.node]){
                int isHeavy = (next.weight > threshold) ? 1 : 0;
                int nextHeavyCount = par.heavyCount + isHeavy;
                
                if (nextHeavyCount <= k && nextHeavyCount < minHeavy[next.node]) {
                    minHeavy[next.node] = nextHeavyCount;
                    q.add(new Node(next.node, next.weight, nextHeavyCount));
                }
            }
        }
        return false;
    }

    public int minimumThreshold(int n, int[][] edges, int source, int target, int k) {
        int max = (int) 1e9;
        int low = 0;

        ArrayList<Node> graph[] = new ArrayList[n];
        for(int i = 0;i<n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0;i<edges.length;i++){
            graph[edges[i][0]].add(new Node(edges[i][1], edges[i][2]));
            graph[edges[i][1]].add(new Node(edges[i][0], edges[i][2]));
        }
        int ans = -1;
        while(low <= max){
            int mid = (max + low) / 2;
            if(isValid(graph, source, target, k, mid)){
                ans = mid;
                max = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
    }
}