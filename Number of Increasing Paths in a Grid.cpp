#include "vector"
#include "unordered_map"

using namespace std;
 
class Solution {

    pair<int,int> arr[4] = {
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };

    int mod = 1e9 + 7;
    unordered_map<int,unordered_map<int,int>> dp;
    int count(vector<vector<int>>& grid, int x,int y){
        if(grid[x][y] < 0) return 0;
        if(dp[x].count(y)) return dp[x][y];
        int ans = 1;
        grid[x][y] *= -1;
        for(auto ar : arr){
            int a = ar.first + x;
            int b = ar.second + y;
            if(a < 0 || b < 0) continue;
            if(a == grid.size() || b == grid.front().size()) continue;
            if((grid[x][y] * -1) < grid[a][b]) {
                ans += count(grid,a,b);
                ans %= mod;
            }
        }
        grid[x][y] *= -1;
        return dp[x][y] = ans;
    }
public:
    int countPaths(vector<vector<int>>& grid) {
        int ans = 0;
        for(int i = 0;i<grid.size();i++){
            for(int j = 0;j<grid.front().size();j++){
                ans += count(grid,i,j);
                ans %= mod;
            }
        }
        return ans;
    }
};
