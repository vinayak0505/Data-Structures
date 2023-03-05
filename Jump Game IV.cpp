#include "queue"
#include "unordered_map"

using namespace std;

class Solution
{
    int solve(unordered_map<int, vector<int>> &graph, int n, vector<int> &num)
    {
        // val, cur, par
        vector<bool> vis(num.size());
        queue<int> pq;
        int val = 0;
        pq.push(0);
        vis.front() = true;
        while (pq.size())
        {
            int size = pq.size();
            while (size--)
            {
                auto v = pq.front();
                pq.pop();
                if (v == n)
                    return val;
                for (int next : graph[num[v]])
                {
                    if (vis[next])
                        continue;
                    pq.push(next);
                    vis[next] = true;
                }
                graph[num[v]].clear();

                if (v != 0 && !vis[v - 1])
                    pq.push(v - 1), vis[v - 1] = true;
                if (!vis[v + 1])
                    pq.push(v + 1), vis[v + 1] = true;
            }
            val++;
        }
        return -1;
    }


public:
    int minJumps(vector<int> &arr)
    {
        if (arr.size() == 1)
            return 0;
        unordered_map<int, vector<int>> graph;

        for (int i = 0; i < arr.size(); i++)
        {
            graph[arr[i]].push_back(i);
        }

        return solve(graph, arr.size() - 1, arr);
    }
};