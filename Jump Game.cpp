
#include "vector"

using namespace std;

class Solution {
public:
    bool canJump(vector<int>& nums) {
        vector<bool> ans(nums.size(),false);
        ans.front() = true;
        for(int i = 0;i<nums.size();i++){
            if(ans[i] == false) return false;
            for(int j = i + nums[i];j > i;j--){
                if(j >= nums.size() - 1) return true;
                if(ans[j] == true) break;
                ans[j] = true;
            }
        }
        return ans.back();
    }
};