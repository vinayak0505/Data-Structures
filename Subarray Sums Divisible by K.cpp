#include "unordered_map"
#include "vector"

using namespace std;

class Solution {
    int per(int a){
        int ans = 0;
        for(int i = 1;i<a;i++) ans += i;
        return ans;
    }
public:
    int subarraysDivByK(vector<int>& nums, int k) {
        unordered_map<int,int> mp;
        int pre = 0;
        mp[0] = 1;
        int val = 1e4 - (int)(1e4)%k + k;
        for(int i: nums){
            pre = (pre + i + val)%k;
            mp[pre]++;
        }
        int ans = 0;
        for(auto a:mp){
            ans += per(a.second);
        }
        return ans;
    }
};