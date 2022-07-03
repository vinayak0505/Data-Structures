#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;
#define int long long

const int limit = 1e5 + 1;

int prime_sum[limit] = {0}, pre_sum[limit];

void init()
{
    for (int i = 1; i < limit; i += 2)
    {
        for (int j = i; j < limit; j += i)
        {
            prime_sum[j] += i;
        }
    }
}

void init2()
{
    pre_sum[0] = 0;
    for (int i = 1; i < limit; i++)
    {
        pre_sum[i] = pre_sum[i - 1] + prime_sum[i];
    }
}

int32_t main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1;
    cin >> test;
    init();
    init2();
    int u, v;
    while (test--)
    {
        cin >> u >> v;
        cout << pre_sum[v] - pre_sum[u - 1] << endl;
    }
    return 0;
}