#include "vector"

using namespace std;

class Solution {
public:
    int equalPairs(vector<vector<int>>& grid) {
        int ans = 0;
        for(int i = 0;i<grid.size();i++){
            int sum = 0;
            for(int j = 0;j<grid.front().size();j++){
                bool isEqual = true;
                for(int k = 0;k<grid.size();k++){
                    if(grid[i][k] != grid[k][j]){
                        isEqual = false;
                        break;
                    }
                }
                if(isEqual) ans++;
            }
        }
        return ans;
    }
};