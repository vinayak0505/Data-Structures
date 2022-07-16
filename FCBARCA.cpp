#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

int mod = 1e9 + 7;

void solve()
{
    int n, k;
    cin >> n >> k;
    // first = passed to messi, passed to other team;
    vector<pair<ll, ll>> arr(n);
    arr.front() = {0, k};
    for (int i = 1; i < n; i++)
    {
        arr[i].first = arr[i - 1].second;
        arr[i].second = (arr[i - 1].first * k) % mod + (arr[i - 1].second * (k - 1)) % mod;
    }
    cout << arr.back().first << endl;
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