#include "vector"

using namespace std;
class Solution {
    int n = 32;
public:
    int singleNumber(vector<int>& nums) {
        vector<int> arr(n);
        for(int i = 0;i<n;i++){
            for(int j:nums){
                if(j & (1 << i)) arr[i]++;
            }
        }
        int ans = 0;
        for(int i = 0;i<n;i++){
            ans |= (arr[i] % 3) << i;
        }
        return ans;
    }
};