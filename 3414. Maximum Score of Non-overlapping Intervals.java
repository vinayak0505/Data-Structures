import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    class Node {
        List<Integer> list;
        long sum;
        int lastIndex;

        public Node(List<Integer> list, long sum, int lastIndex) {
            this.list = new ArrayList<>(list);
            this.sum = sum;
            this.lastIndex = lastIndex;
        }

        public Node() {
            list = new ArrayList<>();
            sum = 0;
            lastIndex = 0;
        }

        public Node(Node node) {
            this(node.list, node.sum, node.lastIndex);
        }

        public Node addItem(int index) {
            this.list.add(index);
            this.list.sort((a, b) -> Integer.compare(a, b));
            return this;
        }

        public Node updateLastIndex(int lastIndex) {
            this.lastIndex = lastIndex;
            return this;
        }

        public Node addSum(long sum) {
            this.sum += sum;
            return this;
        }

        public int getLastItemOrder() {
            return list.getLast();
        }

        public int compare(Node node) {
            int to = Math.min(node.list.size(), list.size());
            for (int i = 0; i < to; i++) {
                if (list.get(i) > node.list.get(i)) {
                    return 1;
                } else if (list.get(i) < node.list.get(i)) {
                    return -1;
                }
            }

            if (node.list.size() < list.size()) {
                return 1;
            } else if (node.list.size() > list.size()) {
                return -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return "array is " + list.toString() + " sum is " + sum + " lastidndex " + lastIndex + " |";
        }

    }

    private Node getPrev(int before, Node dp[][], int end, int row) {
        if (row < 0)
            return new Node();
        // for (int i = index; i >= 0; i--) {
        // if (dp[row][i] == null)
        // break;
        // if (dp[row][i].lastIndex < before)
        // return dp[row][i];
        // }
        int start = 0;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if(dp[row][mid] == null || dp[row][mid].lastIndex < before){
                ans = mid;
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        if (ans == -1 || dp[row][ans] == null) {
            return new Node();
        } else {
            return dp[row][ans];
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        for (int i = 0; i < n; i++) {
            intervals.get(i).add(i);
        }

        intervals.sort((a, b) -> Integer.compare(a.get(1), b.get(1)));

        System.out.println(Arrays.toString(intervals.stream().map(a -> a.get(3)).toArray()));

        Node dp[][] = new Node[4][n];

        for (int row = 0; row < 4; row++) {
            for (int i = 0; i < n; i++) {
                List<Integer> list = intervals.get(i);
                long currentSum = list.get(2);
                Node nodeBeforeCurrent = getPrev(list.get(0), dp, i - 1, row - 1);

                Node curNode = new Node(nodeBeforeCurrent).addItem(list.get(3)).addSum(currentSum)
                        .updateLastIndex(list.get(1));

                if (i == 0) {
                    dp[row][i] = curNode;
                    continue;
                }

                Node prevNode = dp[row][i - 1];
                if (prevNode.sum > curNode.sum) {
                    dp[row][i] = prevNode;
                } else if (prevNode.sum < curNode.sum) {
                    dp[row][i] = curNode;
                } else {
                    if (curNode.compare(prevNode) <= 0) {
                        dp[row][i] = curNode;
                    } else {
                        dp[row][i] = prevNode;
                    }
                }

            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == null) {
                    System.out.print("null");
                } else {
                    System.out.print(dp[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }

        if (dp[3][n - 1] == null)
            return new int[] {};

        return dp[3][n - 1].list.stream()
                .mapToInt(Integer::intValue)
                .toArray();

    }
}