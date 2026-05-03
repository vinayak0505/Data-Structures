import java.util.HashMap;
import java.util.Map;

class Solution {
    class UnionJoin {
        int[] size;
        int[] parent;
        Map<Integer,Integer> x = new HashMap<>();
        Map<Integer,Integer> y = new HashMap<>();

        public UnionJoin(int n){
            size = new int[n];
            parent = new int[n];
            for(int i = 0;i<n;i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void add(int[] num, int i){
            if(x.containsKey(num[0])){
                union(i, x.get(num[0]));
            }else{
                x.put(num[0], i);
            }

            if(y.containsKey(num[1])){
                union(i, y.get(num[1]));
            }else{
                y.put(num[1], i);
            }
        }
        public void union(int a, int b){
            a = getParent(a);
            b = getParent(b);
            if(a == b) return;
            if(size[a] > size[b]){
                size[a] += size[b];
                parent[b] = a;
            }else {
                parent[a] = b;
                size[b] += size[a];
            }
        }
        public int getSize(int a){
            return size[a];
        }

        public int getParent(int a){
            if(parent[a] == a) return a;
            return parent[a] = getParent(parent[a]);
        }
    }

    
    public int maxActivated(int[][] points) {
        UnionJoin u = new UnionJoin(points.length);

        for(int i = 0;i<points.length;i++){
            u.add(points[i], i);    
        }

        int a1 = 0, a2 = 0;
        int p1 = -1;
        for(int i = 0;i<points.length;i++){
            int parent = u.getParent(i);
            int size = u.getSize(parent);
            if(p1 == parent) continue;
            if(size > a1){
                a2 = a1;
                p1 = parent;
                a1 = size;
            }else if(size > a2){
                a2 = size;
            }
        }
        return a1 + a2 + 1;
    }
}