#include "vector"

using namespace std;

class Solution {
    vector<int> ans;
    vector<int> size;
    pair<int,int> solve(int head,const vector<vector<int>> &graph,int p){
        for(auto node:graph[head]) {
            if(node == p) continue;
            auto c = solve(node,graph,head);
            ans[head] += c.first;
            size[head] += c.second;
        }
        return {ans[head] + size[head],size[head]};
    }
    void solve2(int head,const vector<vector<int>>& graph,int val,int p){
        ans[head] += val;
        for(auto node:graph[head]){
            if(node == p) continue;
            int child_sum = ans[node];
            solve2(node,graph,ans[head] - child_sum - 
            (size[node]) * 2 + ans.size(),head);
        }
    }
public:
    vector<int> sumOfDistancesInTree(int n, vector<vector<int>>& edges) {
        vector<vector<int>> graph(n);
        ans = vector<int>(n);
        size = vector<int>(n,1);
        for(auto &edge: edges){
            graph[edge.front()].push_back(edge.back());
            graph[edge.back()].push_back(edge.front());
        }
        solve(0,graph,-1);
        solve2(0,graph,0,-1);
        return ans;
    }
};