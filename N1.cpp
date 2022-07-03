#include <bits/stdc++.h>
#define pb push_back
#define INF 1000000000
using namespace std;

char g[13][13];
int id[13][13];
bool blocked[13][13];
int dist[13][13][13][13];
int treasures[13][2];
int treasureCount = 0;
int n;
vector<pair<int, int>> delta = {
    {-1, 0}, {0, -1}, {1, 0}, {0, 1}};

void bfs(int r, int c)
{
    queue<pair<int, int>> q;
    q.push({r, c});

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            dist[r][c][i][j] = INF;

    dist[r][c][r][c] = 0;
    while (!q.empty())
    {
        pair<int, int> p = q.front();
        q.pop();
        int x = p.first, y = p.second;
        for (pair<int, int> d : delta)
        {
            int xx = x + d.first, yy = y + d.second;
            if (min(xx, yy) < 0 || max(xx, yy) >= n || blocked[xx][yy])
                continue;
            if (dist[r][c][xx][yy] > dist[r][c][x][y] + 1)
            {
                dist[r][c][xx][yy] = dist[r][c][x][y] + 1;
                q.push({xx, yy});
            }
        }
    }
}

int DP[13][13][1 << 13];

int f(int r, int c, int mask)
{
    if (mask == 0)
        return dist[r][c][n - 1][n - 1];

    if (DP[r][c][mask] != -1)
        return DP[r][c][mask];

    int ans = INF;
    for (int i = 0; i < treasureCount; i++)
    {
        if ((mask >> i) & 1)
        {
            int rr = treasures[i][0], cc = treasures[i][1];
            ans = min(ans, dist[r][c][rr][cc] + f(rr, cc, mask ^ (1 << i)));
        }
    }
    DP[r][c][mask] = ans;
    return ans;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    cin >> t;
    while (t-- > 0)
    {
        cin >> n;
        treasureCount = 0;
        for (int i = 0; i < n; i++)
        {
            string s;
            cin >> s;
            for (int j = 0; j < n; j++)
            {
                g[i][j] = s[j];
                if (g[i][j] == '*')
                {
                    treasures[treasureCount][0] = i;
                    treasures[treasureCount][1] = j;
                    id[i][j] = treasureCount++;
                }
                else
                    id[i][j] = -1;
                if (g[i][j] == '#')
                    blocked[i][j] = true;
                else
                    blocked[i][j] = false;
            }
        }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < 1 << treasureCount; k++)
                    DP[i][j][k] = -1;
        // Distance
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (blocked[i][j])
                    continue;
                bfs(i, j);
            }
        }
        int ans = f(0, 0, (1 << treasureCount) - 1);
        if (ans >= INF)
            cout << "-1\n";
        else
            cout << ans << "\n";
    }
}