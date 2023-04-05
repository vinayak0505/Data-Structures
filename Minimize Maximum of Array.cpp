#include "vector"

using namespace std;

class Solution {
    bool check(int num, vector<int> nums){
        long long pre = 0;
        for(int i = nums.size() - 1;i >= 0;i--){
            if(nums[i] > num){
                pre += nums[i] - num;
            }else{
                pre = max(0ll,pre - (num - nums[i]));
            }
        }
        return pre == 0;
    }
public:
    int minimizeArrayValue(vector<int>& nums) {
        int end = 0, start = nums.front();
        for(int i : nums) end = max(i,end),start = min(start,i);

        int ans = 0;
        while(start <= end){
            int mid = (start + end) / 2;
            if(check(mid,nums)){
                end = mid - 1;
                ans = mid;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }
};