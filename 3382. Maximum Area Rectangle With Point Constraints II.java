import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {

    class MergeSortTree {
        int n;
        ArrayList<Integer> array[];

        private void build(int node, int left, int right, ArrayList<Integer>[] points) {
            if (left == right) {
                array[node] = new ArrayList<>(points[left]);
                return;
            }
            int mid = (left + right) / 2;
            build(node * 2, left, mid, points);
            build(node * 2 + 1, mid + 1, right, points);
            ArrayList<Integer> arrayList = new ArrayList<>();
            array[node] = arrayList;
            ArrayList<Integer> leftArray = array[node * 2];
            ArrayList<Integer> rightArray = array[node * 2 + 1];
            int leftIndex = 0;
            int rightIndex = 0;
            while (leftIndex < leftArray.size() && rightIndex < rightArray.size()) {
                if (leftArray.get(leftIndex) == rightArray.get(rightIndex)) {
                    arrayList.add(leftArray.get(leftIndex));
                    leftIndex++;
                    rightIndex++;
                } else if (leftArray.get(leftIndex) < rightArray.get(rightIndex)) {
                    arrayList.add(leftArray.get(leftIndex));
                    leftIndex++;
                } else {
                    arrayList.add(rightArray.get(rightIndex));
                    rightIndex++;
                }
            }
            while (leftIndex < leftArray.size()) {
                arrayList.add(leftArray.get(leftIndex));
                leftIndex++;
            }
            while (rightIndex < rightArray.size()) {
                arrayList.add(rightArray.get(rightIndex));
                rightIndex++;
            }
        }

        private Integer getGreaterThen(int x, ArrayList<Integer> list) {
            Integer ans = null;
            int start = 0;
            int end = list.size() - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (list.get(mid) > x) {
                    ans = list.get(mid);
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            return ans;
        }

        private Integer getGreaterThenInRange(int node, int left, int right, int y1, int y2, int x) {
            if (y1 <= left && right <= y2) {
                return getGreaterThen(x, array[node]);
            }

            if (y2 < left || right < y1) {
                return null;
            }

            int mid = (left + right) / 2;
            Integer leftMin = getGreaterThenInRange(node * 2, left, mid, y1, y2, x);
            Integer rightMin = getGreaterThenInRange(node * 2 + 1, mid + 1, right, y1, y2, x);
            if (leftMin == null) {
                return rightMin;
            }
            if (rightMin == null) {
                return leftMin;
            }
            return Math.min(leftMin, rightMin);
        }

        public Integer getGreaterThenInRange(int x, int y1, int y2) {
            if (y1 > y2)
                return null;
            return getGreaterThenInRange(1, 0, n - 1, y1, y2, x);
        }

        public MergeSortTree(ArrayList<Integer>[] points) {
            n = points.length;
            array = new ArrayList[4 * n];
            build(1, 0, n - 1, points);
        }
    }

    public long maxRectangleArea(int[] xCoord, int[] yCoord) {
        int n = xCoord.length;

        int[] uniqueXPoints = Arrays.stream(xCoord).distinct().toArray();
        Arrays.sort(uniqueXPoints);
        int xSize = uniqueXPoints.length;
        HashMap<Integer, Integer> mapXToIndex = new HashMap<>();
        for (int i = 0; i < xSize; i++) {
            mapXToIndex.put(uniqueXPoints[i], i);
        }
        ArrayList<Integer> pointsBasedOnX[] = new ArrayList[xSize];
        for (int i = 0; i < n; i++) {
            int x = xCoord[i];
            int y = yCoord[i];
            int index = mapXToIndex.get(x);
            if (pointsBasedOnX[index] == null) {
                pointsBasedOnX[index] = new ArrayList<>();
            }
            pointsBasedOnX[index].add(y);
        }
        for (ArrayList<Integer> array : pointsBasedOnX) {
            array.sort((a, b) -> Integer.compare(a, b));
        }

        int[] uniqueYPoints = Arrays.stream(yCoord).distinct().toArray();
        Arrays.sort(uniqueYPoints);
        int ySize = uniqueYPoints.length;
        HashMap<Integer, Integer> mapYToIndex = new HashMap<>();
        for (int i = 0; i < ySize; i++) {
            mapYToIndex.put(uniqueYPoints[i], i);
        }
        ArrayList<Integer> pointsBasedOnY[] = new ArrayList[ySize];
        for (int i = 0; i < n; i++) {
            int x = xCoord[i];
            int y = yCoord[i];
            int index = mapYToIndex.get(y);
            if (pointsBasedOnY[index] == null) {
                pointsBasedOnY[index] = new ArrayList<>();
            }
            pointsBasedOnY[index].add(x);
        }
        for (ArrayList<Integer> array : pointsBasedOnY) {
            array.sort((a, b) -> Integer.compare(a, b));
        }
        MergeSortTree mergeSortTree = new MergeSortTree(pointsBasedOnY);

        long ans = -1;
        for (int i = 0; i < xSize - 1; i++) {
            int x = uniqueXPoints[i];
            int size = pointsBasedOnX[i].size();

            for (int j = 0; j < size - 1; j++) {
                int y1 = pointsBasedOnX[i].get(j);
                int y2 = pointsBasedOnX[i].get(j + 1);
                int y1Index = mapYToIndex.get(pointsBasedOnX[i].get(j));
                int y2Index = mapYToIndex.get(pointsBasedOnX[i].get(j + 1));

                Integer nextXforY1 = mergeSortTree.getGreaterThenInRange(x, y1Index, y1Index);
                Integer nextXforY2 = mergeSortTree.getGreaterThenInRange(x, y2Index, y2Index);
                if (nextXforY2 == null || nextXforY1 == null || !nextXforY1.equals(nextXforY2)) {
                    continue;
                }

                Integer itemBetweeny1Andy2ForNextX = mergeSortTree.getGreaterThenInRange(x, y1Index + 1, y2Index - 1);
                if (itemBetweeny1Andy2ForNextX != null && itemBetweeny1Andy2ForNextX <= nextXforY1) {
                    continue;
                }

                ans = Math.max(ans, (long) (nextXforY1 - x) * (y2 - y1));
            }
        }
        return ans;

    }
}