#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

bool debug = true;

void print(vector<vector<int>> &arr){
    if(!debug) return;
    for(auto i:arr){
        for(auto j:i) cout<<j<<" ";
        cout<<endl;
    }
}
void solve()
{
    int n,l,a,b;
    cin>>n>>l>>a>>b;
    string str;
    cin>>str;
    int left = min(n,a);
    int right = min(l - a,n);
    vector<vector<int>>dp(n + 1,vector<int>(left + right + 1));
    dp.front()[a] = 1;
    for (int i = 1; i <= n; i++)
    {
        if(str[i - 1] == '+'){
            dp[i].front() = dp[i - 1].front();
            for(int j = 1; j < dp[i].size(); j++){
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }else{
            dp[i].back() = dp[i - 1].back();
            for(int j = 0; j < dp[i].size() - 1; j++){
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j + 1];
            }
        }
    }
    print(dp);
    cout<<dp.back()[b]<<endl;
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