import java.util.ArrayList;

class Solution {

    long weightFromTop[];
    int heightFromTop[];
    int parentDpArray[][];

    class WeightedEdge {
        int neighbor;
        long edgeWeight;

        public WeightedEdge(int neighbor, int edgeWeight) {
            this.neighbor = neighbor;
            this.edgeWeight = edgeWeight;
        }

    }

    public static int log2(double x) {
        return (int) (Math.log(x) / Math.log(2)) + 1;
    }

    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        ArrayList<WeightedEdge> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            graph[b].add(new WeightedEdge(a, edge[2]));
            graph[a].add(new WeightedEdge(b, edge[2]));
        }

        weightFromTop = new long[n];
        heightFromTop = new int[n];
        parentDpArray = new int[log2(n)][n];

        buildTreeMetadata(0, -1, 0, 0, graph);

        buildAncestorTable();
        int qsize = queries.length;
        int ans[] = new int[qsize];

        for (int i = 0; i < qsize; i++) {
            int u = queries[i][0], v = queries[i][1];

            int parent = findLowestCommonAncestor(u, v);

            long totalDis = weightFromTop[u] + weightFromTop[v] - (2 * weightFromTop[parent]);
            int totalHeight = heightFromTop[u] + heightFromTop[v] - (2 * heightFromTop[parent]);

            int l = 0;
            int r = totalHeight;

            long findWeight = (totalDis + 1) / 2;
            int uHeight = heightFromTop[u] - heightFromTop[parent];

            ans[i] = u;

            while (l <= r) {
                int mid = (r - l) / 2 + l;
                long diff;
                int midParent;
                if (mid <= uHeight) {
                    midParent = getKthAncestor(u, mid);
                    diff = weightFromTop[u] - weightFromTop[midParent];
                } else {
                    midParent = getKthAncestor(v, totalHeight - mid);
                    diff = totalDis - (weightFromTop[v] - weightFromTop[midParent]);
                }

                if (diff >= findWeight) {
                    ans[i] = midParent;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return ans;
    }

    private void buildAncestorTable() {
        int h = parentDpArray.length;
        int n = parentDpArray[0].length;

        for (int i = 1; i < h; i++) {
            for (int j = 0; j < n; j++) {
                if (parentDpArray[i - 1][j] == -1)
                    continue;
                parentDpArray[i][j] = parentDpArray[i - 1][parentDpArray[i - 1][j]];
            }
        }
    }

    private int getKthAncestor(int u, int mid) {
        int max = parentDpArray.length;

        for (int i = 0; i < max; i++) {
            if (((1 << i) & mid) == 0)
                continue;
            u = parentDpArray[i][u];
        }
        return u;
    }

    private int findLowestCommonAncestor(int u, int v) {
        if (heightFromTop[v] > heightFromTop[u]) {
            int a = u;
            u = v;
            v = a;
        }
        int diff = heightFromTop[u] - heightFromTop[v];
        u = getKthAncestor(u, diff);

        if (u == v)
            return u;

        int max = parentDpArray.length;
        for (int i = max - 1; i >= 0; i--) {
            if (parentDpArray[i][u] != -1 && parentDpArray[i][u] != parentDpArray[i][v]) {
                u = parentDpArray[i][u];
                v = parentDpArray[i][v];
            }
        }
        return parentDpArray[0][u];
    }

    private void buildTreeMetadata(int WeightedEdge, int parent, long edgeWeight, int height,
            ArrayList<Solution.WeightedEdge>[] graph) {
        parentDpArray[0][WeightedEdge] = parent;
        weightFromTop[WeightedEdge] = edgeWeight;
        heightFromTop[WeightedEdge] = height;

        for (WeightedEdge next : graph[WeightedEdge]) {
            if (next.neighbor == parent)
                continue;
            buildTreeMetadata(next.neighbor, WeightedEdge, edgeWeight + next.edgeWeight, height + 1, graph);
        }
    }
}