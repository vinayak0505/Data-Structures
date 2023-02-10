#include "queue"

using namespace std;

class Point{
    public:
    int x;
    int y;
    int d;
    Point(int x,int y, int d): x(x),y(y),d(d){}
};
class Solution {
public:
    int maxDistance(vector<vector<int>> grid) {
        pair<int,int> dirs[] = {
            {0,1},  
            {1,0},  
            {0,-1},  
            {-1,0}
        };
        queue<Point> q;
        int last_count = 0;
        for(int i = 0;i<grid.size();i++){
            for(int j = 0;j<grid.front().size();j++){
                if(grid[i][j]){
                    q.push(Point(i,j,0));
                    grid[i][j] = 0;
                    last_count++;
                }else{
                    grid[i][j] = 200;
                }
            }
        }
        if(last_count == 0 || last_count == grid.size() * grid.front().size()) 
            return -1;
        while(q.size()){
            auto point = q.front();
            q.pop();
            for(auto dir:dirs){
                int newx = dir.first + point.x;
                int newy = dir.second + point.y;
                if(newx < 0 || newy < 0 || 
                newx == grid.size() || 
                newy == grid.front().size())
                continue;
                if(grid[newx][newy] > point.d + 1){
                    q.push(Point(newx,newy,point.d + 1));
                    grid[newx][newy] = point.d + 1;
                }
            }
        }
        int ans = 0;
        for(auto &i:grid) for(int i:i) ans = max(i,ans);
        return ans;
    }
};