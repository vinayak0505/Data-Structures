#include "vector"

using namespace std;

class Solution {
    int ans = -1;
    vector<int> vis;
    vector<int> vis2;
    void solve(vector<int>& edges, int i,int c){
        if(vis2[i] || edges[i] == -1) return;
        if(vis[i] != -1){
            ans = max(ans,c - vis[i]);
            return;
        }
        vis[i] = c;
        solve(edges,edges[i],c+1);
        vis[i] = -1;
        vis2[i] = true;
    }

public:
    int longestCycle(vector<int>& edges) {
        vis = vector<int>(edges.size(),-1);
        vis2 = vector<int>(edges.size());
        for(int i = 0;i<edges.size();i++)
            solve(edges,i,0);
        return ans;
    }
};