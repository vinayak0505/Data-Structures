import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private int[][] dirs = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };

    public int[][] colorGrid(int n, int m, int[][] sources) {
        int grid[][] = new int[n][m];

        Arrays.sort(sources, (a, b) -> {
            return b[2] - a[2];
        });

        Queue<Integer[]> q = new LinkedList<>();

        for(int[] source: sources){
            // System.out.println(source[2]);
            grid[source[0]][source[1]] = source[2];
            q.add(new Integer[]{source[0], source[1], source[2]});
        }

        while(q.size() > 0){
            int x = q.peek()[0];
            int y = q.peek()[1];
            int c = q.peek()[2];
            q.poll();
            for(int dir[]: dirs){
                int newx = x + dir[0];
                int newy = y + dir[1];
                if(newx < n && newx >= 0 && newy < m && newy >= 0){
                    if(grid[newx][newy] != 0) continue;
                    grid[newx][newy] = c;
                    q.add(new Integer[]{newx, newy, c});
                }
            }
        }

        return grid;
    }
}