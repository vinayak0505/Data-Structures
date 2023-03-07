#include "vector"
#include "unordered_map"

using namespace std;

class Solution {
public:
    bool checkSubarraySum(vector<int>& nums, int k) {
        unordered_map<int,int> sum;
        sum[0] = -1;
        int total_sum = 0;
        for(int i = 0;i < nums.size(); i++){
            total_sum = (total_sum + nums[i]) % k;
            if(sum.count(total_sum)){
                if(i - sum[total_sum] > 1) return true;
            }else{
                sum[total_sum] = i ;
            }
        }
        return false;
    }
};