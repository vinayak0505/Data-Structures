#include <iostream>
#include <queue>

using namespace std;
typedef long long ll;

vector<int> min_dfs(int node, vector<vector<pair<int, int>>> const &graph)
{
    vector<int> ans(graph.size(), INT32_MAX / 2);
    vector<bool> vis(graph.size(),false);
    // first = weight , second = node
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
    pq.push({0, node});
    while (pq.size())
    {
        auto top = pq.top();
        pq.pop();
        if (top.first >= ans[top.second] || vis[top.second])
            continue;
        ans[top.second] = top.first;
        vis[top.second] = true;
        for (auto child : graph[top.second])
        {
            if (ans[child.first] > child.second)
                pq.push({child.second, child.first});
            // else
            //     cout << ans[child.first] << "-" << child.second << endl;
        }
        // cout << node << endl;
    }
    return ans;
}

void print(vector<int> arr)
{
    for (int i : arr)
        cout << -i << " ";
    cout << endl;
}
void solve()
{
    int v, e;
    cin >> v >> e;
    vector<vector<pair<int, int>>> graph(v);
    for (int i = 0; i < e; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        graph[u].push_back({v, -w});
        graph[v].push_back({u, -w});
    }
    for (int i = 0; i < v; i++)
    {
        print(min_dfs(i, graph));
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1;
    // cin >> test;
    while (test--)
    {
        solve();
    }
    return 0;
}