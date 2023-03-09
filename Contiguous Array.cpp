#include "vector"
#include "unordered_map"

using namespace std;

class Solution {
public:
    int findMaxLength(vector<int>& nums) {
        unordered_map<int,vector<int>> dp;
        dp[0].push_back(-1);
        int a = 0,b = 0;
        for(int i = 0;i<nums.size();i++){
            if(nums[i]) a++;
            else b++;
            dp[a - b].push_back(i);
        }
        int ans = 0;
        for(auto m:dp){
            if(m.second.size() > 1){
                ans = max(ans,m.second.back() - m.second.front());
            }
        }
        return ans;
    }
};