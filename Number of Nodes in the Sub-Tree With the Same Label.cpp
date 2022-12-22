#include "vector"
#include "string"

using namespace std;
class Solution {
    vector<int> finalans;
    void combine(vector<int>& a,vector<int> b){
        for(int i = 0;i<26;i++) a[i] += b[i];
    }
    vector<int> countSubTrees(vector<vector<int>>& graph, string& labels,int edge,int p) {
        vector<int> ans(26);
        ans[labels[edge] - 'a']++;
        for(int node: graph[edge]){
            if(node == p) continue;
            combine(ans,countSubTrees(graph,labels,node,edge));
        }
        finalans[edge] = ans[labels[edge] - 'a'];
        return ans;
    }
public:
    vector<int> countSubTrees(int n, vector<vector<int>>& edges, string labels) {
        vector<vector<int>> graph(n);
        finalans = vector<int>(n);
        for(auto &edge:edges) {
            graph[edge.front()].push_back(edge.back());
            graph[edge.back()].push_back(edge.front());
        }
        countSubTrees(graph,labels,0,-1);
        return finalans;
    }
};