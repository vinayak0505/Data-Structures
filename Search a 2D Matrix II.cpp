#include "vector"

using namespace std;

class Solution {

public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int x = matrix.size() - 1, y = 0;

        while(x != -1 && y != matrix.front().size()){
            int val = matrix[x][y];
            if(val == target) return true;
            if(val < target) y++;
            else x--;
        }

        return false;
    }
};