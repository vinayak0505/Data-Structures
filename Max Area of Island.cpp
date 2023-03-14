#include "queue"

using namespace std;

class Solution {
    pair<int,int> dirs[4] = {
        {0,1},
        {0,-1},
        {-1,0},
        {1,0}
    };

    int area(vector<vector<int>>& grid,int x,int y){
        int ans = 1;
        queue<pair<int,int>> q;
        grid[x][y] = 0;
        q.push({x,y});
        while(q.size()){
            auto front = q.front();
            q.pop();
            for(auto dir: dirs){
                int newx = front.first + dir.first;
                int newy = front.second + dir.second;
                if(newx < 0 || newy < 0 || 
                newx == grid.size() || newy == grid.front().size())
                continue;
                if(grid[newx][newy]){
                    grid[newx][newy] = 0;
                    ans++;
                    q.push({newx,newy});
                }
            }
        }
        return ans;
    }
public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int ans = 0;
        for(int i = 0;i<grid.size();i++){
            for(int j = 0;j<grid.front().size();j++){
                if(grid[i][j]) ans = max(ans,area(grid,i,j));
            }
        }
        return ans;
    }
};