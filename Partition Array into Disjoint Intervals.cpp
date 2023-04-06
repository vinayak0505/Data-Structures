#include "vector"

using namespace std;

class Solution {
public:
    int partitionDisjoint(vector<int>& nums) {
        vector<int> maximum;
        vector<int> minimum = vector<int>(nums.size());
        int m = 0;
        for(int i: nums){
            m = max(m,i);
            maximum.push_back(m);
        }
        m = 1e6;
        for(int i = nums.size() - 1;i >= 0;i--){
            m = min(m,nums[i]);
            minimum[i] = m;
        }
        for(int i = 1;i<nums.size();i++){
            if(maximum[i - 1] <= minimum[i]) return i;
        }
        return -1;
    }
};