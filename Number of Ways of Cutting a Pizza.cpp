#include "vector"
#include "string"
#include "unordered_map"

using namespace std;

class Solution {

    vector<vector<int>> countdp;
    int mod = 1e9 + 7;
    void fillDp(vector<string>& pizza){
        countdp = vector<vector<int>>(
            pizza.size() + 1,
            vector<int>(pizza.front().size() + 1)
        );
        for(int i = 0;i<pizza.size();i++){
            for(int j = 0;j<pizza.front().size(); j++){
                countdp[i + 1][j + 1] = 
                (pizza[i][j] == 'A') +
                countdp[i][j + 1] +
                countdp[i + 1][j] -
                countdp[i][j];
            }
        }
    }

    bool check(pair<int,int> from,pair<int,int> to){
        return (
            countdp[to.first][to.second] -
            countdp[from.first][to.second] -
            countdp[to.first][from.second] +
            countdp[from.first][from.second]
        );
    }
    unordered_map<int,int> dp;
    int key(int k,int i,int j){
        return k + i * 11 + j * 561;
    }
    int ways(vector<string> &pizza,int k,int i,int j){
        if(i == countdp.size() || j == countdp.front().size()) return 0;
        if(k > countdp.back().back() - countdp[i][j]) return 0;
        int ke = key(k,i,j);
        if(dp.count(ke)) return dp[ke];
        if(k == 1){
            bool v = (check({i,j},{pizza.size(),pizza.back().size()}));
            return v;
        }
        int ans = 0;
        for(int ni = i + 1;ni <= pizza.size(); ni++){
            if(check({i,j},{ni,pizza.front().size()})){
                ans = (ways(pizza,k-1,ni,j) + ans) % mod;
            }
        }
        for(int nj = j;nj <= pizza.front().size(); nj++){
            if(check({i,j},{pizza.size(),nj})){
                ans = (ways(pizza,k-1,i,nj) + ans) % mod;
            }
        }
        return dp[ke] = ans % mod;
    }
public:
    int ways(vector<string>& pizza, int k) {
        fillDp(pizza);
        return ways(pizza,k,0,0);
    }
};