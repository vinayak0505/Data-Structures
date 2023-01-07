#include "vector"

using namespace std;

class Solution {
    vector<bool> gvis;
    vector<int> ans;
    bool solve(
        int edge,
        const vector<vector<int>>&graph,
        vector<bool> &vis){
            if(vis[edge]){
                return gvis[edge] == false;
            }
            vis[edge] = true;
            gvis[edge] = true;
            for(int child: graph[edge]){
                if(solve(child,graph,vis) == false) return false;
            }
            gvis[edge] = false;
            ans.push_back(edge);
            return true;
        }
public:
    vector<int> findOrder(int n, vector<vector<int>>& p) {
        vector<vector<int>> graph(n);
        for(auto &p:p){
            graph[p.front()].push_back(p.back());
        }
        vector<bool> vis(n);
        gvis = vector<bool>(n);
        for(int i = 0;i<n;i++){
            if(vis[i]) continue;
            if(solve(i,graph,vis) == false) return {};
        }
        return ans;
    }
};