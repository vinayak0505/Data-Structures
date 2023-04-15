#include "vector"
#include "unordered_map"

using namespace std;

class Solution {
    unordered_map<int,unordered_map<int,int>> mp;
    int solve(vector<vector<int>>& piles,int k,int i){
        if(i == piles.size()){
            return 0;
        }
        if(mp[k].count(i)) return mp[k][i];
        int ans = solve(piles,k,i+1);
        for(int j = 0;j<k && j < piles[i].size();j++){
            ans = max(ans,solve(piles,k - j - 1,i+1) + piles[i][j]);
        }
        return mp[k][i] = ans;
    }
public:
    int maxValueOfCoins(vector<vector<int>>& piles, int k) {
        for(auto &pile:piles){
            int sum = 0;
            for(int &coin: pile){
                sum += coin;
                coin = sum;
            }
        }
    return solve(piles,k,0);
    }
};