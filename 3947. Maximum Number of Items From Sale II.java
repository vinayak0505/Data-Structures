import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {

    class Node {
        int factor;
        int value;
        int canGetFree;

        public Node(int factor, int value, int canGetFree) {
            this.factor = factor;
            this.value = value;
            this.canGetFree = canGetFree;
        }

        public Node(int factor, int value) {
            this(factor, value, factor - 1);
        }
    }


    private int maxFactor = (int) 1e5;

    public int maximumSaleItems(int[][] items, int budget) {
        Node[] nodes = new Node[items.length];
        for (int i = 0; i < items.length; i++) {
            nodes[i] = new Node(items[i][0], items[i][1]);
        }

        HashMap<Integer, ArrayList<Node>> map = new HashMap<>();
        for (Node node : nodes) {
            map.computeIfAbsent(node.factor, k -> new ArrayList<>()).add(node);
        }

        for(int i = 1; i <= maxFactor; i++) {
            if (map.containsKey(i)) {
                int count = 0;
                for(int j = i + i; j <= maxFactor; j += i) {
                    if (map.containsKey(j)) {
                        count += map.get(j).size();
                    }
                }
                for (Node node : map.get(i)) {
                    node.canGetFree = count + map.get(i).size() - 1;
                }
            }
        }

        Arrays.sort(nodes, (a, b) -> a.value - b.value);
        int ans = 0;
        int minValue = nodes[0].value;
        for(int i = 0;i<items.length && nodes[i].value <= (minValue << 1);i++){
            if (budget >= nodes[i].value) {
                int value = Math.min(budget / nodes[i].value, nodes[i].canGetFree);
                budget -= value * nodes[i].value;
                ans += (value << 1);
            } else {
                break;
            }
        }

        ans += budget / minValue;

        return ans;
    }
}