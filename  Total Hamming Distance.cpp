#include "vector"

using namespace std;
class Solution {
public:
    int totalHammingDistance(vector<int>& nums) {
        vector<int> dp(32);
        for(int i = 0;i<32;i++){
            for(int j = 0;j<nums.size();j++){
                if(nums[j] & (1 << i)) dp[i]++;
            }
        }
        int ans = 0;
        for(int i:dp){
            ans += (nums.size() - i) * i;
        }
        return ans;
    }
};