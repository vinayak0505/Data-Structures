#include "vector"

using namespace std;
class Solution {
public:
    int maxSubarraySumCircular(vector<int>& nums) {
        int ans = INT_MIN;
        int n = nums.size();
        int pre = 0;
        int total = 0;
        int ans_min = INT_MAX;
        int pre_min = 0;
        for(int i = 0;i<nums.size();i++){
            total += nums[i];
            pre += nums[i];
            pre_min += nums[i];
            ans = max(ans,pre);
            ans_min = min(ans_min,pre_min);
            if(pre < 0){
                pre = 0;
            }
            if(pre_min > 0){
                pre_min = 0;
            }
        }
        if(ans < 0) return ans;
        return max(ans,total - ans_min);
    }
};