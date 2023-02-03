#include "vector"

using namespace std;
class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        int slow = nums[slow],fast = nums[nums[fast]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while(fast != slow){
            fast = nums[fast];
            slow = nums[slow];
        }
        return fast;
    }
};