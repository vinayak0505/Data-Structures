import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    private void fillMinDis(ArrayList<int[]> graph[], int[] array, int from) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        Arrays.fill(array, Integer.MAX_VALUE);
        array[from] = 0;
        pq.add(new int[] { from, 0 });

        while (pq.size() > 0) {
            int[] poll = pq.poll();
            for (int[] child : graph[poll[0]]) {
                int newWeight = child[1] + poll[1];
                if (array[child[0]] <= newWeight)
                    continue;
                array[child[0]] = newWeight;
                pq.add(new int[] { child[0], newWeight });
            }
        }
    }

    public boolean[] findAnswer(int n, int[][] edges) {
        ArrayList<int[]> graph[] = new ArrayList[n];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[] { edge[1], edge[2] });
            graph[edge[1]].add(new int[] { edge[0], edge[2] });
        }

        int[] minDisFromStart = new int[n];
        fillMinDis(graph, minDisFromStart, 0);
        int[] minDisFromEnd = new int[n];
        fillMinDis(graph, minDisFromEnd, n - 1);

        int e = edges.length;
        boolean ans[] = new boolean[e];
        for (int i = 0; i < e; i++) {
            ans[i] = isValid(edges[i], minDisFromStart, minDisFromEnd);
        }

        return ans;
    }

    private boolean isValid(int[] edge, int[] minDisFromStart, int[] minDisFromEnd) {
        int n = minDisFromStart.length;
        int totalDis = minDisFromStart[n - 1];
        if (totalDis == Integer.MAX_VALUE) {
            return false;
        }
        if (minDisFromStart[edge[0]] != Integer.MAX_VALUE &&
                minDisFromEnd[edge[1]] != Integer.MAX_VALUE &&
                totalDis == (edge[2] + minDisFromStart[edge[0]] + minDisFromEnd[edge[1]])) {
            return true;
        }
        return (minDisFromStart[edge[1]] != Integer.MAX_VALUE &&
                minDisFromEnd[edge[0]] != Integer.MAX_VALUE &&
                totalDis == (edge[2] + minDisFromStart[edge[1]] + minDisFromEnd[edge[0]]));
    }
}