#include <iostream>
#include <vector>
#include <cstring>

using namespace std;
typedef long long ll;

int cost[1001];
void solve()
{
    int n;
    cin>>n;
    vector<int> dil(n),fuel(n);
    for(int &i:dil) cin>>i;
    for(int &i:fuel) cin>>i;
    memset(cost,60,sizeof cost);
    cost[0] = 0;
    for (int i = 0;i<n;i++)
    {
        for (int j = 0; j <= 1000; j++)
        {
            if(fuel[i] + j <= 1000) cost[fuel[i] + j] = min(cost[fuel[i] + j],cost[j] + 1);
        }
    }
    int ans = 0;
    for(int i:dil) ans += cost[i<<1];
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