#include "vector"

using namespace std;

class Solution {

    pair<int,int> dirs[4] = {
        {0,1},
        {0,-1},
        {-1,0},
        {1,0}
    };
    pair<int,bool> can(vector<vector<int>>& grid,int i,int j){
        if(i < 0|| j < 0|| i == grid.size() || j == grid.front().size()){
            return {0,0};
        }
        if(grid[i][j] == 0) return {0,1};
        grid[i][j] = 0;
        pair<int,bool> ans = {1,1};
        for(auto dir: dirs){
            int x = i + dir.first;
            int y = j + dir.second;
            auto a = can(grid,x,y);
            ans.first += a.first;
            ans.second &= a.second;
        }
        return ans;
    }
public:
    int numEnclaves(vector<vector<int>>& grid) {
        int ans =0;
        for(int i = 0;i<grid.size();i++){
            for(int j = 0;j<grid.front().size();j++){
                if(grid[i][j]){
                    auto a = can(grid,i,j);
                    if(a.second) ans += a.first;
                }
            }
        }
        return ans;
    }
};