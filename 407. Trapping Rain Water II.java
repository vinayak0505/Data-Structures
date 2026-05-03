import java.util.PriorityQueue;

class Solution {
    class CellData implements Comparable<CellData> {
        public int i;
        public int j;
        public int height;
        public CellData(int i, int j, int height){
            this.i = i;
            this.j = j;
            this.height = height;
        }
        @Override
        public int compareTo(Solution.CellData o) {
            return this.height - o.height;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;

        PriorityQueue<CellData> pq = new PriorityQueue<>();

        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(i == 0 || j == 0 || i == n - 1 || j == m - 1){
                    pq.add(new CellData(i, j, heightMap[i][j]));
                    heightMap[i][j] = -1;
                }
            }
        }
        int ans = 0;
        int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        while(!pq.isEmpty()){
            CellData cellData = pq.poll();
            for(int[] direction : directions) {
                int newI = cellData.i + direction[0];
                int newJ = cellData.j + direction[1];
                if(newI >= 0 && newI < n && newJ >= 0 && newJ < m && heightMap[newI][newJ] != -1){
                    if(heightMap[newI][newJ] < cellData.height){
                        ans += cellData.height - heightMap[newI][newJ];
                    }
                    pq.add(new CellData(newI, newJ, Math.max(cellData.height, heightMap[newI][newJ])));
                    heightMap[newI][newJ] = -1;
                }
            }
        }
        return ans;
    }
}

// TODO learn again