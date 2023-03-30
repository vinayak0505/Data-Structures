#include "string"
#include "unordered_map"

using namespace std;

class Solution {
    unordered_map<string,bool> dp;
public:
    bool isScramble(string s1, string s2) {
        if(s1 == s2) return true;
        if(s1.size() < 2) return false;
        string key = s1 + "+" + s2;
        if(dp.count(key)) return dp[key];
        int n = s1.size();
        for(int i = 1;i<n;i++){
            if(
                (
                    isScramble(s1.substr(0, i) ,s2.substr(0,i)) and 
                    isScramble(s1.substr(i) ,s2.substr (i))
                ) 
                    or
                (
                    isScramble(s1.substr(0,i),s2.substr(n-i)) and
                    isScramble(s1.substr(i),s2.substr(0,n-i))
                )
            ) return dp[key] = true;
        }
        return dp[key] = false;
    }
};