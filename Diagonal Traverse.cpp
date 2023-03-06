#include "vector"

using namespace std;

class Solution {
public:
    vector<int> findDiagonalOrder(vector<vector<int>>& mat) {
        vector<int> ans;
        int i = 0,j = 0;
        while(true){
            ans.push_back(mat[i][j]);
            if(i == mat.size() - 1 && j == mat.front().size() - 1) break;
            if((i + j) % 2 == 0){
                if(j == mat.front().size() - 1) i++;
                else if(i == 0) j++;
                else i--,j++;
            }else{
                if(i == mat.size() - 1) j++;
                else if(j == 0) i++;
                else i++,j--;
            }
        }
        return ans;
    }
};