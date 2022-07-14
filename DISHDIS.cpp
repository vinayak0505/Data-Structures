#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

int mod = 1e9 + 7;

void solve()
{
    int n, m;
    cin >> n >> m;
    vector<int> chef(m);
    for (int i = 0, first, second; i < m; i++)
    {
        cin >> first >> second;
        n -= first;
        chef[i] = second - first;
    }
    vector<vector<int>> arr(m,vector<int>(n + 1));
    for (int i = 0; i <= n && i <= chef.front(); i++)
        arr.front()[n - i] = 1;

    for (int i = 1; i < m; i++)
    {
        for (int j = n; j >= 0 && arr[i - 1][j]; j--)
        {
            for (int k = j;k >= 0 && j - k <= chef[i]; k--)
            arr[i][k] = (arr[i][k] + arr[i - 1][j])%mod;
        }
        
    }
    
    cout<<arr.back().front()<<endl;
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