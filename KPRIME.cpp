#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

bool debug = false;
const int limit = 1e5 + 1;
const int factors = 1e3 + 1;

vector<int> factor_freq[limit];

int factor_count[limit] = {0};

void init()
{
    for(auto &i:factor_freq){
        i = vector<int>(factors);
    }
    for (int i = 2; i < limit; i++)
    {
        if (factor_count[i])
            continue;
        for (int j = i; j < limit; j += i)
            factor_count[j]++;
    }
    if(debug){
        for(int i:factor_count) cout<<i<<endl;
    }
}

void init2()
{
    for (int i = 1; i < limit; i++)
    {
        factor_freq[i] = factor_freq[i - 1];
        factor_freq[i][factor_count[i]]++;
    }
}

void solve()
{
    int u, v, k;
    cin >> u >> v >> k;
    cout << factor_freq[v][k] - factor_freq[u - 1][k] << endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    init();
    init2();
    int test = 1;
    cin >> test;
    while (test--)
    {
        solve();
    }
    return 0;
}