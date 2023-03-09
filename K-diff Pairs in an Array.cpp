#include "vector"
#include "unordered_map"

using namespace std;

class Solution {

    int firstNSum(int a){
        return (a * (a + 1)) / 2;
    }

public:
    int findPairs(vector<int>& nums, int k) {
        unordered_map<int,int> dp;
        for(int num: nums) dp[num]++;
        int ans = 0;
        if(k == 0){
            for(auto d: dp) ans += firstNSum(d.second) > 1;
            return ans;
        }
        unordered_map<int,int> dpplus;
        for(int num: nums) dpplus[num + k] = 1;
        unordered_map<int,int> dpminus;
        for(int num: nums) dpminus[num - k] = 1;
        for(auto d: dp){
            ans += dpplus[d.first] + 
            dpminus[d.first];
        }
        return ans / 2;
    }
};