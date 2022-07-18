#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
typedef long long ll;

vector<int> group(vector<pair<ll,int>> arr,ll const k){
    int group_number = 1;
    sort(arr.begin(),arr.end());
    vector<int> ans(arr.size());
    ans[arr.front().second] = group_number;
    for (int i = 1; i < arr.size(); i++)
    {
        if(arr[i].first <= arr[i - 1].first + k) 
            ans[arr[i].second] = group_number;
        else ans[arr[i].second] = ++group_number;
    }
    return ans;
    
}
void solve()
{
    
    int n,k,p;
    cin>>n>>k>>p;
    vector<pair<ll,int>> arr(n);
    for (int i = 0; i < n; i++)
    {
        cin>>arr[i].first;
        arr[i].second = i;
    }

    auto parent = group(arr,k);
    int from,to;
    for (int i = 0; i < p; i++)
    {
        cin>>from>>to;
        if(parent[from - 1] == parent[to - 1]) cout<<"Yes\n";
        else cout<<"No\n";
    }

}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1;
    // cin>>test;
    while(test--)
    {
        solve();
    }
    return 0;
}