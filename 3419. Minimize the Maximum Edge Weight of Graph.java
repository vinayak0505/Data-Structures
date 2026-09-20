import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {

    class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public int minMaxWeight(int n, int[][] edges, int threshold) {
        ArrayList<Node> graph[] = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][1]].add(new Node(edges[i][0], edges[i][2]));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        pq.add(new Node(0, 0));
        boolean vis[] = new boolean[n];
        int visCount = 0;
        int ans = 0;

        while (pq.isEmpty() == false) {
            Node head = pq.poll();
            if (vis[head.to])
                continue;
            vis[head.to] = true;
            ans = Math.max(ans, head.weight);
            visCount++;
            if (visCount == n)
                return ans;

            for (Node toNode : graph[head.to]) {
                if (vis[toNode.to])
                    continue;
                pq.add(toNode);
            }
        }
        if (visCount == n) {
            return ans;
        }
        return -1;
    }
}