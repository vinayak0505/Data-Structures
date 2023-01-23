#include "vector"

using namespace std;
class Solution {
    int ans;
    void find(vector<int> &n,int a,int b){
        if(n[a] < n[b] || a == b){
            ans = n[a];
            return;
        }
        int mid = (a + b)/2;
        if(n[a] <= n[mid])
        find(n,mid + 1,b);
        else find(n,a,mid);
    }
public:
    int findMin(vector<int>& nums) {
        if(nums.front() < nums.back()) return nums.front();
        find(nums,0,nums.size() - 1);
        return ans;
    }
};