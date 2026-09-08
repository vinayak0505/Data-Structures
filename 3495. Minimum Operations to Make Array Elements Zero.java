import java.util.ArrayList;

class Solution {
    public long minOperations(int[][] queries) {
        ArrayList<Integer> match = new ArrayList<>();

        long number = 4;
        while (number < 1e9) {
            match.add((int) number);
            number *= 4;
        }
        match.add(Integer.MAX_VALUE);

        long ans = 0;
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1] + 1;
            if(l == r){
                while(l > 0){
                    ans++;
                    l /= 4;
                }
                break;
            }
            long remaining = 0;

            for (int j = 0; j <= match.size() && l < r; j++) {
                int to = Math.min(r, match.get(j));
                if(l > to)continue;
                long num = to - l;
                if(num % 2 == 1){
                    remaining += (j + 1);
                }
                ans += (num / 2) * (j + 1);
                l = match.get(j);
            }

            ans += (remaining + 1) / 2;
        }

        return ans;
    }
}