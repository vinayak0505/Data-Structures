#include "vector"

using namespace std;

class Solution {
    const int mod = 1e9 + 7;

    int pows(int a,int b){
        if(b == 1) return a % mod;
        if(b == 0) return 1;
        a %= mod;
        long long ans = pows(a,b/2);
        ans = (ans * ans) % mod;
        if(b % 2) ans = (ans * a) % mod;
        return ans % mod;
    }
public:
    int numSubseq(vector<int>& nums, int target) {
        int ans = 0;
        sort(nums.begin(),nums.end());
        for(int start = 0, end = nums.size() - 1; start < nums.size(); start++){
            while(end > start && nums[start]+nums[end] > target) {
                end--;
            }
            if(nums[start]+nums[end] > target) break;
            int value = pows(2, end - start);
            ans = (ans + value) % mod;
        }
        return ans;
    }
};