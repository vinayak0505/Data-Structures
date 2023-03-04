#include "vector"
#include "unordered_set"

using namespace std;

class Solution {
    bool check(vector<int>& nums,int i){
        if((nums[i] + nums.size()) % nums.size() == 0) return false;
        unordered_set<int> dp;
        int num = i;
        while(true){
            if(nums[num] > 0 && nums[i] < 0) return false;
            if(nums[num] < 0 && nums[i] > 0) return false;
            if((nums[num] + nums.size()) % nums.size() == 0) return false;
            num = (nums[num] + num + nums.size()) % nums.size();
            if(dp.count(num)) return true;
            dp.insert(num);
        }
        return false;
    }
public:
    bool circularArrayLoop(vector<int>& nums) {
        for(int i = 0;i<nums.size();i++){
            if(check(nums,i)) return true;
        }
        return false;
    }
};