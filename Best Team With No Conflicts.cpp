#include "vector"

using namespace std;

class Solution {

    int getMax(vector<pair<int,int>>& com,vector<int>& dp,int till,int maxScore){
        int ans = 0;
        for(int i = 0;i<till;i++){
            if(com[i].second <= maxScore){
                ans = max(ans,dp[i]);
            }
        }
        return ans + maxScore;
    }

    int solve(vector<pair<int,int>>& com){
        vector<int> dp(com.size());
        int ans = 0;
        for(int i = 0;i<com.size();i++){
            dp[i] = getMax(com,dp,i,com[i].second);
            ans = max(dp[i],ans);
        }
        return ans;
    }
public:
    int bestTeamScore(vector<int>& scores, vector<int>& ages) {
        vector<pair<int,int>> com(scores.size());
        for(int i = 0;i<scores.size();i++){
            com[i] = {ages[i],scores[i]};
        }
        sort(com.begin(),com.end());
        return solve(com);
    }
};