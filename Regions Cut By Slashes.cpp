#include "vector"
#include "string"

using namespace std;

class Union{

    vector<int> arr;
    int ans = 1;
    int n;
    public:
    Union(int n):n(n){
        arr = vector<int>(n * n,-1);
        for(int i = 0;i<n;i++){
            arr[i] = 0;
        }
        for(int i = arr.size() - 1;i >= arr.size() - n;i--){
            arr[i] = 0;
        }
        for(int i = 0;i<arr.size();i+= n){
            arr[i] = 0;
        }
        for(int i = n - 1;i<arr.size();i+= n){
            arr[i] = 0;
        }
    }

    int convert(int x,int y){
        return y * n + x;
    }

    int parent(int a){
        if(arr[a] == -1 || arr[a] == a) return a;
        return arr[a] = parent(arr[a]);
    }
    void join(int x1,int y1,int x2,int y2){
        int a = parent(convert(x1,y1));
        int b = parent(convert(x2,y2));
        if(a == b) {
            ans++;
        }
        else arr[a] = b;
    }

    int getNum(){
        return ans;
    }
};

class Solution {
public:
    int regionsBySlashes(vector<string>& grid) {
        int n = grid.size() + 1;
        Union un(n);
        for(int i = 0;i<grid.size();i++){
            for(int j = 0;j<grid.size();j++){
                if(grid[i][j] == '/') un.join(i,j+1,i+1,j);
                else if(grid[i][j] == '\\') un.join(i,j,i+1,j+1);
            }
        }
        return un.getNum();
    }
};