#include "vector"
#include "unordered_map"

using namespace std;

class Solution {
public:
    int longestSubsequence(vector<int>& arr, int d) {
        int ans = 0;
        unordered_map<int,int> dp;
        for(int a: arr){
            dp[a] = dp[a - d] + 1;
            ans = max(ans,dp[a]);
        }
        return ans;
    }
};