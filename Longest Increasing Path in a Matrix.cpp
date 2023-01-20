#include "vector"

using namespace std;

class Solution {

    int arr[4][2] = {
        {-1,0},
        {1,0},
        {0,-1},
        {0,1}
    };
    vector<vector<int>> dp;
    int solve(int val,int x,int y,vector<vector<int>>&matrix){
        if(x == matrix.size() || y == matrix.front().size())
            return 0;
        if(x == -1 || y == -1)
            return 0;
        if(val >= matrix[x][y]) return 0;
        if(dp[x][y] != -1) return dp[x][y];
        int ans = 0;
        for(auto a:arr){
            ans = max(ans,solve(matrix[x][y],x + a[0],y + a[1],matrix));
        }
        return dp[x][y] = ans + 1;
    }
public:
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        dp = vector<vector<int>>(200,vector<int>(200,-1));
        int ans = 0;
        for(int i = 0;i<matrix.size();i++){
            for(int j = 0;j<matrix.front().size();j++){
                ans = max(ans,solve(-1,i,j,matrix));
            }
        }
        return ans;
    }
};