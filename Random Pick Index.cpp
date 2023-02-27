#include "unordered_map"
#include "vector"

using namespace std;

class Solution {
    unordered_map<int,vector<int>> um;
public:
    Solution(vector<int>& nums) {
        srand(time(0));
        for(int i = 0;i<nums.size();i++){
            um[nums[i]].push_back(i);
        }
    }
    
    int pick(int target) {
        auto &v = um[target];
        int r = rand() % v.size();
        return v[r];
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(nums);
 * int param_1 = obj->pick(target);
 */