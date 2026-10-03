import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    public class ManacherAlgo {
        StringBuilder builder;
        int palindromeLength[];

        public ManacherAlgo(String s) {
            builder = new StringBuilder();
            builder.append('#');

            for (char c : s.toCharArray()) {
                builder.append(c);
                builder.append('#');
            }

            build();
        }

        private void build() {
            int n = builder.length();
            palindromeLength = new int[n];

            int indexWithFartherestPalindrome = -1;
            int size = 0;

            for (int i = 0; i < n; i++) {
                int curSize = 0;
                if (indexWithFartherestPalindrome + size > i) {
                    curSize = Math.max(0, Math.min(indexWithFartherestPalindrome + size - i,
                            palindromeLength[2 * indexWithFartherestPalindrome - i]));
                }
                while (i - curSize >= 0 && i + curSize < n
                        && builder.charAt(i - curSize) == builder.charAt(i + curSize)) {
                    curSize++;
                }
                palindromeLength[i] = curSize;
                if (i + curSize > indexWithFartherestPalindrome + size) {
                    indexWithFartherestPalindrome = i;
                    size = curSize;
                }
            }
        }

        public boolean isPalindrome(int start, int end) {
            start = start * 2 + 1;
            end = end * 2 + 1;
            int mid = (end + start) / 2;
            int size = end - mid;
            return size < palindromeLength[mid];
        }
    }

    StringBuilder str;
    int start[];
    int end[];

    private int buildString(ArrayList<Integer> graph[], int node, String s, int parent) {
        int height = 1;
        start[node] = str.length();
        for (int child : graph[node]) {
            if (parent == child)
                continue;
            int childHeight = buildString(graph, child, s, node);
            height = Math.max(height, childHeight + 1);
        }
        str.append(s.charAt(node));
        end[node] = str.length() - 1;
        return height;
    }

    public boolean[] findAnswer(int[] parent, String s) {
        str = new StringBuilder();
        int n = parent.length;
        start = new int[n];
        end = new int[n];
        ArrayList<Integer> graph[] = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            if (parent[i] == -1)
                continue;
            graph[parent[i]].add(i);
        }
        buildString(graph, 0, s, -1);

        System.out.println(str.toString());
        System.out.println(Arrays.toString(start));
        System.out.println(Arrays.toString(end));

        ManacherAlgo algo = new ManacherAlgo(str.toString());

        boolean[] ans = new boolean[n];

        for (int i = 0; i < n; i++) {
            ans[i] = algo.isPalindrome(start[i], end[i]);
        }
        return ans;
    }
}