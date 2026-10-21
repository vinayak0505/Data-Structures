class Solution {
    public long flowerGame(int n, int m) {
        long even = n / 2;
        long odd = n - even;
        long even2 = m / 2;
        long odd2 = m - even2;
        System.out.println(even + " " + odd + " " + even2 + " " + odd2);
        return even * odd2 + odd * even2;
    }
}