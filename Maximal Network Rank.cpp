#include "vector"
#include "unordered_set"

using namespace std;

class Solution {

public:
    int maximalNetworkRank(int n, vector<vector<int>>& roads) {
        vector<int> count(n);
        vector<unordered_set<int>> graph(n);
        for(auto road: roads){
            graph[road.front()].insert(road.back());
            graph[road.back()].insert(road.front());
        }
        int ans = 0;
        for(int i = 0;i<n;i++){
            for(int j = i + 1;j<n;j++){
                int val = graph[i].size() + graph[j].size();
                if(graph[i].count(j)) val--;
                ans = max(val,ans);
            }
        }
        return ans;
    }
};