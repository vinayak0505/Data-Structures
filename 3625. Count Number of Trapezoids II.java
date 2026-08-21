import java.util.HashMap;
import java.util.Map.Entry;

class Solution {

    private int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    // Returns a reduced fraction string "dy/dx" for the slope
    private String getSlopeKey(int[] a, int[] b) {
        int dy = b[1] - a[1];
        int dx = b[0] - a[0];

        if (dx == 0) return "INF"; // Vertical line
        if (dy == 0) return "0";   // Horizontal line

        int g = gcd(dy, dx);
        dy /= g;
        dx /= g;

        if (dx < 0) { // Keep denominator positive for consistency
            dy = -dy;
            dx = -dx;
        }
        return dy + "/" + dx;
    }

    // Calculates exact integer line constant C in: A*x + B*y = C
    private int getLineC(int[] point, String slopeKey) {
        if (slopeKey.equals("INF")) return point[0];
        if (slopeKey.equals("0")) return point[1];

        String[] parts = slopeKey.split("/");
        int dy = Integer.parseInt(parts[0]);
        int dx = Integer.parseInt(parts[1]);

        // Line equation: dy * x - dx * y = C
        return dy * point[0] - dx * point[1];
    }

    private int makeKey(int[] a, int[] b) {
        return ((a[0] + b[0]) * 10000) + (b[1] + a[1]);
    }

    public int countTrapezoids(int[][] points) {
        // slope -> c -> count;
        HashMap<String, HashMap<Integer, Integer>> yTocMap = new HashMap<>();
        HashMap<String, Integer> yToCountMap = new HashMap<>();
        HashMap<Integer, HashMap<String, Integer>> midPoint = new HashMap<>();

        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String slope = getSlopeKey(points[i], points[j]);
                int c = getLineC(points[i], slope);

                if (yToCountMap.containsKey(slope) == false) {
                    yToCountMap.put(slope, 0);
                    yTocMap.put(slope, new HashMap<>());
                }

                int midPointKey = makeKey(points[i], points[j]);
                if (midPoint.containsKey(midPointKey) == false) {
                    midPoint.put(midPointKey, new HashMap<>());
                }

                midPoint.get(midPointKey).put(slope, midPoint.get(midPointKey).getOrDefault(slope, 0) + 1);

                yToCountMap.put(slope, yToCountMap.get(slope) + 1);
                HashMap<Integer, Integer> hashMap = yTocMap.get(slope);
                hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
            }
        }
        int ans = 0;

        for (Entry<String, Integer> entrySet : yToCountMap.entrySet()) {
            int count = 0;
            for (int value : yTocMap.get(entrySet.getKey()).values()) {
                ans += count * value;
                count += value;
            }
        }

        for (Entry<Integer, HashMap<String, Integer>> entrySet : midPoint.entrySet()) {
            int count = 0;
            for (int name : entrySet.getValue().values()) {
                ans -= count * name;
                count += name;
            }
        }

        return ans;
    }
}