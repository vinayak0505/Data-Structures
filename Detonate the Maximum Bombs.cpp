#include "queue"

using namespace std;
class Solution
{

    long long dis(vector<int> &v1, vector<int> &v2)
    {
        long long a = abs(v1.front() - v2.front());
        long long b = abs(v1[1] - v2[1]);
        return a * a + b * b;
    }

    int solve(int edge, vector<vector<int>> &graph)
    {
        int ans = 0;
        queue<int> q;
        q.push(edge);
        vector<bool> vis(graph.size());
        while (q.size())
        {
            int edge = q.front();
            q.pop();
            if (vis[edge])
                continue;
            vis[edge] = true;
            ans++;
            for (int child : graph[edge]){
                if(vis[child]) continue;
                q.push(child);
            }
        }
        return ans;
    }

public:
    int maximumDetonation(vector<vector<int>> &bombs)
    {
        vector<vector<int>> graph(bombs.size());
        long long d;
        for (int i = 0; i < bombs.size(); i++)
        {
            for (int j = i + 1; j < bombs.size(); j++)
            {
                d = dis(bombs[i], bombs[j]);
                if ((long long)bombs[i].back() * bombs[i].back() >= d){
                    graph[i].push_back(j);
                }
                if ((long long)bombs[j].back() * bombs[j].back() >= d){
                    graph[j].push_back(i);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < bombs.size(); i++)
            ans = max(ans, solve(i, graph));
        return ans;
    }
};