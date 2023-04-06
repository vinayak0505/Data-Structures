#include "vector"

using namespace std;

class Solution {
    pair<int,int> dirs[4] = {
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };

    bool isClosed(vector<vector<int>>& grid,int x,int y){
        if(x < 0 || y < 0 || x == grid.size() || 
        y == grid.front().size()) return false;
        if(grid[x][y]) return true;
        grid[x][y] = true;
        bool ans = true;
        for(auto dir: dirs){
            int newx = dir.first + x;
            int newy = dir.second + y;
            ans &= isClosed(grid,newx,newy);
        }
        return ans;
    }
public:
    int closedIsland(vector<vector<int>>& grid) {
        int ans = 0;
        for(int i = 0;i<grid.size();i++){
            for(int j = 0;j< grid.front().size();j++){
                if(grid[i][j]) continue;
                if(isClosed(grid,i,j)) ans++;
            }
        }
        return ans;
    }
};