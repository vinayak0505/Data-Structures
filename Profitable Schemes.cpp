#include "vector"

using namespace std;

class Solution {
public:

    const int mod = 1e9 + 7;
    int profitableSchemes(int n, int minProfit, vector<int>& group, vector<int>& profit) {
        auto dp = vector<vector<vector<int>>>(group.size() + 1,
        vector<vector<int>>(201,vector<int>(minProfit+1,0)));

        for(int j = 0;j<n + 1;j++){
            dp[group.size()][j][minProfit] = 1;
        }
        for(int i = group.size() - 1;i >= 0;i--){
            for(int j = 0;j<=n;j++){
                for(int k = 0;k<=minProfit;k++){
                    dp[i][j][k] = dp[i+1][j][k];
                    if(j + group[i] <= n){
                    dp[i][j][k] = 
                    (dp[i][j][k] + 
                    dp[i+1][j+group[i]][min(k+profit[i],minProfit)]) % mod;
                    }
                }
            }
        }
        return dp[0][0][0];
    }
};