#include "vector"

using namespace std;
class Solution {
    vector<bool> vis;
    vector<vector<int>> ans;

    vector<int> merge(vector<int>& a,vector<int> b){
        vector<int> ans;
        int i = 0, j =0;
        while(i < a.size() && j < b.size()){
            if(a[i]<b[j]){
                ans.push_back(a[i++]);
            }else if(a[i] > b[j]){
                ans.push_back(b[j++]);
            }else i++;
        }
        while(i < a.size()){
            ans.push_back(a[i++]);
        }
        while(j < b.size()){
            ans.push_back(b[j++]);
        }
        return ans;
    }
    vector<int> solve(int edge,vector<vector<int>>& pars){
        if(vis[edge]) return merge(ans[edge],{edge});
        vis[edge] = true;
        vector<int> v;
        for(int par:pars[edge]){
            v = merge(v,solve(par,pars));
        }
        ans[edge] = v;
        return merge(v,{edge});
    }

public:
    vector<vector<int>> getAncestors(int n, vector<vector<int>>& edges) {
        vector<vector<int>> parents(n);
        vis = vector<bool>(n);
        ans = vector<vector<int>>(n);
        for(auto edge:edges){
            parents[edge.back()].push_back(edge.front());
        }
        for(int i = 0;i<n;i++){
            if(vis[i]) continue;
            solve(i,parents);
        }
        return ans;
    }
};