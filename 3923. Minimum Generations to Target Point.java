import java.util.HashSet;
import java.util.Set;

class Solution {
    public class Point {
        public int x;
        public int y;
        public int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int hashCode() {
            return (x * 10000) + (y * 100) + z;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (this.getClass() != obj.getClass())
                return false;
            Point p = (Point) obj;
            return p.x == this.x && p.y == this.y && p.z == this.z;
        }
    }

    public int minGenerations(int[][] curPoint, int[] target) {
        int ans = 0;
        Point tartgetPoint = new Point(target[0], target[1], target[2]);
        Set<Point> newPoints = new HashSet<>();
        for (int[] point : curPoint) {
            Point newPoint = new Point(point[0], point[1], point[2]);
            if (newPoint.equals(tartgetPoint))
                return 0;
            newPoints.add(newPoint);
        }

        Set<Point> prePoints = new HashSet<>();
        Set<Point> nextPoints = new HashSet<>();

        while(newPoints.size() > 0){
            ans++;
            for (Point newPoint: newPoints) {
                for (Point prevPoint : prePoints) {
                    Point point = new Point((newPoint.x + prevPoint.x) / 2, (newPoint.y + prevPoint.y) / 2, (newPoint.z + prevPoint.z) / 2);
                    if(newPoints.contains(point) || prePoints.contains(point)){
                        continue;
                    }
                    if(point.equals(tartgetPoint)){
                        return ans;
                    }
                    nextPoints.add(point);
                }
            }

            for (Point newPoint: newPoints) {
                for (Point prevPoint : newPoints) {
                    Point point = new Point((newPoint.x + prevPoint.x) / 2, (newPoint.y + prevPoint.y) / 2, (newPoint.z + prevPoint.z) / 2);
                    if(newPoints.contains(point) || prePoints.contains(point)){
                        continue;
                    }
                    if(point.equals(tartgetPoint)){
                        return ans;
                    }
                    nextPoints.add(point);
                }
            }
            prePoints.addAll(newPoints);
            newPoints = nextPoints;
            nextPoints = new HashSet<>();
        }

        return -1;
    }
}