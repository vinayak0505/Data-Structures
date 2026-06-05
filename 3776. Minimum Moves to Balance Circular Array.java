class Solution {
    public long minMoves(int[] balance) {
        int n = balance.length;
        long sum = 0;
        int index = -1;
        for(int i = 0;i<n;i++){
            sum += balance[i];
            if(balance[i] < 0){
                index = i;
            }
        }
        if(sum < 0) return -1l;
        if(index == -1) return 0;

        long ans = 0;
        sum = -balance[index];

        for(int i = 1; i < n && sum != 0;i++){
            int left = balance[(index - i + n) % n];
            int right = balance[(index + i) % n];

            long toRemoved = Math.min(sum, (long)left + right);
            sum -= toRemoved;
            ans += toRemoved * i;

        }
        return ans;
        
    }
}