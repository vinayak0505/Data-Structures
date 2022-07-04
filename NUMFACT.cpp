#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;
typedef long long ll;

const int limit = 1e6 + 1;
int prime_count[limit] = {0};

bool debug = true;

void init()
{
    for (int i = 2; i < limit; i++)
    {
        if (prime_count[i])
            continue;
        for (int j = i; j < limit; j += i)
            if (prime_count[j] == 0)
                prime_count[j] = i;
    }
}

void solve()
{
    int n;
    cin >> n;
    unordered_map<int, int> ans;
    for (int i = 0; i < n; i++)
    {
        int k;
        cin >> k;
        while(k > 1){
            ans[prime_count[k]]++;
            k /= prime_count[k];
        }
    }
    int final_ans = 1;
    for (auto pair : ans)
    {
        final_ans *= pair.second + 1;
    }
    cout << final_ans << endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    init();
    int test = 1;
    cin >> test;
    while (test--)
    {
        solve();
    }
    return 0;
}