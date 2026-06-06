class Solution {

    private int find(int hp, int[] damage, int n){
        int low = 0, high = n;
        int ans = -1;
        while(low <= high){
            int mid = (low + high) / 2;
            if(hp >= damage[n + 1] - damage[mid]){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
    }
    public long totalScore(int hp, int[] damage, int[] requirement) {
        int n = damage.length;
        long ans = 0;

        int[] prefix = new int[n + 1];
        for(int i = 0; i<n;i++){
            prefix[i + 1] = prefix[i] + damage[i];
        }

        for(int i = 0;i<n;i++){
            int j = find(hp - requirement[i], prefix, i);
            if(j == -1) continue;
            ans += i - j + 1;
        }
        return ans;
    }
}