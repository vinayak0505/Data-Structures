#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

const int limit = 1e6 + 1;

ll diamond(int n){
    int odd = 0,even = 0,temp;
    while(n){
        temp = n%10;
        n /= 10;
        if(temp & 1) odd += temp;
        else even += temp;
    }
    if(odd > even) return odd - even;
    return even - odd;
}

ll dp[limit];

void init(){
    ll temp[limit];
    temp[1] = 2;
    for (int i = 2; i < limit; i++)
    {
        temp[i] = temp[i - 1] + diamond(i<<1) + diamond((i<<1) - 1) - diamond(i);
    }
    dp[1] = 2;
    for (int i = 2; i < limit; i++)
    {
        dp[i] = (temp[i]<<1) + dp[i - 1] - diamond(i<<1);
    }
}


int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1,i;
    cin>>test;
    init();
    while(test--)
    {
        cin>>i;
        cout<<dp[i]<<endl;
    }
    return 0;
}