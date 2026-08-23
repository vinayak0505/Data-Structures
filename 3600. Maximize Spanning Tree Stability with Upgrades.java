import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    class UnionFind {
        int n;
        int parent[];
        int maxStability;
        int unconnected;

        public UnionFind(int n) {
            this.n = n;
            parent = new int[n];
            Arrays.fill(parent, -1);
            maxStability = Integer.MAX_VALUE;
            unconnected = n;
        }

        private int getParent(int a) {
            if (parent[a] == -1)
                return a;
            parent[a] = getParent(parent[a]);
            return parent[a];
        }

        public boolean join(int a, int b, int strength) {
            int pa = getParent(a);
            int pb = getParent(b);
            if (pa == pb)
                return false;
            parent[pa] = pb;
            maxStability = Math.min(maxStability, strength);
            unconnected -= 1;
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        UnionFind uf = new UnionFind(n);

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[2] - a[2]);

        for (int[] edge : edges) {
            if (edge[3] == 0) {
                queue.add(edge);
            } else {
                boolean isValid = uf.join(edge[0], edge[1], edge[2]);
                if(isValid == false) return -1;
            }
        }

        while(!queue.isEmpty()){
            int[] edge = queue.poll();
            int strength = edge[2];
            if(uf.unconnected - 1 <= k) {
                strength *= 2;
            }

            uf.join(edge[0], edge[1], strength);
        }

        if(uf.unconnected > 1) return -1;
        return uf.maxStability;
    }
}