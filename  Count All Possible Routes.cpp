#include "vector"
#include "unordered_map"

using namespace std;

class Solution {
    int mod(int a){
        return a > 0 ? a : -a;
    }
    const int MOD = 1e9 + 7;
    unordered_map<int,unordered_map<int,int>> dp;
public:
    int countRoutes(vector<int>& locations, int start, int finish, int fuel) {
        if(dp[start].count(fuel)) return dp[start][fuel];
        int ans = 0;
        if(start == finish) ans = 1;
        for(int i = 0;i < locations.size();i++){
            if(i == start) continue;
            if(fuel < mod(locations[start] - locations[i])) continue;
            ans = (ans + countRoutes(locations,i,finish,fuel - mod(
                locations[start] - locations[i]
            ))) % MOD;
        }
        return dp[start][fuel] = ans;
    }
};