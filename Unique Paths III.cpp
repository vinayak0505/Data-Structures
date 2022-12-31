
#include "vector"

using namespace std;

typedef vector<vector<int>> vvi;

class Solution {
    vector<int> startingPoint(const vvi &grid){
        for(int i = 0;i<grid.size();i++){
            for(int j = 0;j<grid.front().size();j++){
                if(grid[i][j] == 1) return {i,j};
            }
        }
        return {-1,-1};
    }

    int blocks(const vvi &grid){
        int ans = 0;
        for(int i = 0;i<grid.size();i++){
            for(int j = 0;j<grid.front().size();j++){
                if(grid[i][j] == -1) ans++;
            }
        }
        return ans;
    }

    int ways(int i,int j, vvi &grid, vvi&vis,int c){
        if(grid.size() == i || grid.front().size() == j) return false;
        if(i < 0 || j < 0) return false;
        if(vis[i][j] || grid[i][j] == -1) return false;
        c++;
        if(c == grid.size() * grid.front().size() && grid[i][j] == 2){
            return true;
        }
        if(c == grid.size() * grid.front().size() || grid[i][j] == 2){
            return false;
        }
        vis[i][j] = true;
        int ans = 0;
        ans += ways(i, j + 1, grid, vis, c);
        ans += ways(i + 1, j, grid, vis, c);
        ans += ways(i - 1, j, grid, vis, c);
        ans += ways(i, j - 1, grid, vis, c);
        vis[i][j] = false;
        return ans;
    }
public:
    int uniquePathsIII(vvi& grid) {
        auto point = startingPoint(grid);
        vvi vis = vvi(grid.size(),
        vector<int> (grid.front().size(),false));
        int c = blocks(grid);
        return ways(point.front(),point.back(),grid,vis,c);
        return 0;
    }
};