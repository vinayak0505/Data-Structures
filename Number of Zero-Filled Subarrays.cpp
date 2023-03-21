#include "vector"

using namespace std;

class Solution {
    long long per(long long num){
        return num * (num + 1) / 2;
    }
public:
    long long zeroFilledSubarray(vector<int>& nums) {
        long long ans = 0;
        long long temp = 0;
        for(int i: nums){
            if(i){
                ans += per(temp);
                temp = 0;
            }else{
                temp++;
            }
        }
        return ans + per(temp);
    }
};