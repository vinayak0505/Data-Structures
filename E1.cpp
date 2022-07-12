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
    return {0,0};
}

pair<int, int> directions[] = {
    {-2, 1},
    {-1, 2},
    {1, 2},
    {2, 1}};

int dfs(pair<int, int> start, vector<string> &graph,vector<vector<int>> &dp)
{
    if(dp[start.first][start.second] != -1)
    return dp[start.first][start.second];
    int ans = 0;
    for (auto direction: directions)
    {
        int newx = start.first + direction.first;
        int newy = start.second + direction.second;
        if(newx >= graph.size() || newx < 0 || newy >= graph.size())
        continue;
        ans = max(ans,dfs({newx,newy},graph,dp));
    }
    if(graph[start.first][start.second] == 'P') ans++;
    return dp[start.first][start.second] = ans;
}

void solve()
{
    int n;
    cin >> n;
    vector<string> graph(n);
    for (auto &i : graph)
        cin >> i;
    auto start = findStart(graph);
    vector<vector<int>> dp(n,vector<int>(n,-1));
    cout << dfs(start, graph,dp) << endl;
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