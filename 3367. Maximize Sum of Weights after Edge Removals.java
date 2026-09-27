import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    class Node {
        long takenValue;
        long notTakenValue;
        long diff;
        int node;

        public Node(long takenValue, long notTakenValue, int node) {
            this.takenValue = takenValue;
            this.notTakenValue = notTakenValue;
            this.diff = takenValue - notTakenValue;
            this.node = node;
        }

    }

    private Long helper(int node, ArrayList<int[]> graph[], int takenParentNode, Long[][] dp, int k, int parent) {
        if (dp[node][takenParentNode] != null) {
            return dp[node][takenParentNode];
        }

        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> Long.compare(b.diff, a.diff));

        for (int[] child : graph[node]) {
            if (child[0] == parent)
                continue;
            q.add(new Node(helper(child[0], graph, 1, dp, k, node) + child[1],
                    helper(child[0], graph, 0, dp, k, node), child[0]));
        }

        if (takenParentNode == 1) {
            k--;
        }
        long ans = 0;
        while (q.size() > 0) {
            Node poll = q.poll();
            if (k > 0 && poll.diff > 0) {
                ans += poll.takenValue;
                k--;
            } else {
                ans += poll.notTakenValue;
            }
        }
        return dp[node][takenParentNode] = ans;

    }

    public long maximizeSumOfWeights(int[][] edges, int k) {
        int n = edges.length + 1;
        ArrayList<int[]> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[] { edge[1], edge[2] });
            graph[edge[1]].add(new int[] { edge[0], edge[2] });
        }

        Long[][] dp = new Long[n][2];

        return helper(0, graph, 0, dp, k, -1);
    }
}