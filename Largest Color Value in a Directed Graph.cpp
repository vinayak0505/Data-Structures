#include "vector"
#include "string"

using namespace std;

class Solution {

    void fillmax(vector<int>&a, vector<int>&b){
        for(int i = 0;i<26;i++){
            a[i] = max(a[i],b[i]);
        }
    }

    vector<vector<int>> vis;
    vector<bool> in_path;
    vector<int> find_max(vector<vector<int>>& graph,string& col,int i){
        vector<int> ans(26);
        if(in_path[i]) return {-1};
        if(vis[i].size()) return vis[i];
        in_path[i] = true;
        for(int child: graph[i]){
            auto childans = find_max(graph,col,child);
            if(childans.size() == 1){
                in_path[i] = false;
                return vis[i] = childans;
            }
            fillmax(ans,childans);
        }
        in_path[i] = false;
        ans[col[i] - 'a']++;
        return vis[i] = ans;
    }

public:

    int largestPathValue(string colors, vector<vector<int>>& edges) {
        if(edges.size() == 0 && colors.size()) return 1;
        vis = vector<vector<int>> (colors.size());
        in_path = vector<bool> (colors.size());
        vector<int> ans(26);
        vector<vector<int>> graph(colors.size());
        for(int i = 0;i<edges.size();i++){
            graph[edges[i].front()].push_back(edges[i].back());
        }
        for(int i = 0;i<colors.size();i++){
            if(vis[i].size() || graph[i].size() == 0) continue;
            auto a = find_max(graph,colors,i);
            if(a.size() == 1) continue;
            fillmax(ans,a);
        }
        int a = 0;
        for(int i: ans) a = max(a,i);
        if(a) return a;
        return -1;
    }
};