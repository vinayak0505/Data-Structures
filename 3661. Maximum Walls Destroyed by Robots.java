import java.util.Arrays;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int m = walls.length;

        int[][] pair = new int[n][2];

        for (int i = 0; i < n; i++) {
            pair[i][0] = robots[i];
            pair[i][1] = distance[i];
        }

        Arrays.sort(walls);
        Arrays.sort(pair, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < n; i++) {
            robots[i] = pair[i][0];
            distance[i] = pair[i][1];
        }

        int[] wallsBefore = new int[n];
        for (int i = 0, j = 0, w = 0; j < n;) {
            if (walls[i] > robots[j]) {
                wallsBefore[j] = w;
                w = 0;
                j++;
                continue;
            }

            if (walls[i] >= robots[j] - distance[j]) {
                w++;
            }
            i++;
            if (i == m) {
                wallsBefore[j] = w;
                break;
            }
        }

        int[] wallsAfter = new int[n];
        for (int i = m - 1, j = n - 1, w = 0; j >= 0;) {
            if (walls[i] < robots[j]) {
                wallsAfter[j] = w;
                w = 0;
                j--;
                continue;
            }

            if (walls[i] <= robots[j] + distance[j]) {
                w++;
            }
            i--;
            if (i == -1) {
                wallsAfter[j] = w;
                break;
            }
        }

        int[] between = new int[n];
        for (int i = 0, j = 0, w = 0; j < n;) {
            if(walls[i] == robots[j]){
                between[j] = w + 1;
                w = 0;
                j++;
                continue;
            }
            if (walls[i] > robots[j]) {
                between[j] = w;
                w = 0;
                j++;
                continue;
            }
            w++;
            i++;
            if (i == m) {
                between[j] = w;
                break;
            }
        }

        System.out.println(Arrays.toString(robots));
        System.out.println(Arrays.toString(walls));
        System.out.println(Arrays.toString(wallsAfter));
        System.out.println(Arrays.toString(wallsBefore));
        System.out.println(Arrays.toString(between));

        int[][] dp = new int[n][2];

        dp[0][0] = wallsBefore[0];
        dp[0][1] = wallsAfter[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + wallsBefore[i], dp[i - 1][1] + wallsBefore[i] - Math.max(0, (wallsBefore[i] + wallsAfter[i - 1] - between[i])));
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]) + wallsAfter[i];
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}