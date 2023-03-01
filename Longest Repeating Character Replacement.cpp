#include "vector"
#include "string"

using namespace std;

class Solution {
    int solve(vector<vector<int>>&dp,int c, int k){
        int start = 0;
        int ans = 0;

        for(int i = 0;i<dp.size();i++){
            while(i - start - k > dp[i][c] - dp[start][c] && start <= i) start++;
            ans = max(ans,i - start);
        }

        return ans;
    }
public:
    int characterReplacement(string s, int k) {
        vector<vector<int>> dp(s.size() + 1,vector<int>(26));
        for(int i = 1;i<dp.size();i++){
            dp[i] = dp[i - 1];
            dp[i][s[i - 1] - 'A']++;
        }
        int ans = 0;
        for(int i = 0;i < 26; i++){
            ans = max(ans, solve(dp,i,k));
        }
        return ans;
    }
};