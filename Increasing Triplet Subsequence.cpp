#include "vector"

using namespace std;
class Solution
{
public:
    bool increasingTriplet(vector<int> &nums)
    {
        vector<int> greater = nums, smaller = nums;
        for (int i = 1; i < nums.size(); i++)
            smaller[i] = min(nums[i], smaller[i - 1]);

        for (int i = nums.size() - 2; i >= 0; i--)
            greater[i] = max(nums[i], greater[i + 1]);

        for (int i = 0; i < nums.size(); i++)
            if (smaller[i] < nums[i] && nums[i] < greater[i])
                return true;
                
        return false;
    }
};