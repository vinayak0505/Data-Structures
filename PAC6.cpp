#include <iostream>
#include <queue>

using namespace std;
typedef long long ll;

int min_time(vector<vector<pair<int, int>>> const &graph, int from, int to)
{
    // 0 = weight, 1 = node, 2 = total_weight
    priority_queue<vector<int>, vector<vector<int>>, greater<>> pq;
    vector<int> dist(graph.size(), INT32_MAX);
    pq.push({0, from});
    dist[from] = 0;
    while (pq.size())
    {
        auto top = pq.top();
        pq.pop();
        for (auto child : graph[top[1]])
        {
            if (dist[child.first] > dist[top[1]] + child.second)
            {
                dist[child.first] = dist[top[1]] + child.second;
                pq.push({child.second, child.first});
            }
        }
    }
    return dist[to];
}

void solve()
{
    // N, M, R, X and Y denoting the number of cities,
    // patient has minutes left before he dies,
    // number of route to reach Hospital,
    // position where you are,
    // and where Hospital is situated.
    int n, m, r, x, y;
    cin >> n >> m >> r >> x >> y;
    vector<vector<pair<int, int>>> graph(n);
    for (int i = 0; i < r; i++)
    {
        int u, v, w;
        cin >> u >> v >> w;
        graph[u - 1].push_back({v - 1, w});
        graph[v - 1].push_back({u - 1, w});
    }
    int total_time = min_time(graph, x - 1, y - 1);
    if (m > total_time)
        cout << "alive " << total_time << "\n";
    else
        cout << "died\n";
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