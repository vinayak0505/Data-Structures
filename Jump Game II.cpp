#include "vector"

using namespace std;

class Solution {
public:
    int jump(vector<int>& nums,int in = 0,int j = 0,bool first = true) {
        vector<int>dp(nums.size(), -1) ;
        dp.front() = 0;
        for(int ind = 1; ind < nums.size(); ind++){
            int op = INT_MAX ;
            for(int i = 0 ; i < ind ; i++){
                if(i + nums[i] >= ind){
                    op = min(op, 1 + dp[i]) ;
                }
            }
            dp[ind] = op ;
        }
        return dp.back();
    }
};