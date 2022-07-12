#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

void solve()
{
    int n;
    cin>>n;
    vector<int> arr(n),dp(n,1);
    for(auto &i:arr) cin>>i;
    for (int i = 1; i < n; i++)
    {
        if(arr[i - 1] <= arr[i]) dp[i] = dp[i - 1] + 1;
    }
    ll ans = 0;
    for(int i:dp) {
        ans += i;
    }
    cout<<ans<<endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1;
    cin>>test;
    while(test--)
    {
        solve();
    }
    return 0;
}   