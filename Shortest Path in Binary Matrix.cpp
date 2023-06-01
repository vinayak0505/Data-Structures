#include "vector"
#include "queue"

using namespace std;

class Solution {
    pair<int,int> dirs[8] = {
        {-1,-1}, // top left
        {-1,0}, // top
        {-1,1}, // top right
        {0,1}, // right
        {1,1}, // bottom right
        {1,0}, // bottom
        {1,-1}, // bottom left
        {0,-1} // left
    };
public:
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
        int count = 0;
        queue<pair<int,int>> q;
        q.push({0,0});
        while(q.size()){
            count++;
            int size = q.size();
            while(size--){
                auto [x,y] = q.front();
                q.pop();
                if(grid[x][y]) continue;
                grid[x][y] = 1;
                if(x == grid.size() - 1 && y == grid.front().size() - 1)
                return count;
                for(auto dir : dirs){
                    int a = dir.first + x;
                    int b = dir.second + y;
                    if(a == grid.size() || b == grid.front().size())
                    continue;
                    if(a == -1 || b == -1) continue;
                    if(grid[a][b]) continue;
                    q.push({a,b});
                }
            }
        }
        return -1;
    }
};