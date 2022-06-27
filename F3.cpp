#include <iostream>
#include <queue>
#include <unordered_map>

using namespace std;
typedef long long ll;

const int mod = 21945;
const int grid_size = 8;

class Touch
{
    bool right = false, left = false, top = false, bottom = false;
    unordered_map<int, int> mp;

    int power2(int y, int x = 2)
    {
        if (mp.count(y))
            return mp[y];
        int start = y;
        int res = 1;
        while (y > 0)
        {
            if (y & 1)
                res = (res * x) % mod;
            y = y >> 1; // y = y/2
            x = (x * x) % mod;
        }
        return mp[start] = res;
    }

    int getVertical(int n)
    {
        if (n == 1)
            return 1;
        if (!right && !left)
            return power2(n - 1);
        if (!right && left)
            return power2(n - 2) + 1;
        if (right && !left)
            return power2(n - 2);
        return 1;
    }

    int getHorizontal(int n)
    {
        if (n == 1)
            return 1;
        if (!top && !bottom)
            return power2(n - 1);
        if (top && !bottom)
            return power2(n - 2) + 1;
        if (!top && bottom)
            return power2(n - 2);
        return 1;
    }

public:
    void setRight() { right = true; }
    void setLeft() { left = true; }
    void setTop() { top = true; }
    void setBottom() { bottom = true; }

    int getCount(int n)
    {
        return (getHorizontal(n) * getVertical(n)) % mod;
    }
};

const pair<int, int> directions[] = {
    {0, -1},
    {0, 1},
    {1, 0},
    {-1, 0}};

int dfs(int x, int y, string graph[grid_size], int n)
{
    Touch ans;
    queue<pair<int, int>> q;
    q.push({x, y});
    graph[x][y] = '0';
    while (q.size())
    {
        auto front = q.front();
        q.pop();

        if (front.first == 0)
            ans.setTop();
        else if (front.first == 7)
            ans.setBottom();
        if (front.second == 0)
            ans.setLeft();
        else if (front.second == 7)
            ans.setRight();

        for (auto direction : directions)
        {
            int newx = front.first + direction.first;
            int newy = front.second + direction.second;
            if (newx < 0 || newy < 0 || newx == 8 || newy == 8)
                continue;
            if (graph[newx][newy] == '1')
            {
                graph[newx][newy] = '0';
                q.push({newx, newy});
            }
        }
    }
    return ans.getCount(n);
}

void solve()
{
    int n;
    cin >> n;
    string graph[grid_size];
    int ans = 0;
    for (auto &str : graph)
        cin >> str;
    int temp;
    for (int i = 0; i < grid_size; i++)
        for (int j = 0; j < grid_size; j++)
            if (graph[i][j] == '1')
            {
                temp = dfs(i, j, graph, n - 2);
                ans = (ans + temp) % mod;
            }
    cout << ans << endl;
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