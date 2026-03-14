class Solution {
    private int[] preSum;
    private void createPreSum(String s){
        preSum = new int[s.length() + 1];
        int count = 0;
        preSum[0] = 0;
        for(int i = 1;i<s.length() + 1;i++){
            if(s.charAt(i - 1) == '1'){
                count++;
            }
            preSum[i] = count;
        }
    }
    private long getSens(String s, int start, int end) {
        return preSum[end] - preSum[start];
    }
    private long getCost(String s, int encCost, int flatCost, int start, int end){
        long countSen = getSens(s, start, end);
        if(countSen > 0){
            return ((long)(end - start)) * countSen * encCost;
        }
        return flatCost;
    }

    private long minCost(String s, int encCost, int flatCost, int start, int end){
        long ans = getCost(s, encCost, flatCost, start, end);
        if(((end - start) & 1) == 1) return ans;
        int mid = (start + end) >> 1;
        return Math.min(ans, minCost(s, encCost, flatCost, start, mid) +
        minCost(s, encCost, flatCost, mid, end));
    }

    public long minCost(String s, int encCost, int flatCost) {
        createPreSum(s);
        return minCost(s, encCost, flatCost, 0, s.length());
    }
}