#include "unordered_map"
#include "vector"

using namespace std;

class Solution {
    unordered_map<int,unordered_map<int,pair<int,int>>> dp;
    pair<int,int> findMax(vector<int>& piles,int i,int m, bool a){
        if(i == piles.size()) return {0,0}; 
        if(dp[i].count(m)) {
            if(a) return dp[i][m];
            return {dp[i][m].second,dp[i][m].first};
        }
        int sum = 0;
        pair<int,int> finalans;
        for(int j = i;(j<i + 2 * m) && j<piles.size();j++){
            sum += piles[j];
            auto val = findMax(piles,j + 1,max(j + 1 - i,m),!a);
            if(a){
                if(finalans.first < val.first + sum){
                    finalans = {val.first + sum, val.second};
                }
            }
            else if(finalans.second < val.second + sum){
                finalans = {val.first, val.second + sum};
            }
        }
        if(a) dp[i][m] = finalans;
        else dp[i][m] = {finalans.second,finalans.first};
        return finalans;
    }
public:
    int stoneGameII(vector<int>& piles) {
        return findMax(piles,0,1,true).first;
    }
};