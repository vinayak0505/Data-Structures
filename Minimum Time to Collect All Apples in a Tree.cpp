#include "vector";
using namespace std;

class Solution {
    int ans = 0;
    bool solve(int p,int t,const vector<vector<int>>& graph,
    vector<bool>& hasApple,int pp){
        int found = 0;
        
        for(int i:graph[p]){
            if(i == pp) continue;
            found += solve(i,t+1,graph,hasApple,p);
        }
        if(hasApple[p] && found == 0){
            ans += t;
            found++;
        }
        if(found)
            ans -= (found - 1) * t;
        return found;
    }
public:
    int minTime(int n, vector<vector<int>>& edges, vector<bool>& hasApple) {
        vector<vector<int>> graph(n);
        for(auto &edge:edges){
            graph[edge.front()].push_back(edge.back());
            graph[edge.back()].push_back(edge.front());
        }
        solve(0,0,graph,hasApple,-1);
        return ans * 2;
    }
};