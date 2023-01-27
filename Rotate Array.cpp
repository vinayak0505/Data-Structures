#include "vector"

using namespace std;

class Solution {
public:
    void rotate(vector<int>& nums, int k) {
        k %= nums.size();
        vector<int> ans = nums;
        for(int i = 0;i<nums.size();i++){
            nums[(i + k) % nums.size()] = ans[i];
        }
    }
};