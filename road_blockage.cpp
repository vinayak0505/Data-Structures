#include <iostream>
#include <bits/stdc++.h>

using namespace std;
typedef long long ll;
map<vector<int>, int> ans;
vector<int, vector<vector<int>>> elements;

int dfs(int node, int parent, vector<vector<int>> const &graph, int dis)
{
    int v1 = 0;
    if (graph[node].size() == 1)
        return 1;
    if (elements.size() < dis - 1)
        elements.push_back({});
    for (int child : graph[node])
    {
        if (child == parent)
            continue;
        int val = dfs(child, node, graph, dis + 1);
        v1 = max(v1, val);
        ans[{node, child}] = dis;
        elements[dis].push_back({node, child, val});
    }
    return v1 + 1;
}
int find(pair<int, int> ele)
{
    int base = ans[ele];
    int ans = base;
    for (auto &i : elements[base])
    {
        if (ele.first == i[0] && ele.second == i[1])
            continue;
        ans = max(ans, i[2] + base);
    }
    return ans;
}

void solve()
{
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