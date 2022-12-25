#include "vector"

using namespace std;

class Solution {
    vector<bool> res;
    int ans = 0;
    void solve(int node,const vector<vector<int>> &graph){
        if(res[node]) return;
        res[node] = true;
        ans++;
        for(auto child:graph[node]){
            solve(child,graph);
        }
    }
public:
    int reachableNodes(int n, vector<vector<int>>& edges, vector<int>& restricted) {
        res = vector<bool>(n);
        for(auto i:restricted) res[i] = true;
        vector<vector<int>> graph(n);
        for(auto &edge:edges){
            graph[edge.front()].push_back(edge.back());
            graph[edge.back()].push_back(edge.front());
        }
        solve(0,graph);
        return ans;
    }
};