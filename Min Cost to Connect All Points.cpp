#include "queue"
#include "unordered_map"

using namespace std;
// front = weight , [1] = x, back = y;
typedef priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>> pq_g;

class Solution {
    // first = to, second wieght
    int n;
    unordered_map<int,vector<pair<int,int>>> graph;
    vector<bool> vis;
    int count = 0;
    int ans = 0;

    void solve(){
        // first = wieght, second = to
        pq_g p;
        for(auto a:graph){
            p.push({0,a.first});
            break;
        }
        while(p.size()){
            auto a = p.top();
            p.pop();
            if(vis[a.second]) continue;
            vis[a.second] = true;
            ans += a.first;
            count++;
            if(count == n) return;
            for(auto child: graph[a.second]){
                if(vis[child.second]) continue;
                p.push(child);
            }
        }
    }

public:
    int minCostConnectPoints(vector<vector<int>>& p) {
        n = p.size();
        count = 0;
        vis = vector<bool>(n);
        for(int i = 0;i<p.size();i++){
            for(int j = i + 1;j<p.size();j++){
                auto a = p[i];
                auto b = p[j];
                int w = abs(a.front() - b.front()) + abs(a.back() - b.back());
                graph[i].push_back({w,j});
                graph[j].push_back({w,i});
            }
        }
        solve();
        return ans;
    }
};