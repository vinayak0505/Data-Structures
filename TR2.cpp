#include <iostream>
#include <vector>
#include <cstring>

using namespace std;
typedef long long ll;

const int MX = 200003;
vector<int> g[MX];

bool dir[1005][1005];
int trie[MX][2], ans[MX], nnode, res;

void dfs(int u, int now)
{
    for (int v : g[u])
    {
        int fdir = dir[u][v];

        if (!trie[now][fdir])
        {
            trie[now][fdir] = nnode++;
        }
        int nnow = trie[now][fdir];

        ans[nnow]++;
        dfs(v, nnow);
    }
}

void dfs2(int u, int dis, int k)
{
    res = max(res, dis);
    for (int i = 0; i < 2; i++)
    {
        int w = trie[u][i];
        if (w && ans[w] >= k)
            dfs2(w, dis + 1, k);
    }
}

void solve()
{
    int m;
    cin >> m;
    nnode = 1;
    memset(trie, 0, sizeof(trie));
    memset(ans, 0, sizeof(ans));
    for (int i = 1; i <= m; i++)
    {
        int n;
        cin >> n;
        memset(g, {}, sizeof(g));
        for (int j = 0; j < n - 1; j++)
        {
            int a, b;
            char c;
            cin >> a >> c >> b;

            dir[a][b] = c == 'L';

            g[a].push_back(b);
        }
        dfs(1, 0);
    }

    for (int i = 1; i <= m; i++)
    {
        res = 0;
        dfs2(0, 0, i);
        cout << res << " ";
    }
    cout << endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1;
    cin >> test;
    while (test--)
        solve();
    return 0;
}