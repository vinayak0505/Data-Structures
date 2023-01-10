#include "vector"

using namespace std;

class Solution {
    // 2 = not visited, 1 is not safe , 0 is safe
    bool isSafe(int edge,vector<int>& safe,vector<vector<int>>& graph){
        if(safe[edge] != 2) return safe[edge] == 0;
        safe[edge] = 1;
        for(int child: graph[edge]){
            if(isSafe(child,safe,graph) == false) return false;
        }
        safe[edge] = 0;
        return true;
    }
public:
    vector<int> eventualSafeNodes(vector<vector<int>>& graph) {
        vector<int> safe(graph.size(),2);
        for(int i = 0;i<graph.size();i++){
            if(safe[i] != 2) continue;
            isSafe(i,safe,graph);
        }
        vector<int> ans;
        for(int i = 0;i < graph.size();i++){
            if(safe[i] == 0) ans.push_back(i);
        }
        return ans;
    }
};