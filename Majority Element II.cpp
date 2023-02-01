#include "vector"

using namespace std;

class Solution {

    bool valid(vector<int>&nums,int a){
        int count = 0;
        for(int num:nums) if(num == a) count++;
        return (nums.size()/3) < count;
    }
public:
    vector<int> majorityElement(vector<int>& nums) {
        int num1 = INT_MAX,num2 = INT_MAX,c1 = 0,c2 = 0;
        for(int num:nums){
            if(num == num1) c1++;
            else if(num == num2) c2++;
            else if(c1 == 0) num1 = num,c1 = 1;
            else if(c2 == 0) num2 = num,c2 = 1;
            else c1--,c2--;
        }
        vector<int> ans;
        if(valid(nums,num1)) ans.push_back(num1);
        if(valid(nums,num2)) ans.push_back(num2);
        return ans;
    }
};