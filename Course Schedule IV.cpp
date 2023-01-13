#include "vector"
#include "unordered_set"

using namespace std;
class Solution {

    vector<vector<int>> graph;
    vector<unordered_set<int>> st;

    void find(int from,int edge){
        if(st[edge].count(from)) return;
        st[edge].insert(from);
        for(int child:graph[from])
            find(child,edge);
    }

public:
    vector<bool> checkIfPrerequisite(
        int n, 
        vector<vector<int>>& prerequisites, 
        vector<vector<int>>& queries) 
    {
        graph = vector<vector<int>>(n);
        st = vector<unordered_set<int>>(n);

        for(auto p:prerequisites){
            graph[p.front()].push_back(p.back());
        }

        for(int i = 0;i<n;i++){
            find(i,i);
        }
        vector<bool> ans(queries.size());
        for(int i = 0;i<queries.size();i++){
            ans[i] = st[queries[i].front()].count(queries[i].back());
        }

        return ans;
    }
};