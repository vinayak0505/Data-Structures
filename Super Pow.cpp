#include "vector"

using namespace std;

class Solution {
    int mod = 1337;
    int pow(int a,int b){
        int ans = 1;
        for(int i = 0;i<b;i++){
            ans *= a;
            ans %= mod;
        }
        return ans;
    }
public:
    int superPow(int a, vector<int>& b) {
        a = a % mod;
        int ans = 1;
        for(int i = b.size() - 1;i >= 0;i--){
            ans *= pow(a,b[i]);
            ans %= mod;
            a = pow(a,10);
        }
        return ans;
    }
};