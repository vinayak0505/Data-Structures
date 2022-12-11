#include <iostream>
#include <vector>
#include <string.h>

using namespace std;
typedef long long ll;

int dp[1001][51][51];


void init(){
    memset(dp,sizeof dp, -1);
    dp[0][0][0] = dp[1][1][0] = dp[0][0][1] = 1;
    
}
void solve()
{
    
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