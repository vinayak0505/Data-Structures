#include "vector"

using namespace std;

class Solution {
public:
    int findUnsortedSubarray(vector<int>& nums) {
        auto ma = nums, mi = nums;
        int ans = -1e5;
        for(int &i:ma){
            i = max(i,ans);
            ans = i;
        }
        ans = INT_MAX;
        for(int i = nums.size() - 1;i >= 0;i--){
            mi[i] = min(ans,mi[i]);
            ans = mi[i];
        }
        int i = 0, j = nums.size() - 1;
        while(i != nums.size() && mi[i] == nums[i]) i++;
        while(j && ma[j] == nums[j]) j--;
        if(i > j) return 0;
        return j - i + 1;
    }
};