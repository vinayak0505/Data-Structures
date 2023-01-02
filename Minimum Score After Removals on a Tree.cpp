
#include "vector"

using namespace std;

class Solution {
    void solve(int& a,int b,int c,int d){
        a = min(a,max({b,c,d}) - min({b,c,d}));
    }
    // node parent xorchange xorper dp graph a
    void solve(int n,int par,const int c,const int per,
    const vector<int>& dp, const vector<vector<int>>& graph, int &a,
    int x,int y){
        if(n != par)
            solve(a,per,c ^ dp[n],dp[n]);
        for(int child:graph[n]){
            if(child == par) continue;
            if((x == n && child == y) || (y == n && child == x))
            continue;
            solve(child,n,c,per,dp,graph,a,x,y);
        }
    }
    int fill_dp(int node,vector<bool>& vis,
    const vector<vector<int>> &graph,
    vector<int> &dp,const vector<int> &nums,int a,int b){
        dp[node] = nums[node];
        for(int child:graph[node]){
            if(vis[child]) continue;
            if((a == node && child == b) || (b == node && child == a)) 
            continue;
            vis[child] = true;
            dp[node] ^= fill_dp(child,vis,graph,dp,nums,a,b);
        }
        return dp[node];
    }
    
    int minimumScore(const vector<int>& nums, 
    const vector<vector<int>>& graph,vector<int> mis) {
        vector<int> dp(nums.size());
        vector<bool> vis(nums.size());
        vector<int> ans;
        auto t = {0,mis.front(),mis.back()};
        for(int i : t){
            if(vis[i]) continue;
            vis[i] = true;
            fill_dp(i,vis,graph,dp,nums,mis.front(),mis.back());
            ans.push_back(i);
        }
        int a = INT32_MAX,b = INT32_MAX;
        solve(ans.front(), ans.front(),dp[ans.front()],dp[ans.back()],
        dp,graph,a,mis.front(),mis.back());
        solve(ans.back(), ans.back(),dp[ans.back()],dp[ans.front()],
        dp,graph,b,mis.front(),mis.back());
        return min(a,b);
    }

public:
    int minimumScore(vector<int>& nums, vector<vector<int>>& edges) {
        int ans = INT32_MAX;
        vector<vector<int>> graph(nums.size());
        for(auto edge:edges){
            graph[edge.front()].push_back(edge.back());
            graph[edge.back()].push_back(edge.front());
        }
        for(auto &edge: edges){
            ans = min(ans,minimumScore(nums,graph,edge));
        }
        return ans;
    }
};