#include "vector"

using namespace std;
class Solution {

    int solve(vector<vector<int>>& row,int a){
        if(row[a].front() == a) return 0;
        int par = -1;
        int count = 0;
        int b;
        while(row[a].front() != a){
            if(row[a].front() == par){
                b = row[a].back();
            }else {
                b = row[a].front();
            }
            row[a] = {a,a};
            count++;
            par = a;
            a = b;
        }
        return count - 1;
    }
public:
    int minSwapsCouples(vector<int>& row) {
        vector<vector<int>> arr(row.size()/2);
        for(int i = 0;i<row.size();i+=2){
            arr[row[i]/2].push_back(row[i + 1]/2);
            arr[row[i + 1]/2].push_back(row[i]/2);
        }
        int ans = 0;
        for(int i = 0;i<arr.size();i++){
            ans += solve(arr,i);
        }
        return ans;
    }
};