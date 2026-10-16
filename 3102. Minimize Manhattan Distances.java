import java.util.TreeSet;

class Solution {
    public int minimumDistance(int[][] points) {
        int n = points.length;
        int[][] add = new int[n][2];
        int[][] sub = new int[n][2];
        TreeSet<int[]> addT = new TreeSet<>((a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        TreeSet<int[]> subT = new TreeSet<>((a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));

        for (int i = 0; i < n; i++) {
            addT.add(new int[] { points[i][0] + points[i][1], i });
            subT.add(new int[] { points[i][0] - points[i][1], i });

            add[i] = new int[] { points[i][0] + points[i][1], i };
            sub[i] = new int[] { points[i][0] - points[i][1], i };
        }

        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            addT.remove(add[i]);
            subT.remove(sub[i]);

            if (sum > Math.max(addT.getLast()[0] - addT.getFirst()[0], subT.getLast()[0] - subT.getFirst()[0])) {
                sum = Math.max(addT.getLast()[0] - addT.getFirst()[0], subT.getLast()[0] - subT.getFirst()[0]);
            }

            addT.add(add[i]);
            subT.add(sub[i]);
        }
        return sum;
    }
}