#include "unordered_map"
#include "string"

using namespace std;

class Solution {
    int count = 0;
    unordered_map<char,int> dp;
    void fill(int n){
        string num = to_string(n);
        count = num.size();
        for(char c: num) dp[c]++;
    }

    bool check(int n){
        string num = to_string(n);
        if(num.size() != count) return false;
        unordered_map<char,int> dp1 = dp;
        for(char c: num) dp1[c]--;
        for(auto d: dp1) if(d.second) return false;
        return true;
    }
public:
    bool reorderedPowerOf2(int n) {
        fill(n);
        for(int i = 1;i<1e9;i *= 2){
            if(check(i)) return true;
        }
        return false;
    }
};