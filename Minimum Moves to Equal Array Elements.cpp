#include "vector"

using namespace std;

class Solution {
public:
    int minMoves(vector<int>& nums) {
        int m = INT_MAX;
        for(int num: nums){
            m = min(num, m);
        }
        int ans = 0;
        for(int num: nums){
            ans += num - m;
        }
        return ans;
    }
};