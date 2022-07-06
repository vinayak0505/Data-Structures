#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

const int limit = 1e5 + 2;
vector<int> prime[limit];

int ceil(int a, int b, int c)
{
    return (a - c) / b + 1;
}

void init()
{
    for (int i = 1; i < limit; i++)
        for (int j = 2 * i; j < limit; j += i)
            prime[j].push_back(i);
}

void solve()
{
    int n;
    cin >> n;

    int ans = 0;
    for (int i = 2; i <= n; i++)
        for (int j : prime[i])
            ans += ceil(n, i, j);
    cout << ans << endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1;
    init();
    cin >> test;
    while (test--)
    {
        solve();
    }
    return 0;
}