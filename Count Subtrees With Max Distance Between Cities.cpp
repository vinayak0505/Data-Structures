#include "vector"
using namespace std;

class Solution {
    // first = max length, second max ending at that elemet
    pair<int,int> solve(int edge,int parent,
    vector<vector<int>>& graph,vector<bool> &vis,int &count){
        if(vis[edge]) return {0,0};
        vis[edge] = true;
        count--;
        pair<int,int> ans{0,0};
        int m1 = 0, m2 = 0;
        for(int child:graph[edge]){
            if(child == parent) continue;
            auto val = solve(child,edge,graph,vis,count);
            ans.first = max(ans.first,val.first);
            if(val.second > m1){
                m2 = m1, m1 = val.second;
            }else if(val.second > m2) m2 = val.second;
        }
        ans.second = m1 + 1;
        ans.first = max(ans.first, m1 + m2 + 1);
        vis[edge] = false;
        return ans;
    }

    vector<int> ans;

    void solve(int i,vector<vector<int>>&graph,vector<bool>&vis,
    int count,int start){
        if(i == vis.size()) return;
        solve(i + 1,graph,vis,count,start);
        vis[i] = false;
        if(count > 0){
            int acount = count + 1;
            int v = solve(i,-1,graph,vis,acount).first - 2;
            if(acount == 0) ans[v]++;
        }
        solve(i + 1,graph,vis,count + 1,i);
        vis[i] = true;
    }
public:
    vector<int> countSubgraphsForEachDiameter(int n, vector<vector<int>>& edges) {
        vector<vector<int>> graph(n);
        for(auto &edge: edges){
            graph[edge.front() - 1].push_back(edge.back() - 1);
            graph[edge.back() - 1].push_back(edge.front() - 1);
        }
        ans = vector<int>(n - 1);
        vector<bool> vis(n,true);
        solve(0,graph,vis,0,-1);
        return ans;
    }
};