
#include "vector"
using namespace std;
class Solution {
public:
    int minFallingPathSum(vector<vector<int>>& matrix) {
        auto dp = matrix;
        for(int i = 1;i<matrix.size();i++){
            dp[i][0] = min(dp[i - 1][0],
                    dp[i - 1][1]) + matrix[i][0];
            for(int j = 1;j<matrix[0].size() - 1;j++){
                dp[i][j] = min({
                    dp[i - 1][j - 1],
                    dp[i - 1][j],
                    dp[i - 1][j + 1],
                }) + matrix[i][j]; 
            }
            dp[i][matrix[0].size() - 1] = 
            min(dp[i - 1][matrix[0].size() - 1],
                dp[i - 1][matrix[0].size() - 2]) + matrix[i][matrix[0].size() - 1];
        }
        int ans = INT32_MAX;
        for(int i: dp.back()) ans = min(ans,i);
        return ans;
    }
};