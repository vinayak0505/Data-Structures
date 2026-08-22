import java.util.ArrayList;

class Solution {

    public int maxLen(int n, int[][] edges, String label) {

        Integer memo[][][] = new Integer[n][n][1 << n];

        ArrayList<Integer> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];

            graph[a].add(b);
            graph[b].add(a);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, solve(i, i, 1 << i, graph, label, memo) - 1);
        }

        for (int[] edge : edges) {
            int left = edge[0];
            int right = edge[1];
            // if(label.charAt(left) != label.charAt(right)) continue;
            int ans_val = solve(left, right, (1 << left) | (1 << right), graph, label, memo);
            ans = Math.max(ans, ans_val);
        }

        return ans;
    }

    private int solve(int u, int v, int mask, ArrayList<Integer>[] graph, String label, Integer[][][] memo) {
        if (memo[u][v][mask] != null) {
            return memo[u][v][mask];
        }
        if (label.charAt(u) != label.charAt(v))
            return memo[u][v][mask] = 0;

        int ans = 0;
        for (int childA : graph[u]) {
            if (((1 << childA) & mask) != 0)
                continue;
            for (int childB : graph[v]) {
                if (childA == childB)
                    continue;
                if (((1 << childB) & mask) != 0)
                    continue;
                int pos_ans = solve(childA, childB, mask | (1 << childB) | (1 << childA), graph, label, memo);
                ans = Math.max(ans, pos_ans);
            }
        }
        return memo[u][v][mask] = ans + 2;
    }
}