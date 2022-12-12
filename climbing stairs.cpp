class Solution {
    int arr[46] = {0};
public:
    int climbStairs(int n) {
        if(n == 1 || n == 2) return n;
        if(arr[n]) return arr[n];
        return arr[n] = climbStairs(n - 1) + climbStairs(n - 2);
    }
};