#include "unordered_map"
#include "string"

using namespace std;

class Solution {
    int mod = 1e9 + 7;
    unordered_map<int,int> dp;
    int help(string& s,int i,int k){
        if(i == s.size()) return 1;
        if(s[i] == '0') return 0;
        if(dp.count(i)) return dp[i];
        long long num = 0;
        int ans = 0;
        for(int j = i;j<s.size();j++){
            num = num * 10 + s[j] - '0';
            if(num > k) break;
            ans += help(s,j+1,k);
            ans %= mod;
        }
        return dp[i] = ans;
    }
public:
    int numberOfArrays(string s, int k) {
        return help(s,0,k);
    }
};