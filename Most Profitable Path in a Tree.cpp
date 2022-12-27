#include "vector"

using namespace std;

class Solution {
    vector<int> getPath(int edge,int par, const vector<vector<int>>& graph,int bob){
        if(edge == bob){
            return {bob};
        }
        for(int child:graph[edge]){
            if(child == par) continue;
            auto a = getPath(child,edge,graph,bob);
            if(a.size() != 0) {
                a.push_back(edge);
                return a;
            }
        }
        return {};
    }

    int findMax(int edge,int par, 
    const vector<vector<int>>& graph,const vector<int> &amount){
        int ans = INT32_MIN;
        for(int child:graph[edge]){
            if(child == par) continue;
            ans = max(findMax(child,edge,graph,amount),ans);
        }
        if(ans == INT32_MIN) ans = 0;
        return ans + amount[edge];
    }

public:
    int mostProfitablePath(vector<vector<int>>& edges, int bob, vector<int>& amount) {
        vector<vector<int>> graph(amount.size());
        for(auto &edge:edges){
            graph[edge.front()].push_back(edge.back());
            graph[edge.back()].push_back(edge.front());
        }
        auto path = getPath(0,-1,graph,bob);
        for(int i = 0;i<path.size()/2;i++){
            amount[path[i]] = 0;
        }
        if(path.size()%2 == 1){
            amount[path[path.size()/2]] /= 2;
        }
        return findMax(0,-1,graph,amount);
    }
};