import java.util.ArrayList;

class Solution {
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int d1 = getDiameter(edges1);
        int d2 = getDiameter(edges2);

        int r1 = (d1 + 1) / 2;
        int r2 = (d2 + 1) / 2;

        return Math.max(Math.max(d1, d2), r1 + r2 + 1);
    }

    // min height, diameter
    private int getDiameter(int[][] edges) {
        int n = edges.length + 1;
        ArrayList<Integer> graph[] = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }

        int[] solve = solve(graph, 0, -1);
        return solve[0];
    }

    // diameter, maxHeightIf0AtTop
    private int[] solve(ArrayList<Integer>[] graph, int node, int parent) {
        int maxHeight = 0;
        int secondMaxHeight = 0;
        int diameter = 0;
        for (int child : graph[node]) {
            if (child == parent)
                continue;
            int[] value = solve(graph, child, node);
            diameter = Math.max(diameter, value[0]);
            if (value[1]+ 1 > maxHeight) {
                secondMaxHeight = maxHeight;
                maxHeight = value[1] + 1;
            } else if (value[1]+1 > secondMaxHeight) {
                secondMaxHeight = value[1] + 1;
            }
        }
        diameter = Math.max(diameter, maxHeight + secondMaxHeight);
        return new int[] { diameter, maxHeight };
    }

}