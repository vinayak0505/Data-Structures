#include <iostream>
#include <vector>
#include <queue>

using namespace std;
typedef long long ll;

pair<int, int> findStart(vector<string> const &graph)
{
    for (int i = 0; i < graph.size(); i++)
    {
        for (int j = 0; j < graph.size(); j++)
        {
            if (graph[i][j] == 'K')
                return {i, j};
        }
    }
}

pair<int, int> directions[] = {
    {-2, 1},
    {-1, 2},
    {1, 2},
    {2, 1}};
int dfs(pair<int, int> start, vector<string> &graph)
{
    int ans = 0;
    queue<pair<int, int>> q;
    q.push(start);
    while (q.size())
    {
        auto front = q.front();
        q.pop();
        for (auto direction : directions)
        {
            int newx = front.first + direction.first;
            int newy = front.second + direction.second;
            if (newx < 0 || newx >= graph.size() || newy >= graph.size())
                continue;
            if (graph[newx][newy] == 'K')
                continue;
            if (graph[newx][newy] == 'P')
                ans++;
            graph[newx][newy] = 'K';
            q.push({newx, newy});
        }
    }
    return ans;
}

void solve()
{
    int n;
    cin >> n;
    vector<string> graph(n);
    for (auto &i : graph)
        cin >> i;
    auto start = findStart(graph);
    cout << dfs(start, graph) << endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1;
    cin >> test;
    while (test--)
    {
        solve();
    }
    return 0;
}