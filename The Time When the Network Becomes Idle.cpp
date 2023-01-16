#include "queue"

using namespace std;

class Solution
{

    void solve(int edge, vector<vector<int>> &graph, vector<int> &time, int t)
    {
        queue<int> q;
        q.push(edge);
        time[0] = 0;
        while (q.size())
        {
            edge = q.front();
            q.pop();
            for (int child : graph[edge]){
                if(time[child] != -1) continue;
                q.push(child), time[child] = time[edge] + 1;
            }
        }
    }

public:
    int networkBecomesIdle(vector<vector<int>> &edges, vector<int> &patience)
    {
        vector<int> time(patience.size(), -1);
        vector<vector<int>> graph(patience.size());
        for (auto edge : edges)
        {
            graph[edge.front()].push_back(edge.back());
            graph[edge.back()].push_back(edge.front());
        }
        solve(0, graph, time, 0);

        int ans = 0;
        for (int i = 1; i < patience.size(); i++)
        {
            int val = time[i] * 2;
            val += ((time[i]*2 - 1)/patience[i]) * patience[i];
            ans = max(ans, val);
        }
        return ans + 1;
    }
};