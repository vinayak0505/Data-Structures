#include "vector"

using namespace std;

class NumMatrix {
    vector<vector<int>> m;
public:
    NumMatrix(vector<vector<int>>& matrix) {
        m = matrix;
        for(int i = 1;i<matrix.size();i++){
            m[i][0] += m[i - 1][0];
        }
        for(int i = 1;i<matrix.front().size();i++){
            m[0][i] += m[0][i - 1];
        }
        for(int i = 1;i<matrix.size();i++){
            for(int j = 1;j<matrix.front().size();j++){
                m[i][j] += m[i - 1][j] + m[i][j - 1] - m[i - 1][j - 1];
            }
        }
    }
    
    int sumRegion(int row1, int col1, int row2, int col2) {
        int ans = m[row2][col2];
        if(row1){
            ans -= m[row1 - 1][col2];
        }
        if(col1){
            ans -= m[row2][col1 - 1];
        }
        if(col1 && row1){
            ans += m[row1 - 1][col1 - 1];
        }
        return ans;
    }
};

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix* obj = new NumMatrix(matrix);
 * int param_1 = obj->sumRegion(row1,col1,row2,col2);
 */