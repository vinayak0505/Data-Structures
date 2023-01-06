#include "unordered_set"
#include "vector"

using namespace std;

class Solution {
    unordered_set<int> us;

    void solve(
        int edge,
        const vector<vector<int>>& graph,
        vector<bool>& vis
    ){
        if(vis[edge]){
            if(us.count(edge)){
                us.erase(edge);
            }
            return;
        }
        vis[edge] = true;
        for(int child:graph[edge]) solve(child,graph,vis);
    }

public:
    vector<int> findSmallestSetOfVertices(int n, vector<vector<int>>& edges) {
        vector<vector<int>> graph(n);
        vector<bool> vis(n);

        for(auto &edge: edges){
            graph[edge.front()].push_back(edge.back());
        }

        for(int edge = 0; edge < n; edge++){
            if(vis[edge]) continue;
            us.insert(edge);
            solve(edge,graph,vis);
        }

        vector<int> ans;
        for(int i: us) ans.push_back(i);
        return ans;
    }
};