#include "vector"

using namespace std;
class Solution {

    int ans = INT_MIN;
    vector<bool> vis;
    int solve(int edge,int par,int val,vector<vector<int>>& graph,vector<int>& vals,int k){
        if(vis[edge]) return vals[edge];
        vis[edge] = true;
        vector<int> sum;
        sum.push_back(val);
        for(auto child:graph[edge]){
            if(child == par) continue;
            sum.push_back(solve(child,edge,vals[edge],graph,vals,k));
        }
        sort(sum.begin(),sum.end(),greater<int>());
        int t = vals[edge];
        for(int i = 0;i<sum.size() && i < k && sum[i] > 0;i++){
            t += sum[i];
        }
        ans = max(t,ans);
        return vals[edge];
    }
public:
    int maxStarSum(vector<int>& vals, vector<vector<int>>& edges, int k) {
        vector<vector<int>> graph(vals.size());
        vis = vector<bool>(vals.size());
        for(auto edge:edges){
            graph[edge.front()].push_back(edge.back());
            graph[edge.back()].push_back(edge.front());
        }
        for(int i = 0;i<vals.size();i++){
            solve(i,-1,0,graph,vals,k);
        }
        return ans;
    }
};