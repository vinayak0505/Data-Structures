#include <iostream>
#include <queue>

using namespace std;
typedef long long ll;

void print(int n, int k)
{
    cout << "2\n";
    for (int i = 0; i < n; i++)
    {
        if (i == k)
            cout << "2 ";
        else
            cout << "1 ";
    }
    cout << endl;
}


void print(int first, int second, int n)
{
    cout << "3\n";
    for (int i = 0; i < n; i++)
    {
        if (i == first)
            cout << "2 ";
        else if (i == second)
            cout << "3 ";
        else
            cout << "1 ";
    }
    cout << endl;
}

void solve()
{
    int n, m;
    cin >> n >> m;
    vector<vector<int>> graph(n);
    for (int i = 0; i < m; i++)
    {
        int u, v;
        cin >> u >> v;
        graph[u - 1].push_back(v - 1);
        graph[v - 1].push_back(u - 1);
    }
    if (m % 2 == 0)
    {
        cout << "1\n";
        for (int i = 0; i < n; i++)
            cout << "1 ";
        cout << endl;
        return;
    }
    for (int i = 0; i < n; i++)
    {
        if (graph[i].size() % 2)
        {
            print(n, i);
            return;
        }
    }
    for (int i = 0; i < n; i++)
    {
        if (graph[i].size())
        {
            print(i, graph[i].front(), n);
            return;
        }
    }
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