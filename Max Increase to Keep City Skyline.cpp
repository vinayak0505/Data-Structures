#include "vector"
#include "unordered_map"

using namespace std;

class Solution {
public:
    int findMax(vector<int>arr){
        int maxi=arr[0];
        for(int i=1;i<arr.size();i++){
            if(arr[i]>maxi){
                maxi=arr[i];
            }
        }
        return maxi;
    }
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid) {
        unordered_map<int,int>m;
        int n=grid.size();

        for(int i=0;i<n;i++){
            int temp=findMax(grid[i]);
            m[i]=temp;
        }
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                swap(grid[i][j],grid[j][i]);
            }
        }

        for(int i=0;i<n;i++){
            int temp=findMax(grid[i]);
            m[i+n]=temp;
        }
        
        int res=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                res+=(min(m[i],m[j+n])-grid[i][j]);
            }
        }
        return res;

        
    }
};