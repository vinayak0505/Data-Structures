#include "unordered_map"
#include "string"

using namespace std;

class Solution {
    int getKey(int a,int b){
        return a * 1000 + b;
    }
    unordered_map<int,int> dp;
    int count(string& s,int a,int b){
        if(b <= a) return 0;
        int key = getKey(a,b);
        if(dp.count(key)) return dp[key];
        if(s[a] == s[b]) return dp[key] = count(s,a+1,b-1);
        return dp[key] = min(count(s,a+1,b),count(s,a,b-1)) + 1;
    }
public:
    int minInsertions(string s) {
        return count(s,0,s.size() - 1);
    }
};