#include "set"
#include "vector"

using namespace std;
class Solution {
    set<vector<int>> ans;

    void solve(int i,vector<int>& nums,vector<int>& a){
        if(i == nums.size()){
            if(a.size() > 1) ans.insert(a);
            return;
        }
        solve(i + 1,nums,a);
        if(a.size() == 0 || a.back() <= nums[i]){
            a.push_back(nums[i]);
            solve(i + 1,nums,a);
            a.pop_back();
        }
    }

public:
    vector<vector<int>> findSubsequences(vector<int>& nums) {
        vector<int> a;
        solve(0,nums,a);
        vector<vector<int>> val(ans.begin(),ans.end());
        return val;
    }
};