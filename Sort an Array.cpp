#include "vector"

using namespace std;

class Solution {
    void _sortArray(vector<int>& nums,int start,int end) {
        if(start == end) return;
        int mid = (start + end)/2;
        _sortArray(nums,start,mid);
        _sortArray(nums,mid + 1,end);
        int p1 = start,p2 = mid + 1;
        vector<int> ans;
        while(p1 <= mid && p2 <= end){
            if(nums[p1] < nums[p2]){
                ans.push_back(nums[p1++]);
            }else{
                ans.push_back(nums[p2++]);
            }
        }

        while(p1 <= mid){
            ans.push_back(nums[p1++]);
        }

        while(p2 <= end){
            ans.push_back(nums[p2++]);
        }
        for(int i = 0;start <= end;start++,i++){
            nums[start] = ans[i];
        }
    }
public:
    vector<int> sortArray(vector<int>& nums) {
        _sortArray(nums,0,nums.size() - 1);
        return nums;
    }
};