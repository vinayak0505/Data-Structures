#include "vector"
using namespace std;
class Solution {

    bool hasCircle(int node,int par,const vector<int>& edge,vector<bool>& vis,
    vector<vector<int>>&graph){
        if(vis[node]) return true;
        vis[node] = true;
        for(int child:graph[node]){
            if(child == edge.front() - 1 && node == edge.back() - 1) continue;
            if(child == edge.back() - 1 && node == edge.front() - 1) continue;
            if(child == par) continue;
            if(hasCircle(child,node,edge,vis,graph)) return true;
        }
        return false;
    }

    bool allVis(vector<bool>&a){
        for(int a:a) if(a == false) return false;
        return true;
    }
public:
    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
        vector<vector<int>> graph(edges.size());
        for(auto &edge:edges){
            graph[edge.front() - 1].push_back(edge.back() - 1);
            graph[edge.back() - 1].push_back(edge.front() - 1);
        }
        for(int i = edges.size() - 1; i>=0;i--){
            vector<bool> vis(edges.size());
            if(hasCircle(0,-1,edges[i],vis,graph) == false 
            && allVis(vis)) return edges[i];
        }
        return {};
    }
};