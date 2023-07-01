#include "vector"

using namespace std;

class Solution {
    vector<int> arr;
    int ans = INT_MAX;
    void fill(vector<int>& cookies, int l){
        if(l == cookies.size()){
            int tempans = 0;
            for(int i: arr) tempans = max(tempans,i);
            ans = min(tempans,ans);
            return;
        }
        for(int i = 0;i<arr.size();i++){
            arr[i] += cookies[l];
            fill(cookies,l+1);
            arr[i] -= cookies[l];
        }
    }
public:
    int distributeCookies(vector<int>& cookies, int k) {
        arr = vector<int>(k);
        fill(cookies,0);
        return ans;
    }
};