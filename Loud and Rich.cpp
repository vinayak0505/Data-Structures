#include "vector"

using namespace std;
class Solution {
    vector<int> ans;
    int solve(int edge,vector<vector<int>>& graph, vector<int>& quiet){
        if(ans[edge] != INT_MAX) return ans[edge];
        ans[edge] = edge;
        for(int richer:graph[edge]){
            int a = solve(richer,graph,quiet);
            if(quiet[a] < quiet[ans[edge]]) ans[edge] = a;
        }
        return ans[edge];
    }
public:
    vector<int> loudAndRich(vector<vector<int>>& richer, vector<int>& quiet) {
        ans = vector<int>(quiet.size(),INT_MAX);
        vector<vector<int>> graph(quiet.size());
        for(auto rich: richer){
            graph[rich.back()].push_back(rich.front());
        }
        for(int i = 0;i<quiet.size();i++){
            solve(i,graph,quiet);
        }
        return ans;
    }
};