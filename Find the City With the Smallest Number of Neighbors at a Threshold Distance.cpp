#include "queue"

using namespace std;

typedef priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>> pq_g;

class Solution {

    int solve(int edge,vector<vector<pair<int,int>>>&graph,int left){
        int count = 0;
        // first = to, second = weight
        vector<bool> vis(graph.size());
        pq_g q;
        q.push({0,edge});
        while(q.size()){
            auto top = q.top();
            q.pop();
            if(vis[top.second]) continue;
            if(top.first > left) continue;
            vis[top.second] = true;
            count++;
            for(auto child:graph[top.second]){
                if(vis[child.first]) continue;
                q.push({top.first + child.second,child.first});
            }
        }
        return count;
    }
public:
    int findTheCity(int n, vector<vector<int>>& edges, int distanceThreshold) {
        vector<vector<pair<int,int>>>graph(n);
        for(auto edge: edges){
            graph[edge.front()].push_back({edge[1],edge.back()});
            graph[edge[1]].push_back({edge.front(),edge.back()});
        }
        int ans = 0,count = INT_MAX;
        for(int i = 0;i < n; i++){
            int val = solve(i,graph,distanceThreshold);
            if(val <= count){
                count = val; ans = i;
            }
        }
        return ans;
    }
};