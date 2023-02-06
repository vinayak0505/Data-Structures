#include "vector"

using namespace std;

class Solution {
public:
    vector<int> shuffle(vector<int>& nums, int n) {
        vector<int> newnums; int i=0;
        while(i!=(nums.size()-1))
              {
                if(i>=nums.size()&&i!=0){i=i%n;i++;}
                newnums.push_back(nums[i]);
                i+=n;
              }
        newnums.push_back(nums[i]);
        return newnums;
    }
};