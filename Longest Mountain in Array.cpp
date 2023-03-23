#include "vector"

using namespace std;

class Solution {
public:
    int longestMountain(vector<int>& arr) {
        vector<int> left = vector<int>(arr.size());
        vector<int> right = vector<int>(arr.size());
        int sum = 0;
        int pre = arr.front();
        for(int i = 0;i<left.size();i++){
            if(pre < arr[i]){
                sum++;
            }else sum = 0;
            left[i] = sum;
            pre = arr[i];
        }
        sum = 0,pre = arr.back();
        for(int i = right.size() - 1;i >= 0;i--){
            if(pre < arr[i]){
                sum++;
            }else sum = 0;
            right[i] = sum;
            pre = arr[i];
        }
        int ans = 0;
        for(int i = 0;i<arr.size();i++){
            // cout<<arr[i]<<left[i]<<right[i]<<endl;
            if(left[i] && right[i])
                ans = max(ans,left[i] + right[i] + 1);
        }

        if(ans < 3) return 0;
        return ans;
    }
};