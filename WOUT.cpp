#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

void solve()
{
    ll n,h,l,m;
    cin>>n>>h;
    vector<ll> arr(n);
    for (int i = 0; i < n; i++)
    {
        cin>>l>>m;
        arr[l]++;
        if(m < n - 1) arr[m+ 1]--;
    }
    ll temp = 0;
    for (int i = 0; i < n; i++)
    {
        temp += arr[i];
        arr[i] = temp;
    }
    ll ans = 0;
    temp = 0;
    for (int i = 0; i < h; i++)
        temp += arr[i];
    
    ans = temp;
    for (int i = h; i < n; i++)
    {
        temp += arr[i];
        temp -= arr[i - h];
        ans = max(ans,temp);
    }
    cout<<n * h - ans<<endl;
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