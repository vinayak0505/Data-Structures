#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

int max(vector<vector<int>> const &total, int start, int end)
{
    if (start > end)
        return -1e7;
    int ans = 0;
    for (int i = 0; i < 26; i++)
        ans = max(ans, total[end + 1][i] - total[start][i]);
    if (ans == 0)
        return -1e7;
    return ans;
}

void solve()
{
    string str;
    cin >> str;
    int ans = 0;
    vector<vector<int>> total(str.size() + 1, vector<int>(26));
    vector<int> hash_index;
    for (int i = 1; i < str.size() + 1; i++)
    {
        total[i] = total[i - 1];
        if (str[i - 1] != '#')
            total[i][str[i - 1] - 'a']++;
        else
            hash_index.push_back(i - 1);
    }
    for (int i = 1; i < hash_index.size() - 1; i++)
    {
        ans = max(ans,
                  max(total, 0, hash_index[i - 1] - 1) +
                      max(total, hash_index[i - 1] + 1, hash_index[i] - 1) +
                      max(total, hash_index[i] + 1, hash_index[i + 1] - 1) +
                      max(total, hash_index[i + 1] + 1, str.size() - 1) + 3);
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