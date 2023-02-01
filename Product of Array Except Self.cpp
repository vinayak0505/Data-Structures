#include "vector"

using namespace std;
class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        int val = 1;
        int count = 0;
        for(int num:nums) if(num) val *= num; else count++;
        for(int& num: nums){
            if(num){
                if(count) num = 0;
                else num = val / num;
            }else {
                if(count > 1) num = 0;
                else num = val;
            }
        }
        return nums;
    }
};