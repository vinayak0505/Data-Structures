
#include "vector"

using namespace std;
class Solution {

    double solve(int edge,int parent,int t,
    vector<vector<int>>& graph,int target){
        if(t == -1) return 0;
        if(edge == target && (t == 0 || 
        (
            (parent == -1 && graph[edge].size() == 0) || 
            (parent != -1 && graph[edge].size() == 1)
        ))) return 1;
        double ans = 0;
        int count = 0;
        for(int child:graph[edge]){
            if(child == parent) continue;
            ans += solve(child,edge,t-1,graph,target);
            count++;
        }
        if(count == 0) return 0;
        return ans / count;
    }
public:
    double frogPosition(int n, vector<vector<int>>& edges, int t, int target) {
        vector<vector<int>> graph(n);
        for(auto &edge:edges){
            graph[edge.front() - 1].push_back(edge.back() - 1);
            graph[edge.back() - 1].push_back(edge.front() - 1);
        }
        return solve(0,-1,t,graph,target - 1);
    }
};