#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;
#define int ll

int find_min_ltr(int n, vector<int> const &arr)
{
    vector<int> min_arr(n), max_arr(n);

    min_arr.front() = arr.front();
    max_arr.back() = arr.back();

    for (int i = 1; i < n; i++)
        min_arr[i] = min(arr[i], arr[i] + min_arr[i - 1]);

    for (int i = n - 2; i >= 0; i--)
        max_arr[i] = max(arr[i], arr[i] + max_arr[i + 1]);

    int ans = 0;
    for (int i = 1; i < n; i++)
        ans = max(ans, max_arr[i] - min_arr[i - 1]);

    return ans;
}

int find_min_rtl(int n, vector<int> const &arr)
{
    vector<int> min_arr(n), max_arr(n);

    min_arr.back() = arr.back();
    max_arr.front() = arr.front();

    for (int i = n - 2; i >= 0; i--)
        min_arr[i] = min(arr[i], arr[i] + min_arr[i + 1]);

    for (int i = 1; i < n; i++)
        max_arr[i] = max(arr[i], arr[i] + max_arr[i - 1]);

    int ans = 0;
    for (int i = n - 2; i >= 0; i--)
        ans = max(ans, max_arr[i] - min_arr[i + 1]);

    return ans;
}

void solve()
{
    int n;
    cin >> n;
    vector<int> arr(n);
    for (auto &i : arr)
        cin >> i;
    cout<<max(find_min_ltr(n,arr),find_min_rtl(n,arr))<<endl;
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