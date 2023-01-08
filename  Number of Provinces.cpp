#include "vector"

using namespace std;
class Solution {
    vector<bool> vis;

    void solve(int edge,const vector<vector<int>>& c){
        if(vis[edge]) return;
        vis[edge] = true;
        for(int i = 0;i<c.size();i++){
            if(i == edge || c[edge][i] == 0) continue;
            solve(i,c);
        }
    }
public:
    int findCircleNum(vector<vector<int>>& isConnected) {
        int ans = 0;
        vis = vector<bool>(isConnected.size());
        for(int i = 0;i<vis.size();i++){
            if(vis[i]) continue;
            solve(i,isConnected);
            ans++;
        }
        return ans;
    }
};