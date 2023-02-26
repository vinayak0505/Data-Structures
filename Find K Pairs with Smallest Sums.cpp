#include "vector"

using namespace std;

class Solution {

    int count(int mid,vector<vector<int>>& matrix){
        int ans = 0;
        for(int i = 0,j = matrix.front().size();i<matrix.size() && j;i++){
            while(j && matrix[i][j - 1] > mid) j--;
            ans += j;
        }
        return ans;
    }
public:
    int kthSmallest(vector<vector<int>>& matrix, int k) {
        int low = matrix.front().front();
        int high = matrix.back().back();
        while(low <= high){
            int mid = (high + low) / 2;
            int pos = count(mid,matrix);
            if(pos < k){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return low;
    }
};