#include "vector"

using namespace std;

class Solution {
    vector<vector<bool>> p,a;
    vector<pair<int,int>> dirs = {
        {0,-1},
        {0,1},
        {-1,0},
        {1,0}
    };

    void filla(int x,int y,vector<vector<int>>& heights){
        if(a[x][y]) return;
        a[x][y] = true;
        int i,j;
        for(auto dir: dirs){
            i = x + dir.first;
            j = y + dir.second;
            if(i < 0 || j < 0 || i == heights.size() 
            || j == heights.front().size())
            continue;
            if(heights[x][y] <= heights[i][j])
                filla(i,j,heights);
        }
    }

    void fillb(int x,int y,vector<vector<int>>& heights){
        if(p[x][y]) return;
        p[x][y] = true;
        int i,j;
        for(auto dir: dirs){
            i = x + dir.first;
            j = y + dir.second;
            if(i < 0 || j < 0 || i == heights.size() 
            || j == heights.front().size())
            continue;
            if(heights[x][y] <= heights[i][j])
                fillb(i,j,heights);
        }
    }
    void solvea(vector<vector<int>>& heights){
        for(int i = 0;i<heights.size();i++){
            filla(i,0,heights);
        }
        for(int i = 0;i<heights.front().size();i++){
            filla(0,i,heights);
        }

    }
    void solveb(vector<vector<int>>& heights){
        for(int i = 0;i<heights.size();i++){
            fillb(i,heights.front().size() - 1,heights);
        }
        for(int i = 0;i<heights.front().size();i++){
            fillb(heights.size() - 1,i,heights);
        }
    }
public:
    vector<vector<int>> pacificAtlantic(vector<vector<int>>& heights) {
        p = vector<vector<bool>> (heights.size(),vector<bool>(heights.front().size()));
        a = vector<vector<bool>> (heights.size(),vector<bool>(heights.front().size()));
        solvea(heights);
        solveb(heights);
        vector<vector<int>> ans;
        for(int i = 0;i < heights.size(); i++){
            for(int j = 0;j < heights.front().size(); j++){
                if(a[i][j] && p[i][j]) ans.push_back({i,j});
            }
        }
        return ans;
    }
};