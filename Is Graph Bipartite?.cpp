#include "vector"

using namespace std;

class Solution {

    bool valid(int edge,int par,bool val,vector<int>& vis, vector<vector<int>>& graph){
        if(vis[edge] != 2){
            return vis[edge] == val;
        }
        vis[edge] = val;
        for(int child: graph[edge]){
            if(valid(child,edge,!val,vis,graph) == false) return false;
        }
        return true;
    }

public:
    bool isBipartite(vector<vector<int>>& graph) {
        vector<int> vis(graph.size(),2);
        for(int i = 0;i<vis.size();i++){
            if(vis[i] != 2) continue;
            if(valid(i,-1,0,vis,graph) == false) return false;
        }
        return true;
    }
};