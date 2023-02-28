#include "vector"

using namespace std;

class Solution {
    vector<pair<int,int>> dirs = {
        {0,1},
        {0,-1},
        {-1,0},
        {1,0}
    };

    void fill(vector<vector<char>>& board,int i,int j){
        if(board[i][j] == '.') return;
        board[i][j] = '.';
        for(auto dir: dirs){
            int x = i + dir.first;
            int y = j + dir.second;
            if(x < 0 || y <0 || x == board.size() || y == board.front().size())
                continue;
            fill(board,x,y);
        }
    }
public:
    int countBattleships(vector<vector<char>>& board) {
        int count = 0;
        for(int i = 0;i<board.size();i++){
            for(int j = 0;j<board.front().size();j++){
                if(board[i][j] == 'X'){
                    count++;
                    fill(board,i,j);
                }
            }
        }
        return count;
    }
};