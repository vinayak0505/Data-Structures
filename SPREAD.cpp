#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

const int limit = 1e6;
ll bit[limit] = {0};
int _size;

void update(int node, int val)
{
    while (node < _size)
    {
        bit[node] += val;
        node += (node & -node);
    }
}

ll query(int node)
{
    ll ans = 0;
    while (node > 0)
    {
        ans += bit[node];
        node -= (node & -node);
    }
    return ans;
}

void solve()
{
    int n, m, c, from, to, val;
    char q;
    cin >> n >> m >> c;
    _size = n + 2;

    for (int i = 0; i < m; i++)
    {
        cin >> q;
        if (q == 'Q')
        {
            cin >> from;
            cout << query(from) + (ll)c << endl;
        }
        else
        {
            cin >> from >> to >> val;
            update(from, val);
            update(to + 1, -val);
        }
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    solve();
    return 0;
}