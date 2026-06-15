class Solution {

    private long lcm(int r1, int r2){
        long num = (long)r1*r2;
        long deno = gcd(r1, r2);
        return num/deno;
    }

    private long gcd(int r1, int r2){
        long a = Math.max(r1, r2);
        long b = Math.min(r1, r2);
        while(b != 0){
            long temp = b;
            b = a%b;
            a = temp;
        }
        return a;
    }

    private boolean solve(long posAns, int[] d, int[] r){
        int r1 = r[0];
        int r2 = r[1];

        long r1sub = posAns / r1;
        long r2sub = posAns / r2;

        long r1r2add = posAns / lcm(r1, r2);

        long d1 = d[0] - Math.min(d[0], r2sub - r1r2add);
        long d2 = d[1] - Math.min(d[1], r1sub - r1r2add);

        posAns = (posAns - (r1sub + r2sub - r1r2add));

        return posAns >= d1 + d2;
    }

    public long minimumTime(int[] d, int[] r) {
        long max = (4 * (long)1e9) + 1;
        long ans = max;
        long low = 0;
        while(low <= max){
            long mid = (low + max) / 2;
            boolean tempans = solve(mid, d, r);
            if(tempans){
                ans = mid;
                max = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
    }
}