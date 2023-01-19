#include "vector"

using namespace std;
class Solution {
    int ans = INT_MAX;
    void solve(int edge,vector<bool> &vis,vector<vector<pair<int,int>>> &graph){
        if(vis[edge]) return;
        vis[edge] = true;
        for(auto child:graph[edge]){
            ans = min(ans,child.second);
            solve(child.first,vis,graph);
        }
    }
public:
    int minScore(int n, vector<vector<int>>& roads) {
        vector<vector<pair<int,int>>> graph(n);
        for(int i = 0;i<roads.size();i++){
            graph[roads[i][0] - 1].push_back({roads[i][1] - 1,roads[i][2]});
            graph[roads[i][1] - 1].push_back({roads[i][0] - 1,roads[i][2]});
        }
        vector<bool> vis(n);
        solve(0,vis,graph);
        return ans;
    }
};