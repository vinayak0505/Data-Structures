#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

const int limit = 1e6 + 1;
bool isNotPrime[limit] = {0};
int preCompute[limit] = {0};
void init()
{
    for (int i = 2; i * i < limit; i++)
    {
        if (isNotPrime[i])
            continue;
        for (int j = i + i; j < limit; j += i)
        {
            isNotPrime[j] = true;
        }
    }
}
void init2()
{
    for (int i = 5; i < limit; i++)
    {
        if (!isNotPrime[i - 2] && !isNotPrime[i])
            preCompute[i] = preCompute[i - 1] + 1;
        else
            preCompute[i] = preCompute[i - 1];
    }
}
void solve()
{
    int n;
    cin >> n;
    cout << preCompute[n] << endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1;
    init();
    init2();
    cin >> test;
    while (test--)
    {
        solve();
    }
    return 0;
}