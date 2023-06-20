#include<vector>

using namespace std;

class Solution {
public:
    vector<int> getAverages(vector<int>& nums, int k) {
        vector<int> ans(nums.size(),-1);
        long long sum = 0;
        for(int i = 0;i<k*2 && i<nums.size();i++){
            sum += nums[i];
        }

        for(int i = k;i<((int)nums.size() - k);i++){
            sum += nums[i + k];
            ans[i] = sum/(k + k + 1);
            sum -= nums[i - k];
        }
        return ans;
    }
};