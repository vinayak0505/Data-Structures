#include "iostream"
#include "vector"

using namespace std;

class Solution {
    int change(int t){
        if(t == 1) return 2;
        return 1;
    }
    // not assign = 0, a team = 1, b team = 2
    bool assign(vector<vector<int>> &graph,int head,int t,vector<int> &vis){
        if(vis[head]){
            return (vis[head] == t);
        }
        vis[head] = t;
        t = change(t);
        for(int i = 0;i<graph[head].size();i++){
            if(assign(graph,graph[head][i],t,vis) == false) return false;
        }
        return true;
    }
public:
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        vector<vector<int>> graph(n + 1);
        vector<int> vis(n + 1);
        for(auto &dis:dislikes){
            graph[dis.front()].push_back(dis.back());
            graph[dis.back()].push_back(dis.front());
        }
        for(int i = 1;i<=n;i++){
            if(vis[i]) continue;
            if(assign(graph,i,1,vis) == false) return false;
        }
        return true;
    }
};