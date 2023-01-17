#include "vector"
#include "string"

using namespace std;

class Solution {
public:
    int minFlipsMonoIncr(string s) {
        vector<int> arr(s.size());
        int pre = 0;
        for(int i = 0;i<arr.size();i++){
            if(s[i] == '1') pre++;
            arr[i] = pre;
        }
        int ans = s.size() - arr.back();
        for(int i = s.size() - 1; i>=0; i--){
            int j = s.size() - i - 1 - (arr.back() - arr[i]);
            ans = min(ans,arr[i] + j);
        }
        return ans;
    }
};