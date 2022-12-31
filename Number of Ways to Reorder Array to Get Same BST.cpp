#include "vector"

using namespace std;
class Solution {

    const long long mod = 1e9 + 7;

    long comb(int m, int n) {
        int mod = 1e9 + 7;
        long num = 1, dem = 1;
        for (int i=m; i>= m - n + 1; --i)
            num = (num * i) % mod;
        for (int i=1; i<=n; ++i)
            dem = (dem * mypow(i)) % mod;
        
        return (num * dem) % mod;
    }
    int mypow(long x) {
        int n = 1e9 + 5, mod = 1e9 + 7;
        long res = 1;
        while(n) {
            if (n & 1) res = (res * x) % mod;
            x = (x * x) % mod;
            n >>= 1;
        }
        return res;
    }
    long long solve(vector<int>& nums){
        if(nums.size() == 1) return 1;
        vector<int> left,right;
        for(int i = 1;i<nums.size();i++){
            if(nums[i] > nums.front()) left.push_back(nums[i]);
            else right.push_back(nums[i]);
        }
        long long ans = comb(left.size() + right.size(), left.size());
        ans %= mod;
        if(left.size()) ans *= solve(left);
        ans %= mod;
        if(right.size()) ans *= solve(right);
        ans %= mod;
        return ans;
    }

public:
    long long numOfWays(vector<int>& nums) {
        return (solve(nums) - 1) % mod;
    }
};