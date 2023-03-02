#include "vector"

using namespace std;

class Solution {
public:
    vector<int> findDuplicates(vector<int>& nums) {
        vector<int> ans;
        for(int num: nums){
            if(num < 0) num *= -1;
            if(nums[num - 1] < 0){
                ans.push_back(num);
            }else nums[num - 1] *= -1;
        }
        return ans;
    }
};