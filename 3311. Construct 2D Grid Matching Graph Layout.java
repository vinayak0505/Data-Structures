import java.util.ArrayList;

class Solution {
    private int[][] make1DGraph(ArrayList<Integer> graph[], int startWith, boolean[] vis) {
        int n = graph.length;
        int[][] ans = new int[1][n];
        ans[0][0] = startWith;
        for (int i = 0; i < n - 1; i++) {
            vis[ans[0][i]] = true;
            for (int child : graph[ans[0][i]]) {
                if (vis[child])
                    continue;
                ans[0][i + 1] = child;
            }
        }
        return ans;
    }

    public int[][] constructGridLayout(int n, int[][] edges) {
        ArrayList<Integer> graph[] = new ArrayList[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        if (edges.length == n - 1) {
            for (int i = 0; i < n; i++) {
                if (graph[i].size() == 1) {
                    return make1DGraph(graph, i, vis);
                }
            }
        }

        int startWith = -1;

        for (int i = 0; i < n; i++) {
            if (graph[i].size() == 2) {
                startWith = i;
                break;
            }
        }

        int e = edges.length;
        int b = e - 2 * n;
        int x = (-b + (int) Math.sqrt(b * b - 4 * n)) / 2;
        if (x <= 0) {
            x = (-b - (int) Math.sqrt(b * b - 4 * n)) / 2;
        }

        int y = n / x;

        System.out.println(x + " " + y);
        boolean has2 = x == 2 || y == 2;

        int[] firstRow = populateFirstRow(graph, vis, startWith, has2);
        int[][] ans;
        if (firstRow.length == x) {
            int t = x;
            x = y;
            y = t;
        }

        ans = new int[x][y];
        ans[0] = firstRow;
        for (int i = 1; i < x; i++) {
            for (int child : graph[ans[i - 1][0]]) {
                if (vis[child])
                    continue;
                vis[child] = true;
                ans[i][0] = child;
                break;
            }
            for (int j = 1; j < y; j++) {
                for (int child : graph[ans[i][j - 1]]) {
                    if (vis[child])
                        continue;
                    if (graph[child].contains(ans[i - 1][j]) == false)
                        continue;
                    vis[child] = true;
                    ans[i][j] = child;
                    break;
                }
            }
        }
        return ans;
    }

    private int[] populateFirstRow(ArrayList<Integer>[] graph, boolean vis[], int startWith, boolean has2) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(startWith);
        vis[startWith] = true;
        if (has2) {
            for (int child : graph[startWith]) {
                if (graph[child].size() != 2)
                    continue;
                vis[child] = true;
                ans.add(child);
                break;
            }
            return ans.stream().mapToInt(Integer::intValue).toArray();

        }
        while (true) {
            for (int child : graph[startWith]) {
                if (vis[child])
                    continue;
                if (graph[child].size() > 3)
                    continue;
                vis[child] = true;
                ans.add(child);
                startWith = child;
                break;
            }
            if (graph[ans.getLast()].size() == 2)
                break;
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

}