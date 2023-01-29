#include "vector"

using namespace std;
class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int ans = 0;
        int first = 0;
        int sum = 0;
        for(int i = 0;i<nums.size();i++){
            sum += nums[i];
            while(target <= sum - nums[first]){
                sum -= nums[first];
                first++;
            }
            if(target <= sum){
                if(ans == 0) ans = i - first + 1;
                else ans = min(ans,i - first + 1);
            }
        }
        return ans;
    }
};