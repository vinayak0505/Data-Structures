#include "vector"

using namespace std;

class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        vector<vector<int>> dp(grid.size() + 1,vector<int>(grid.front().size() + 1,1e5));
        dp[1][0] = 0;
        for(int i = 1;i<dp.size();i++){
            for(int j = 1;j<dp.front().size();j++){
                dp[i][j] = min({dp[i][j],dp[i - 1][j] + grid[i - 1][j - 1],
                                dp[i][j - 1] + grid[i - 1][j - 1]});
            }
        }
        return dp.back().back();
    }
};