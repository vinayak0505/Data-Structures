#include "vector"

using namespace std;

class Solution {
    vector<pair<int,int>> dirs = {
        {-1,-1},
        {-1,0},
        {-1,1},
        {1,-1},
        {1,0},
        {1,1},
        {0,-1},
        {0,1}
    };

    void fill(int i,int j,vector<vector<int>>& board){
        for(auto dir: dirs){
            int x = i + dir.first;
            int y = j + dir.second;
            if(x < 0 || y < 0 || x == board.size() || y == board.front().size())
                continue;
            board[x][y]++;
        }
    }
public:
    void gameOfLife(vector<vector<int>>& board) {
        int n = board.size(), m = board.front().size();
        vector<vector<int>> neighbors = vector<vector<int>>(n,vector<int>(m));
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(board[i][j]) fill(i,j,neighbors);
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(neighbors[i][j] == 2) continue;
                if(neighbors[i][j] == 3) board[i][j] = 1;
                else board[i][j] = 0;
            }
        }
    }
};