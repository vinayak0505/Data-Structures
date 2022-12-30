#include "vector"

using namespace std;

class Solution {
    vector<vector<vector<int>>> paths;

    vector<vector<int>> solve(int edge,const vector<vector<int>> &graph){
        if(paths[edge].size()) return paths[edge];
        for(int child:graph[edge]){
            auto ans = solve(child,graph);
            for(auto& a:ans){
                a.push_back(edge);
                paths[edge].push_back(a);
            }
        }
        return paths[edge];
    }
public:
    vector<vector<int>> allPathsSourceTarget(vector<vector<int>>& graph) {
        paths = vector<vector<vector<int>>>(graph.size());
        paths.back().push_back({(int)graph.size() - 1});
        solve(0,graph);
        for(auto &p:paths.front()){
            reverse(p.begin(),p.end());
        }
        return paths.front();
    }
};