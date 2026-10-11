import java.util.Arrays;

class Solution {

    class Node {
        Circle circle;
        Line line;

        public Node(Circle circle) {
            this.circle = circle;
        }

        public Node(Line line) {
            this.line = line;
        }

        boolean isMatch(Node node, int xCorner, int yCorner) {
            Circle circle2 = node.circle;
            if (circle2 == null) {
                System.out.println("this shoudl not happen");
                return false;
            }
            if (line == null) {
                long r1 = (long) circle.r;
                long r2 = (long) circle2.r;
                long x1 = (long) circle.x;
                long y1 = (long) circle.y;
                long x2 = (long) circle2.x;
                long y2 = (long) circle2.y;

                long rSum = r1 + r2;
                long dx = x2 - x1;
                long dy = y2 - y1;

                // Check if circles intersect
                if (rSum * rSum < dx * dx + dy * dy) {
                    return false;
                }

                // Check if their intersection point is inside the rectangle bounds (0 to
                // xCorner, 0 to yCorner)
                long intersectX = x1 * r2 + x2 * r1;
                long intersectY = y1 * r2 + y2 * r1;
                long totalR = r1 + r2;

                return intersectX >= 0 && intersectX <= totalR * xCorner &&
                        intersectY >= 0 && intersectY <= totalR * yCorner;
            }

            long x0 = circle2.x;
            long y0 = circle2.y;
            long r = circle2.r;

            long x1 = line.point1x, y1 = line.point1y;
            long x2 = line.point2x, y2 = line.point2y;

            // closest point on the wall: just keep the circle center inside the wall's
            // range
            long nearestX = Math.max(Math.min(x1, x2), Math.min(Math.max(x1, x2), x0));
            long nearestY = Math.max(Math.min(y1, y2), Math.min(Math.max(y1, y2), y0));

            long distSq = (x0 - nearestX) * (x0 - nearestX) + (y0 - nearestY) * (y0 - nearestY);
            return distSq <= r * r;

        }
    }

    class Circle {
        int x;
        int y;
        int r;

        public Circle(int[] circle) {
            x = circle[0];
            y = circle[1];
            r = circle[2];
        }
    }

    static class Line {
        int point1x;
        int point1y;
        int point2x;
        int point2y;

        public Line(int point1x, int point1y, int point2x, int point2y) {
            this.point1x = point1x;
            this.point1y = point1y;
            this.point2x = point2x;
            this.point2y = point2y;
        }

        public static Line[] lineBuilder(int xCorner, int yCorner) {
            return new Line[] {
                    new Line(0, 0, xCorner, 0),
                    new Line(xCorner, 0, xCorner, yCorner),
                    new Line(0, 0, 0, yCorner),
                    new Line(0, yCorner, xCorner, yCorner)
            };
        }
    }

    private int getParent(int i, int[] parents) {
        if (parents[i] == i)
            return i;
        return parents[i] = getParent(parents[i], parents);
    }

    public boolean canReachCorner(int xCorner, int yCorner, int[][] circles) {
        int n = circles.length;
        Node[] nodes = new Node[n + 4];
        int[] parent = new int[n + 4];
        Line[] lineBuilder = Line.lineBuilder(xCorner, yCorner);
        nodes[0] = new Node(lineBuilder[0]);
        nodes[1] = new Node(lineBuilder[1]);
        nodes[2] = new Node(lineBuilder[2]);
        nodes[3] = new Node(lineBuilder[3]);

        for (int i = 0; i < n; i++) {
            nodes[i + 4] = new Node(new Circle(circles[i]));
        }

        for (int i = 0; i < n + 4; i++) {
            parent[i] = i;
        }
        parent[1] = 0;
        parent[3] = 2;

        for (int i = 4; i < n + 4; i++) {
            for (int j = 0; j < i; j++) {
                // System.out.println("for i " + i + " and j " + j + " matched " +
                // nodes[j].isMatch(nodes[i]));
                if (nodes[j].isMatch(nodes[i], xCorner, yCorner) == false)
                    continue;
                int leftParent = getParent(j, parent);
                int rightParent = getParent(i, parent);

                parent[rightParent] = leftParent;

            }
        }

        System.out.println(Arrays.toString(parent));

        return getParent(0, parent) != getParent(2, parent);
    }
}