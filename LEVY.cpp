#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

const int limit = 1e5 + 1;
vector<int> arr(limit);

void init()
{
    vector<int> prime;
    int data[limit] = {0};
    for (int i = 2; i < limit; i++)
    {
        if (data[i])
            continue;
        prime.push_back(i);
    }
    for (int i : prime)
    {
        for (int j : prime)
        {
            if (i + 2 * j < limit)
                arr[i + 2 * j]++;
        }
    }
}
void solve()
{
    int n;
    cin>>n;
    cout<<arr[n]<<endl;
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