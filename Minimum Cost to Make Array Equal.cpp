#include<vector>

using namespace std;

class Solution {

    long long solve(int to,vector<int>& nums, vector<int>& cost){
        long long ans = 0;
        for(int i = 0;i<nums.size();i++){
            ans += (long long)abs(to - nums[i]) * cost[i];
        }
        return ans;
    }
    int getMediun(long long c, vector<pair<int,int>>& arr){
        int i = 0;
        while(c >= 0){
            c -= arr[i++].second;
        }
        return arr[i-1].first;
    }
public:
    long long minCost(vector<int>& nums, vector<int>& cost) {
        vector<pair<int,int>> arr(nums.size());
        for(int i = 0;i<nums.size();i++){
            arr[i] = {nums[i],cost[i]};
        }
        sort(arr.begin(),arr.end());
        long long total = 0;
        for(int i : cost) total += i;
        total /= 2;
        return min(
            solve(getMediun(total,arr),nums,cost),
            solve(getMediun(total+1,arr),nums,cost)
        );
    }
};