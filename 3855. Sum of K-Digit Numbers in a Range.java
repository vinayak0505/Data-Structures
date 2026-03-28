class Solution {
    // total sum, total pairs.
    private int[] helper(int l, int r, int k){
        int sum = 0;
        for(int i = l;i<= r;i++){
            sum += i;
        }
        int diff = r - l + 1;

        if(k == 1){
            return new int[]{sum, diff};
        }
        int[] got = helper(l, r, k - 1);
        int totalsum = sum * (int)Math.pow(10, k - 1) * got[1];
        totalsum += got[0] * diff;
        return new int[]{totalsum, got[1] * diff};
    }
    public int sumOfNumbers(int l, int r, int k) {
        return helper(l, r, k)[0];
    }
}