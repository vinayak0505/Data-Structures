#include "vector"

using namespace std;

class Solution {
public:
    vector<int> singleNumber(vector<int>& nums) {
        long long a = 0;
        for(int num: nums) a ^= num;
        a = a & -a;
        long long b = 0,c = 0;
        for(int num: nums){
            if(a & num) b ^= num;
            else c ^= num;
        }
        return {(int)b,(int)c};
    }
};