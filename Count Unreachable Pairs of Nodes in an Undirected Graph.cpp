#include "vector"

using namespace std;

class Solution {

    vector<bool> vis;
    long long count = 0;

    void solve(int edge,vector<vector<int>> &graph){
        if(vis[edge]) return;
        vis[edge] = true;
        count++;
        for(int child:graph[edge]){
            solve(child,graph);
        }
    }
public:
    long long countPairs(int n, vector<vector<int>>& edges) {
        vis = vector<bool>(n);
        vector<vector<int>> graph(n);
        for(auto edge: edges){
            graph[edge.front()].push_back(edge.back());
            graph[edge.back()].push_back(edge.front());
        }
        long long ans = 0;
        for(int i = 0;i<n;i++){
            if(vis[i]) continue;
            count = 0;
            solve(i,graph);
            ans += count * (n - count);
        }
        return ans / 2;
    }
};