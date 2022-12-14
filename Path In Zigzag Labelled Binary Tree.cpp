#include "vector"

using namespace std;

class Solution {
public:
    vector<int> pathInZigZagTree(int label) {
        vector<int> ans;
        while(label){
            ans.push_back(label);
            label/=2;
        }
        int power = (ans.size() % 2) ? 2 : 1;
        reverse(ans.begin(),ans.end());
        for(int i = (ans.size() % 2 != 0);i<ans.size();i+=2){
            ans[i] = power * 3 - 1 - ans[i];
            power *= 4;
        }
        return ans;
    }
};