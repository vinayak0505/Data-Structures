#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
typedef long long ll;

ll find(vector<int> &trees, int n)
{
    sort(trees.begin(), trees.end(), greater<>());
    ll ans = 0;
    for (int i = 0; i < n && i < trees.size(); i++){
        ans += trees[i];
    }
    return ans;
}

void solve()
{
    // m = number of tress, n = number of squirrel, k = number of chestnut
    int m, n, k;
    cin >> m >> n >> k;
    vector<int> trees(m);
    vector<pair<int, int>> time(m);
    for (auto &i : time)
        cin >> i.first;
    for (auto &i : time)
        cin >> i.second;
    int ans = 0, l = 0, h = INT32_MAX / 2;
    while (l <= h)
    {
        int mid = (h + l) / 2;
        for (int j = 0; j < m; j++)
        {
            if (time[j].first <= mid)
                trees[j] = ((mid - time[j].first) / time[j].second) + 1;
            else
                trees[j] = 0;
        }
        if (k <= find(trees, n))
        {
            ans = mid;
            h = mid - 1;
        }
        else
            l = mid + 1;
    }
    cout << ans << endl;
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