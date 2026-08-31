import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    int dp[][];
    Node nodes[];

    class Edge {
        public int node;
        public int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    class Node {
        public int weight; // total weight
        public int height; // total Height

        public Node(int weight, int height) {
            this.weight = weight;
            this.height = height;
        }
    }

    private void fillNode(int node, int parent, ArrayList<Edge> graph[], int totalHeight, int totalWeight) {
                System.out.println(node);
        dp[0][node] = parent;
        nodes[node] = new Node(totalWeight, totalHeight);

        for (Edge child : graph[node]) {
            if (child.node == parent)
                continue;
            fillNode(child.node, node, graph, totalHeight + 1, totalWeight + child.weight);
        }
    }

    public static double log2(double x) {
        return Math.log(x) / Math.log(2.0);
    }

    private int moveUpBy(int a, int height) {
        for (int i = 0; i < dp.length; i++) {
            if ((height & (1 << i)) == 0)
                continue;
            a = dp[i][a];
        }
        return a;
    }

    private Edge commonParent(int a, int b) {
        int totalWeight = nodes[a].weight + nodes[b].weight;

        if (nodes[b].height > nodes[a].height) {
            int c = b;
            b = a;
            a = c;
        }

        int heighta = nodes[a].height;
        int heightb = nodes[b].height;
        a = moveUpBy(a, heighta - heightb);

        if (a == b) {
            return new Edge(a, totalWeight - (2 * nodes[a].weight));
        }

        for (int i = dp.length - 1; i >= 0; i--) {
            if (dp[i][a] != -1 && dp[i][a] != dp[i][b]) {
                a = dp[i][a];
                b = dp[i][b];
            }
        }
        a = dp[0][a];

        return new Edge(a, totalWeight - (2 * nodes[a].weight));

    }

    private void makeBinaryLiftingTree() {
        int n = dp.length;
        int m = dp[0].length;

        for (int i = 1; i < n; i++) {
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < m; j++) {
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = dp[i - 1][dp[i - 1][j]];
                }
            }
        }
    }

    public int[] minimumWeight(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        dp = new int[(int) log2(n) + 1][n];

        ArrayList<Edge> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        nodes = new Node[n];
        for (int[] edge : edges) {
            graph[edge[0]].add(new Edge(edge[1], edge[2]));
            graph[edge[1]].add(new Edge(edge[0], edge[2]));
        }

        fillNode(0, -1, graph, 0, 0);
        makeBinaryLiftingTree();

        int q = queries.length;
        int ans[] = new int[q];

        for (int i = 0; i < q; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            int c = queries[i][2];

            Edge ab = commonParent(a, b);
            Edge bc = commonParent(b, c);
            Edge ca = commonParent(c, a);
            System.out.println(ab.weight + " " + bc.weight + " " + ca.weight);

            ans[i] = commonParent(ab.node, c).weight + ab.weight;
            ans[i] = Math.min(ans[i], commonParent(bc.node, a).weight + bc.weight);
            ans[i] = Math.min(ans[i], commonParent(ca.node, b).weight + ca.weight);
        }

        return ans;

    }
}