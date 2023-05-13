#include "unordered_map"

class Solution {
    int mod = 1e9 + 7;
    std::unordered_map<int,std::unordered_map<int,int>> dp;
public:
    int countGoodStrings(int low, int high, int zero, int one) {
        if(high < 0) return 0;
        int ans = low <= 0;
        if(dp[low].count(high)) return dp[low][high];
        ans += countGoodStrings(low-zero,high-zero,zero,one);
        ans += countGoodStrings(low-one,high-one,zero,one);
        return dp[low][high] = ans % mod;
    }
};