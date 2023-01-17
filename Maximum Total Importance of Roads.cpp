#include "vector"

using namespace std;

class Solution {
public:
    long long maximumImportance(int n, vector<vector<int>>& roads) {
        vector<vector<int>> graph(n);
        for(auto road: roads){
            graph[road.front()].push_back(road.back());
            graph[road.back()].push_back(road.front());
        }
        sort(graph.begin(),graph.end(),[](vector<int> &a,vector<int> &b){
            return a.size() > b.size();
        });
        long long ans = 0;
        for(int i = 0;i<n;i++){
            ans += (n - i) * graph[i].size();
        }
        return ans;
    }
};