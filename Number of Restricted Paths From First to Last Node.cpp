#include "queue"

using namespace std;

class Solution {
    int mod = 1e9 + 7;
    vector<int> dp;
    vector<vector<pair<int,int>>> graph;
    vector<int>dis;
    int solve(int edge){
        if(dp[edge] != -1) return dp[edge];
        if(edge == dp.size() - 1){
            return 1;
        }
        int ans = 0;
        for(auto child:graph[edge]){
            if(dis[edge] > dis[child.first]) {
                if(dp[child.first] != -1) ans += dp[child.first];
                else ans += solve(child.first);
                if(ans >= mod) ans -= mod;
            }
        }
        return dp[edge] = ans;
    }
public:
    int countRestrictedPaths(int n, vector<vector<int>>& edges) {
        dp = vector<int>(n,-1);
        graph = vector<vector<pair<int,int>>>(n);
        priority_queue<
        pair<int,int>,
        vector<pair<int,int>>,
        greater<pair<int,int>>> q;

        for(auto edge: edges){
            graph[edge.front() - 1].push_back({edge[1] - 1,edge[2]});
            graph[edge[1] - 1].push_back({edge.front() - 1,edge[2]});
        }
        dis = vector<int> (n,INT_MAX);
        dis.back() = 0;
        q.push({0,n - 1});
        int u, v,w;
        while (q.size()) {
            auto p = q.top(); q.pop();
            u = p.second;
            for (auto& to : graph[u]) {
                v = to.first, w = to.second;
                if (dis[v] > dis[u] + w) {
                    dis[v] = dis[u] + w;
                    q.push({dis[v], v});
                }
            }
        }
        return solve(0);
    }
};
