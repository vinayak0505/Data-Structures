#include "queue"

using namespace std;
typedef priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq_q;

class Solution
{
    int mod = 1e9 + 7;
    
public:
    int countPaths(int n, vector<vector<int>> &roads)
    {
        // first = weight , second = child
        vector<vector<pair<int, int>>> graph(n), msgraph(n);
        for (auto road : roads)
        {
            graph[road[0]].push_back({road[2], road[1]});
            graph[road[1]].push_back({road[2], road[0]});
        }

        vector<long long> dis(n, LLONG_MAX);
        vector<int> count(n);
        count.front() = 1;
        dis[0] = 0;

        pq_q q;
        q.push({0, 0});

        while (q.size())
        {
            auto top = q.top();
            q.pop();
            if(dis[top.second] < top.first) continue;
            for (auto child : graph[top.second])
            {
                if (dis[child.second] <= dis[top.second] + child.first)
                {
                    if (dis[child.second] == dis[top.second] + child.first){
                        count[child.second] += count[top.second];
                        count[child.second] %= mod;
                    }
                    continue;
                }
                dis[child.second] = dis[top.second] + child.first;
                count[child.second] = count[top.second];
                q.push({dis[child.second],child.second});
            }
        }
        return count.back();
    }
};