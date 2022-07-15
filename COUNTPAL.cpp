#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;
typedef long long ll;

int mod = 1e9 + 7;
string s;
unordered_map<int,bool> mymap2;

bool isPalindrome(int j, int i)
{
    int key = i * 1000 + j;
    if(mymap2.count(key)) return mymap2[key];
    if (j == i || j + 1 == i)
        return true;
    if (s[j] != s[i - 1])
        return false;
    return mymap2[key] = isPalindrome(j + 1, i - 1);
}

unordered_map<int,int> mymap;

int F(int i)
{
    if(mymap.count(i)) return mymap[i];
    if (i == 0)
        return 1;
    int res = 0;
    for (int j = 0; j < i; j++)
        if (isPalindrome(j, i))
            res = (F(j) + res)%mod;
    return mymap[i] = res;
}

void solve()
{
    mymap2 = unordered_map<int,bool>();
    mymap = unordered_map<int,int>();
    cin >> s;
    cout << F(s.size()) << endl;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1;
    // cin>>test;
    while (test--)
    {
        solve();
    }
    return 0;
}