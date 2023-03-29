#include "vector"

using namespace std;

class Solution {
public:
    int maxSatisfaction(vector<int>& s) {
        vector<bool> taken = vector<bool>(s.size(),1);
        sort(s.begin(),s.end());
        int val = 0;
        for(int i = s.size() - 1; i >= 0;i--){
            if(-s[i] > val){
                taken[i] = false;
            }else{
                val += s[i];
            }
        }
        val = 0;
        for(int i = 0,j = 0;i<s.size();i++){
            if(taken[i]){
                val += ++j * s[i];
            }
        }
        return val;
    }
};