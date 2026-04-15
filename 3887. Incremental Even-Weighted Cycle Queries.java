import java.util.ArrayList;

class Solution {
    class UnionFind {
        Edge parent[];
        public UnionFind(int n){
            parent = new Edge[n];
            for(int i = 0;i<n;i++){
                parent[i] = new Edge(-1, 0);
            }
        }

        public Edge getParent(Edge i){
            if(parent[i.node].node == -1) return i;
            Edge edge = getParent(parent[i.node]);
            i.node = edge.node;
            i.w += edge.w;
            return new Edge(edge.node, i.w);
        }

        public int isUnion(Edge a, Edge b){
            a = getParent(a);
            b = getParent(b);
            if(a.node != b.node){
                return -1;
            };
            return ((a.w + b.w) & 1);
        }

        public void join(Edge a, Edge b,int val){
            a = getParent(a);
            b = getParent(b);
            if(a.node == b.node) return;
            if(a.node > b.node){
                parent[a.node].node = b.node;
                parent[a.node].w = val + a.w + b.w;
            }else{
                parent[b.node].node = a.node;
                parent[b.node].w = val + a.w + b.w;
            }
        }
    }
    class Edge{
        public int node;
        public int w;
        public Edge(int node, int w){
            this.node = node;
            this.w = w;
        }
    }

    public int numberOfEdgesAdded(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int ans = 0;
        ArrayList<Edge> graph[] = new ArrayList[n];
        for(int i= 0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        boolean vis[] = new boolean[n];
        for(int i = 0, a, b, w;i<edges.length;i++){
            a = edges[i][0];
            b = edges[i][1];
            w = edges[i][2];
            vis[a] = true;
            int value = uf.isUnion(new Edge(a, w), new Edge(b, w));
            if(value == -1 || value == w){
                System.out.println(value);
                if(value == -1){
                    uf.join(new Edge(a, w), new Edge(b, w), w);
                }             
                graph[a].add(new Edge(b, w));
                graph[b].add(new Edge(a, w));
                ans++;
            }
            vis[a] = false;

        }
        return ans;
    }
}