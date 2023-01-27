#include "vector"

using namespace std;

class Solution {
    pair<int,int> dir[4] = {
        {1,0},
        {0,1},
        {-1,0},
        {0,-1}
    };

    void fill(vector<vector<char>>& grid,int i,int j){
        if(i < 0 || j < 0 || i == grid.size() || j == grid.front().size()) 
            return;
        if(grid[i][j] == '0') return;
        grid[i][j] = '0';
        for(auto d:dir){
            fill(grid,i + d.first,j + d.second);
        }
    }

public:
    int numIslands(vector<vector<char>>& grid) {
        int ans = 0;
        for(int i = 0;i<grid.size();i++){
            for(int j = 0;j<grid.front().size();j++){
                if(grid[i][j] == '1')
                    ans++;
                fill(grid,i,j);
            }
        }
        return ans;
    }
};