#include "vector"

using namespace std;

class Solution {

    void solve(
        int edge,
        int tc,
        vector<int>& timeTaken,
        vector<vector<pair<int,int>>>& graph
    ){
        if(timeTaken[edge] <= tc) return;
        timeTaken[edge] = tc;
        for(auto [child,weight]:graph[edge]){
            solve(child,tc + weight,timeTaken,graph);
        }
    }

public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<vector<pair<int,int>>> graph(n);
        for(int i = 0;i<times.size();i++){
            graph[times[i].front() - 1].push_back({times[i][1] - 1,times[i].back()});
        }
        vector<int> timeTaken(n,INT32_MAX);
        solve(k - 1,0,timeTaken,graph);
        int ans = 0;
        for(int i : timeTaken) ans = max(ans,i);
        return ans == INT32_MAX ? -1 : ans;
    }
};