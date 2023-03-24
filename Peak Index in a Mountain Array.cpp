#include "vector"

using namespace std;

class Solution {
public:
    int peakIndexInMountainArray(vector<int>& nums) {
        if(nums.size() == 1) return 0;
        int a = 0,b = nums.size() - 1;
        while(a != b){
            int mid = (b + a)/2;
            bool val = true;
            if(mid == 0){
                if(nums[mid] > nums[mid + 1]) return mid;
                a = mid + 1;
            }else if(mid == nums.size() - 1){
                if(nums[mid] > nums[mid - 1]) return mid;
                b = mid - 1;
            }else{
                if(nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) return mid;
                if(nums[mid] > nums[mid + 1]) b = mid - 1;
                else a = mid + 1;
            }
        }
        return a;
    }
};