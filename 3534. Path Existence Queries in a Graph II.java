import java.util.Arrays;

class Solution {

    class Value {
        public int nums;
        public int index;

        public Value(int nums, int index) {
            this.nums = nums;
            this.index = index;
        }

        @Override
        public String toString() {
            return nums + " " + index;
        }
    }

    public int log2(int n) {
        return (int) (Math.log(n) / Math.log(2)) + 2;
    }

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Value[] data = new Value[n];
        for (int i = 0; i < n; i++) {
            data[i] = new Value(nums[i], i);
        }

        Arrays.sort(data, (a, b) -> a.nums - b.nums);

        int m = log2(n);
        int maxJumps[][] = new int[m][n];

        for (int i = 0, j = 0; i < n; i++) {
            while (j + 1 < n && (data[j + 1].nums - data[i].nums) <= maxDiff) {
                j++;
            }

            maxJumps[0][i] = j;
        }

        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                maxJumps[j][i] = maxJumps[j - 1][maxJumps[j - 1][i]];
            }
        }

        int actual[] = new int[n];

        for (int i = 0; i < n; i++) {
            actual[data[i].index] = i;
        }

        int q = queries.length;

        int ans[] = new int[q];

        for (int i = 0; i < q; i++) {
            int a = actual[queries[i][0]];
            int b = actual[queries[i][1]];

            if(a == b){
                ans[i] = 0;
                continue;
            }

            System.out.println(maxJumps[m - 1][a] + " " + maxJumps[m - 1][b] + " " + a + " " + b);
            if (a > b) {
                int c = a;
                a = b;
                b = c;
            }
            if (maxJumps[m - 1][a] < b) {
                ans[i] = -1;
                continue;
            }

            
            int count = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (maxJumps[j][a] < b) {
                    a = maxJumps[j][a];
                    count += (1 << j);
                }
            }
            ans[i] = count + 1;

        }
        return ans;
    }
}